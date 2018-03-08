package com.example.a16047.listview_viewholder_test;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by 16047 on 2018/3/7.
 */

public class ViewHolderAdapter extends BaseAdapter {
    private List<String> mData;
    private LayoutInflater mInflater;
    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int i) {
        return mData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public ViewHolderAdapter(){

    }

    public ViewHolderAdapter(Context context,List<String> mData){
        this.mData=mData;
        mInflater=LayoutInflater.from(context);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder=null;
        if(view==null){
            holder=new ViewHolder();
            view=mInflater.inflate(R.layout.viewholder_item,null);
            holder.img=(ImageView)view.findViewById(R.id.imageView);
            holder.title=(TextView)view.findViewById(R.id.textView);
            view.setTag(holder);
        }else {
            holder= (ViewHolder) view.getTag();
        }
        holder.img.setBackgroundResource(R.mipmap.ic_launcher);
        holder.title.setText(mData.get(i));
        return view;
    }

    public final class ViewHolder{
        public ImageView img;
        public TextView title;
    }
}
