package com.example.doron.frag;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


public class MySimpleArrayAdapter extends ArrayAdapter<String> {
    private final Context context;
    private final String[] values;

    public MySimpleArrayAdapter(Context context, String[] values) {
        super(context, R.layout.rowlayout, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.rowlayout, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.label);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        textView.setText(values[position]);
        // Change the icon for Windows and iPhone
        String s = values[position];
//        imageView.setImageResource(R.drawable.pic2);
        switch(position){
            case 0:
                imageView.setImageResource(R.drawable.pic1);
                break;
            case 1:
                imageView.setImageResource(R.drawable.pic2);
                break;
            case 2:
                imageView.setImageResource(R.drawable.pic3);
                break;
            case 3:
                imageView.setImageResource(R.drawable.pic4);
                break;
            case 4:
                imageView.setImageResource(R.drawable.pic5);
                break;
            default:
                imageView.setImageResource(R.drawable.pic1);
                break;
        }


        return rowView;
    }
}