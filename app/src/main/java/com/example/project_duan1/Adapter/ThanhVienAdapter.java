package com.example.project_duan1.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_duan1.DetailActivity;
import com.example.project_duan1.Model.Thanhvien;
import com.example.project_duan1.R;

import java.util.ArrayList;
import java.util.List;

public class ThanhVienAdapter extends RecyclerView.Adapter<ThanhVienAdapter.ThanhvienHolder> implements Filterable {

    private List<Thanhvien> mListThanhVien;
    private List<Thanhvien> mListThanhVienOld;

    private IclickListenner mIclickListenner;

    private Context context;




    public interface IclickListenner {
        void onclickUpdateItem(Thanhvien thanhvien);
        void onClickDeleteItem(Thanhvien thanhvien);
    }

    public ThanhVienAdapter(Context context ,List<Thanhvien> mListThanhVien, IclickListenner listenner) {
        this.mListThanhVien = mListThanhVien;
        this.mListThanhVienOld = mListThanhVien;
        this.mIclickListenner = listenner;
        this.context = context;
    }

    @NonNull
    @Override
    public ThanhvienHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyclenhanvien,parent,false);

        return new ThanhvienHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ThanhvienHolder holder, int position) {

        Thanhvien thanhvien = mListThanhVien.get(position);
        if (thanhvien == null){
            return;
        }
        holder.txtten.setText("Tên: " +thanhvien.getTen() );
        holder.txttuoi.setText("Tuổi: "+ thanhvien.getTuoi());
        holder.txtchucvu.setText("Chức vụ: "+ thanhvien.getChucvu());

        holder.imgupdatetv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mIclickListenner.onclickUpdateItem(thanhvien);
            }
        });

        holder.imgxoathanhvien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mIclickListenner.onClickDeleteItem(thanhvien);
            }
        });

        holder.layout_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickGotoDetail(thanhvien);
            }
        });



    }
    private void onClickGotoDetail(Thanhvien thanhvien){
        Intent intent = new Intent(context, DetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("objec_ten",thanhvien);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }



    @Override
    public int getItemCount() {
        if (mListThanhVien != null){
            return mListThanhVien.size();
        }
        return 0;
    }

    public class ThanhvienHolder extends RecyclerView.ViewHolder {
       private TextView txtten,txttuoi,txtchucvu;
       private ImageView imgupdatetv,imgxoathanhvien;

       private CardView layout_item;
       public ThanhvienHolder(@NonNull View itemView) {
           super(itemView);
           txtten = itemView.findViewById(R.id.txttentv);
           txttuoi = itemView.findViewById(R.id.txttuoitv);
           txtchucvu = itemView.findViewById(R.id.txtchucvutv);
           imgupdatetv = itemView.findViewById(R.id.iveditthanhvien);
           imgxoathanhvien = itemView.findViewById(R.id.ivdeletethanhvien);
           layout_item = itemView.findViewById(R.id.layout_item);
       }
   }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String strSearch = charSequence.toString();
                if (strSearch.isEmpty()) {
                    mListThanhVien = mListThanhVienOld;
                }
                else {
                    List<Thanhvien> list = new ArrayList<>();
                    for (Thanhvien thanhvien : mListThanhVienOld){
                        if (thanhvien.getTen().toLowerCase().contains(strSearch.toLowerCase())) {
                            list.add(thanhvien);
                        }
                    }
                    mListThanhVien = list;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = mListThanhVien;

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mListThanhVien = (List<Thanhvien>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public void release() {
        context = null;
    }
}
