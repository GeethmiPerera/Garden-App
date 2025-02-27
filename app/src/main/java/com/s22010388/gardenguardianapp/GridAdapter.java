package com.s22010388.gardenguardianapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class GridAdapter extends BaseAdapter {

    Context context;
    String[] outdoorPlantName;
    int[] image;
    LayoutInflater inflater;

    public GridAdapter(Context context, String[] outdoorPlantName, int[] image) {
        this.context = context;
        this.outdoorPlantName = outdoorPlantName;
        this.image = image;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return outdoorPlantName.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.grid_item, parent, false);
        }

        ImageView imageView = convertView.findViewById(R.id.grid_img);
        TextView textView = convertView.findViewById(R.id.item_name);

        imageView.setImageResource(image[position]);
        textView.setText(outdoorPlantName[position]);

        return convertView;
    }
}
