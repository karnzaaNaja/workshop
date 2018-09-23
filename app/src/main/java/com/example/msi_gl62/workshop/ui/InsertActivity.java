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
    private EditText et_name,et_pass,et_email,et_tel,et_address;
    private Button btn_insert;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        Bundle bundle = getIntent().getExtras();

        bindView();

        btn_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertData();
            }
        });
        if(bundle != null){
            final String position = bundle.getString("position");
            btn_insert.setText("update");
            btn_insert.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    updateData(position);
                }
            });

        }
    }

    private void bindView() {
        et_name = findViewById(R.id.name);
        et_pass = findViewById(R.id.pass);
        et_email = findViewById(R.id.email);
        et_tel =findViewById(R.id.tel);
        et_address=findViewById(R.id.address);
        btn_insert = findViewById(R.id.btn_insert);

    }

    private void insertData(){
        String name = et_name.getText().toString();
        String pass = et_pass.getText().toString();
        String email = et_email.getText().toString();
        String tel = et_tel.getText().toString();
        String address = et_address.getText().toString();
        if(!name.isEmpty()&&!pass.isEmpty()&&!email.isEmpty()){
            DatabaseHelper helper = new DatabaseHelper(this);
            helper.insertUser(name,pass,email,tel,address);
            finish();
        }else{
            Toast.makeText(this,"กรุณากรอกข้อมูลให้ครบ",Toast.LENGTH_SHORT).show();
        }
    }

    private void updateData(String id){
        String name = et_name.getText().toString();
        String pass = et_pass.getText().toString();
        String email = et_email.getText().toString();
        String tel = et_tel.getText().toString();
        String address = et_address.getText().toString();
        if(!name.isEmpty()&&!pass.isEmpty()&&!email.isEmpty()){
            DatabaseHelper helper = new DatabaseHelper(this);
            helper.updateUser(id,name,pass,email,tel,address);
            finish();
        }else{
            Toast.makeText(this,"กรุณากรอกข้อมูลให้ครบ",Toast.LENGTH_SHORT).show();
        }
    }

}
