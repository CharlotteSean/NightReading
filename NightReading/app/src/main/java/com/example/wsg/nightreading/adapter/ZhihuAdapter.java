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
 * 文件名：ZhihuAdapter
 * 创建者：wsg
 * 创建时间：2017/9/8  20:34
 * 描述：TODO
 */

public class ZhihuAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private List<ZhiHu> mDatas;
    private ZhiHu data;


    public ZhihuAdapter(Context context, List<ZhiHu> datas) {

        mInflater = LayoutInflater.from(context);
        mDatas = datas;
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

        ViewHolder viewHolder = null;
        if (view == null) {
            viewHolder = new ViewHolder();
            view = mInflater.inflate(R.layout.item_zhihu, null);
            viewHolder.tv_title = (TextView) view.findViewById(R.id.item_zhihu_tv);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        data = mDatas.get(i);

        viewHolder.tv_title.setText(data.getTitle());



        return view;
    }

    class ViewHolder {
        private TextView tv_title;
    }
}



