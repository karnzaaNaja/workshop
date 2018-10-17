package com.example.msi_gl62.workshop.simple;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import com.example.msi_gl62.workshop.R;
import com.example.msi_gl62.workshop.adapter.AdapterListView;
import com.example.msi_gl62.workshop.model.UserModel;
import com.example.msi_gl62.workshop.sqldatabase.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;


public class ScrollingActivity extends AppCompatActivity {

    private AdapterListView adapterListView;
    private List<UserModel> readUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        setListView();

    }


    public void setListView() {

        ListView listViewScroolling = findViewById(R.id.listViewScroolling);

        DatabaseHelper helper = new DatabaseHelper(this);
        readUser = helper.readUser();
        ArrayList<UserModel> listUser = (ArrayList<UserModel>) readUser;
        adapterListView = new AdapterListView(listUser, getApplicationContext());
        listViewScroolling.setAdapter(adapterListView);
        registerForContextMenu(listViewScroolling);


    }

}
