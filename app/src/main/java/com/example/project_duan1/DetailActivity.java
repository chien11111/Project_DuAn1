package com.example.project_duan1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.project_duan1.Fragment_Nav.QLNhanVienFragment;
import com.example.project_duan1.Model.Thanhvien;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Bundle bundle = getIntent().getExtras();
        if (bundle == null){
            return;
        }
        Thanhvien thanhvien = (Thanhvien) bundle.get("objec_ten");

        TextView txtten = findViewById(R.id.txttenchitiet);
        TextView txttuoi = findViewById(R.id.txttuoichitiet);
        TextView txtchucvu = findViewById(R.id.txtchucvuchitiet);
        TextView txtsdt = findViewById(R.id.txtsdtchitiet);
        TextView txtemail = findViewById(R.id.txtemailchitiet);
        Button btntrolai = findViewById(R.id.btntrolai);

        txtten.setText("Tên: " +thanhvien.getTen());
        txttuoi.setText("Tuổi: "+thanhvien.getTuoi());
        txtchucvu.setText("Chức vụ: "+thanhvien.getChucvu());
        txtsdt.setText("Số điện thoại: "+thanhvien.getSdt());
        txtemail.setText("Email: "+thanhvien.getEmail());

        btntrolai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}