package com.example.wsg.nightreading.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.wsg.nightreading.R;
import com.example.wsg.nightreading.entity.DuanZi;

import java.util.List;

/**
 * 项目名：NightReading
 * 包名：com.example.wsg.nightreading.adapter
 * 文件名：DuanZiAdapter
 * 创建者：wsg
 * 创建时间：2017/9/9  14:01
 * 描述：搞笑段子
 */

public class DuanZiAdapter extends BaseAdapter {


    private List<DuanZi> mDatas;
    private Context mContext;

    private LayoutInflater mInflater;

    public DuanZiAdapter(Context context,List<DuanZi> duanZis) {
        mInflater= LayoutInflater.from(context);
        mContext=context;
        mDatas=duanZis;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int i) {
        return mDatas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        Holder holder = null;
        if(view == null) {
            holder = new Holder();
            view = mInflater.inflate(R.layout.item_zhihu,viewGroup,false);

            holder = new Holder();
            holder.mTextView = (TextView)view.findViewById(R.id.item_zhihu_tv);

            view.setTag(holder);
        }else {
            holder = (Holder)view.getTag();
        }

        holder.mTextView.setText(mDatas.get(i).getSubTitle());


        return view;
    }


    private final class Holder {
        TextView mTextView;
    }


}
