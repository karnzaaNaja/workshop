package com.example.msi_gl62.workshop.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.msi_gl62.workshop.R;
public class LoginActivity extends AppCompatActivity {

    private Button btnSubmitLogin;
    private EditText edtUsername;
    private EditText edtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnSubmitLogin = findViewById(R.id.btnSubmitLogin);
        edtUsername =findViewById(R.id.userNameLogin);
        edtPassword=findViewById(R.id.passwordLogin);

        btnSubmitLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                final String userName = edtUsername.getText().toString();
                final String password = edtPassword.getText().toString();
                if(!userName.isEmpty()&&!password.isEmpty()){
                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    intent.putExtra("name", userName);
                    intent.putExtra("password", password);
                    startActivity(intent);
                }else{
                    Toast.makeText(LoginActivity.this,"กรุณากรอกข้อมูลให้ครบ",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
