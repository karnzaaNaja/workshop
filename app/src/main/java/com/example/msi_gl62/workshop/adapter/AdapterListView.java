package com.example.msi_gl62.workshop.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.msi_gl62.workshop.R;
import com.example.msi_gl62.workshop.model.UserModel;

import java.util.ArrayList;


public class AdapterListView  extends ArrayAdapter<UserModel> implements View.OnClickListener{

    private ArrayList<UserModel> dataSet;
    private Context mContext;

    static class ViewHolder {
        TextView txtNameUser;
        TextView txtEmailUser;
        TextView txtTelUser;
        TextView txtAddressUser;
        ImageView imageViewUser;
        ImageView call;

    }

    public AdapterListView(ArrayList<UserModel> data, Context context) {
        super(context, R.layout.item_user, data);
        this.dataSet = data;
        this.mContext=context;
    }

    @Override
    public void onClick(View v) {
        int position=(Integer) v.getTag();
        Object object= getItem(position);
        UserModel userModel=(UserModel) object;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final UserModel userModel = getItem(position);
        ViewHolder viewHolder;
        final View result;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_user, parent, false);
            viewHolder.txtNameUser = convertView.findViewById(R.id.nameUser);
            viewHolder.txtEmailUser = convertView.findViewById(R.id.emailUser);
            viewHolder.txtTelUser = convertView.findViewById(R.id.telUser);
            viewHolder.txtAddressUser = convertView.findViewById(R.id.addressUser);
            viewHolder.call = convertView.findViewById(R.id.call);
            viewHolder.imageViewUser=convertView.findViewById(R.id.imageUser);
            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

        String sex =userModel.getSex();
        if(sex.equals("F")){
            viewHolder.imageViewUser.setImageResource(R.drawable.gir);
        }else { }

        viewHolder.txtNameUser.setText("ชื่อ: "+userModel.getName());
        viewHolder.txtEmailUser.setText("email: "+userModel.getEmail());
        viewHolder.txtTelUser.setText("เบอร์ติดต่อ: "+userModel.getTel());
        viewHolder.txtAddressUser.setText("ที่อยู่: "+userModel.getAddress());
        viewHolder.call.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("MissingPermission")
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", userModel.getTel(), null));
                mContext.startActivity(intent);
            }
        });
        return convertView;
    }
}