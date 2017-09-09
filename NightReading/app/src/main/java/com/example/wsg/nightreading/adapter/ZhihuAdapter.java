package com.example.wsg.nightreading.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.wsg.nightreading.R;
import com.example.wsg.nightreading.entity.ZhiHu;

import java.util.List;

/**
 * 项目名：NightReading
 * 包名：com.example.wsg.nightreading.adapter
 * 文件名：ZhiHuAdapter
 * 创建者：wsg
 * 创建时间：2017/9/9  14:00
 * 描述：知乎日报
 */

public class ZhiHuAdapter extends BaseAdapter {
    private List<ZhiHu> mDatas;
    private Context mContext;

    private LayoutInflater mInflater;

    public ZhiHuAdapter(Context context, List<ZhiHu> zhiHus) {
        mInflater = LayoutInflater.from(context);
        mDatas=zhiHus;
        mContext=context;
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

        holder.mTextView.setText(mDatas.get(i).getTitle());


        return view;
    }




    private final class Holder {
        TextView mTextView;
    }
}
