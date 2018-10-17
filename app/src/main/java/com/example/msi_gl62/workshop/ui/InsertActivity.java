package com.example.msi_gl62.workshop.ui;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.msi_gl62.workshop.R;
import com.example.msi_gl62.workshop.sqldatabase.DatabaseHelper;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import es.dmoral.toasty.Toasty;

public class InsertActivity extends AppCompatActivity {
    private EditText edtName,edtEmail,edtTel,edtAddress;
    private Button btnInsert;
    private Button btnChooses;
    private ImageView imageView;
    final int REQUEST_CODE_GALLERY = 999;
    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        bindView();
        Bundle bundle = getIntent().getExtras();
        setImage();
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
        edtEmail = findViewById(R.id.email);
        edtTel=findViewById(R.id.tel);
        edtAddress=findViewById(R.id.address);
        btnInsert = findViewById(R.id.btn_insert);
        btnChooses = findViewById(R.id.btnChoose);
        imageView=findViewById(R.id.imageView);
    }


    public static byte[] imageViewToByte(ImageView image) {
        Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK && data != null){
            Uri uri = data.getData();

            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);

                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imageView.setImageBitmap(bitmap);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if(requestCode == REQUEST_CODE_GALLERY){
            if(grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_CODE_GALLERY);
            }
            else {
                Toast.makeText(getApplicationContext(), "You don't have permission to access file location!", Toast.LENGTH_SHORT).show();
            }
            return;
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }


    private  void  setImage(){
        btnChooses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityCompat.requestPermissions(
                        InsertActivity.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_CODE_GALLERY
                );
            }
        });
    }


    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch(view.getId()) {
            case R.id.radio_pirates:
                if (checked)
                    buttonOnClick("M");
                    break;
            case R.id.radio_ninjas:
                if (checked)
                    buttonOnClick("F");
                    break;
        }
    }



    private void buttonOnClick(final String sex){
        btnInsert.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        insertData(sex);
                    }
                });
    }


    private void insertData(String sex){
        String name = edtName.getText().toString();
        String email = edtEmail.getText().toString();
        String tel = edtTel.getText().toString();
        String address = edtAddress.getText().toString();
        if(!name.isEmpty()&&!email.isEmpty()){
            DatabaseHelper helper = new DatabaseHelper(this);
            helper.insertUser(name,email,tel,address,sex);
            finish();
            Toasty.success(this, "เพิ่มรายชื่อ", Toast.LENGTH_SHORT, true).show();
        }else{
            Log.e("dfdfdfdfdf","sex ="+sex);
            Toasty.error(this, "กรุณากรอกให้ครบ", Toast.LENGTH_SHORT, true).show();
        }
    }

    private void updateData(String id){
        String name = edtName.getText().toString();
        String email = edtEmail.getText().toString();

        String tel = edtTel.getText().toString();
        String address = edtAddress.getText().toString();
        if(!name.isEmpty()&&!email.isEmpty()){
            DatabaseHelper helper = new DatabaseHelper(this);
            helper.updateUser(id,name,email,tel,address);
            finish();
        }else{
            Toast.makeText(this,"กรุณากรอกข้อมูลให้ครบ",Toast.LENGTH_SHORT).show();
        }
    }

}
