package com.example.msi_gl62.workshop.simple;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.example.msi_gl62.workshop.R;
import com.example.msi_gl62.workshop.adapter.AdapterListView;
import com.example.msi_gl62.workshop.model.UserModel;
import com.example.msi_gl62.workshop.sqldatabase.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AdapterListView adapterListView;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<UserModel> list = new ArrayList<>();
        UserModel userModel = new UserModel();
        userModel.setName("Nawakarn");
        userModel.setEmail("karn939.n@gmail.com");
        userModel.setAddress("Angthong");
        userModel.setTel("0916996188");
        list.add(userModel);

        listView = findViewById(R.id.listView);
        ArrayList<UserModel> listUser = (ArrayList<UserModel>) list;
        adapterListView = new AdapterListView(listUser, getApplicationContext());
        listView.setAdapter(adapterListView);
        registerForContextMenu(listView);


    }
}
