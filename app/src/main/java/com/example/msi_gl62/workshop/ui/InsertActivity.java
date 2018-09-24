package com.example.msi_gl62.workshop.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.msi_gl62.workshop.R;
import com.example.msi_gl62.workshop.sqldatabase.DatabaseHelper;

public class InsertActivity extends AppCompatActivity {
    private EditText edtName,edtPassword,edtEmail,edtTel,edtAddress;
    private Button btnInsert;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        Bundle bundle = getIntent().getExtras();

        bindView();

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertData();
            }
        });
        if(bundle != null){
            final String position = bundle.getString("position");
            btnInsert.setText("update");
            btnInsert.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    updateData(position);
                }
            });

        }
    }

    private void bindView() {
        edtName = findViewById(R.id.name);
        edtPassword = findViewById(R.id.pass);
        edtEmail = findViewById(R.id.email);
        edtTel=findViewById(R.id.tel);
        edtAddress=findViewById(R.id.address);
        btnInsert = findViewById(R.id.btn_insert);

    }

    private void insertData(){
        String name = edtName.getText().toString();
        String pass = edtPassword.getText().toString();
        String email = edtEmail.getText().toString();
        String tel = edtTel.getText().toString();
        String address = edtAddress.getText().toString();
        if(!name.isEmpty()&&!pass.isEmpty()&&!email.isEmpty()){
            DatabaseHelper helper = new DatabaseHelper(this);
            helper.insertUser(name,pass,email,tel,address);
            finish();
        }else{
            Toast.makeText(this,"กรุณากรอกข้อมูลให้ครบ",Toast.LENGTH_SHORT).show();
        }
    }

    private void updateData(String id){
        String name = edtName.getText().toString();
        String pass = edtPassword.getText().toString();
        String email = edtEmail.getText().toString();

        String tel = edtTel.getText().toString();
        String address = edtAddress.getText().toString();
        if(!name.isEmpty()&&!pass.isEmpty()&&!email.isEmpty()){
            DatabaseHelper helper = new DatabaseHelper(this);
            helper.updateUser(id,name,pass,email,tel,address);
            finish();
        }else{
            Toast.makeText(this,"กรุณากรอกข้อมูลให้ครบ",Toast.LENGTH_SHORT).show();
        }
    }

}
