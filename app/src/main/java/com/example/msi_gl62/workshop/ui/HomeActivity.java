package com.example.msi_gl62.workshop.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.msi_gl62.workshop.R;
import com.example.msi_gl62.workshop.adapter.AdapterListView;
import com.example.msi_gl62.workshop.model.UserModel;
import com.example.msi_gl62.workshop.sqldatabase.DatabaseHelper;
import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    private AdapterListView adapterListView;
    private List<UserModel> readUser;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ImageView imageView = findViewById(R.id.image_add_user);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomeActivity.this, InsertActivity.class);
                startActivity(i);
            }
        });

        Bundle bundle = getIntent().getExtras();
        String textName = bundle.getString("name");
        String textPassword = bundle.getString("password");
        Toast.makeText(HomeActivity.this,"User : "+ textName,Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onResume() {
        super.onResume();
        getUser();
    }

    private void getUser() {
        DatabaseHelper helper = new DatabaseHelper(this);
        readUser = helper.readUser();
        if (readUser.size() != 0) {
            listView = findViewById(R.id.list);
            ArrayList<UserModel> listUser = (ArrayList<UserModel>) readUser;
            adapterListView = new AdapterListView(listUser, getApplicationContext());
            listView.setAdapter(adapterListView);
            registerForContextMenu(listView);
        }

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        Log.i("INFO", info.position + "");
        menu.setHeaderTitle("แก้ไข");
        menu.add("update");
        menu.add("delete");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int position = info.position;
        Log.i("INFO", position + "");
        if (item.getTitle().equals("update")) {
            String id = readUser.get(position).getId();
            Log.i("UPDATE ID", id + "");
            Intent i = new Intent(HomeActivity.this, InsertActivity.class);
            i.putExtra("position", id);
            startActivity(i);
        } else if (item.getTitle().equals("delete")) {
            String id = readUser.get(position).getId();
            Log.i("DELETE ID", id);
            DatabaseHelper helper = new DatabaseHelper(this);
            helper.deleteUser(id);
            readUser = helper.readUser();
            Log.i("DELETE", readUser.size() + "");
            adapterListView.clear();
            adapterListView.addAll(readUser);
            adapterListView.notifyDataSetChanged();
        }
        return true;

    }

}
