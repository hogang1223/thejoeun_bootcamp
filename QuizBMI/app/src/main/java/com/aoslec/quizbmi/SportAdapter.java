package com.aoslec.quizbmi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class SportAdapter extends BaseAdapter {

    private Context mContext = null;
    private int layout = 0;
    private ArrayList<Sport> data = null;
    private LayoutInflater inflater = null;

    public SportAdapter(Context mContext, int layout, ArrayList<Sport> data) {
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
        return data.get(position).getCalorie();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = inflater.inflate(this.layout, parent, false);
        }
        ImageView imageSport = convertView.findViewById(R.id.imageSport);
        TextView textSport = convertView.findViewById(R.id.textSport);
        TextView textCal = convertView.findViewById(R.id.textCal);


        imageSport.setImageResource(data.get(position).getIcon());
        textSport.setText(data.get(position).getSport());
        textCal.setText(data.get(position).getCalorie());

        if(position % 2 == 1){
            convertView.setBackgroundColor(0x10f0f0f0); // 0x -> #
        }

        return convertView;
    }
}
