package com.appvn.cocacola.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.appvn.cocacola.R;
import com.appvn.cocacola.model.Fonts;

import java.util.ArrayList;

/**
 * Created by truongtvd on 7/18/14.
 */
public class FontAdapter extends ArrayAdapter<Fonts> {
    private Context context;
    private LayoutInflater inflater;
    private Typeface typeface;
    public FontAdapter(Context context, int resource, ArrayList<Fonts> objects) {
        super(context, resource, objects);
        this.context=context;
        this.inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public View getDropDownView(int position, View convertView,ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    public View getCustomView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=null;
        if(convertView==null){
            viewHolder=new ViewHolder();
            convertView=inflater.inflate(R.layout.item_layout,null);
            viewHolder.tvTitle=(TextView)convertView.findViewById(R.id.tvtitle);
            convertView.setTag(viewHolder);
        }else{
            viewHolder=(ViewHolder)convertView.getTag();

        }
        Fonts fonts=getItem(position);
        typeface=Typeface.createFromAsset(context.getAssets(),"fonts/"+fonts.getFont());
        viewHolder.tvTitle.setText(fonts.getTitle());
        viewHolder.tvTitle.setTypeface(typeface);
        return  convertView;
    }

    private class ViewHolder{
        TextView tvTitle;
    }
}
