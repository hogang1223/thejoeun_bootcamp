package com.aoslec.networkjson_student;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class StudentAdapter extends BaseAdapter {
    private Context mContext = null;
    private int layout = 0;
    private ArrayList<Student> data = null;
    private LayoutInflater inflater = null;

    public StudentAdapter(Context mContext, int layout, ArrayList<Student> data) {
        this.mContext = mContext;
        this.layout = layout;
        this.data = data;
        this.inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position).getCode();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.v("Status", "..........");
        convertView = inflater.inflate(this.layout, parent, false);
        TextView tv_custom_code = convertView.findViewById(R.id.tv_custom_code);
        TextView tv_custom_name = convertView.findViewById(R.id.tv_custom_name);
        TextView tv_custom_dept = convertView.findViewById(R.id.tv_custom_dept);
        TextView tv_custom_phone = convertView.findViewById(R.id.tv_custom_phone);

        tv_custom_code.setText(data.get(position).getCode());
        tv_custom_name.setText(data.get(position).getName());
        tv_custom_dept.setText(data.get(position).getDept());
        tv_custom_phone.setText(data.get(position).getPhone());

        return convertView;
    }
}
