package com.example.wsg.nightreading.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.wsg.nightreading.R;
import com.example.wsg.nightreading.adapter.DuanZiAdapter;
import com.example.wsg.nightreading.entity.DuanZi;
import com.example.wsg.nightreading.ui.WebViewActivity;
import com.example.wsg.nightreading.utils.L;
import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.client.HttpCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目名：NightReading
 * 包名：com.example.wsg.nightreading.fragment
 * 文件名：FriendFragment
 * 创建者：wsg
 * 创建时间：2017/9/6  19:54
 * 描述：TODO
 */

public class FriendFragment extends Fragment {

    private ListView listView;
    private List<DuanZi> list=new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_friend, null);
        findView(view);
        initData();

        return view;
    }



    private void findView(View view) {
        listView=(ListView)view.findViewById(R.id.Ff_lv);

    }

    private void initData() {

        //解析接口
        String url = "http://apicloud.mob.com/wx/article/search?key=20d8e016bad52&cid=37";
        RxVolley.get(url, new HttpCallback() {
            @Override
            public void onSuccess(String t) {
                //Toast.makeText(getActivity(), t, Toast.LENGTH_SHORT).show();
                L.i("wechat json:" + t);
                try {
                    parsingJson(t);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });




//        //设置适配器
//        LQRAdapterForAbsListView mAdapter=new LQRAdapterForAbsListView<DuanZi>(getActivity(),list,R.layout.item_duanzi) {
//            @Override
//            public void convert(LQRViewHolderForAbsListView helper, DuanZi item, int position) {
//                helper.setText(R.id.item_duanzi_tv,list.get(position).getSubTitle());
//            }
//        };
//        listView.setAdapter(mAdapter);
//
//        //添加点击事件
//        mAdapter.setOnItemClickListener(new OnItemClickListener() {
//            @Override
//            public void onItemClick(LQRViewHolder helper, ViewGroup parent, View itemView, int position) {
//                L.i("position:" + position);
//                Intent intent = new Intent(getActivity(), WebViewActivity.class);
//                intent.putExtra("title", list.get(position+1).getSubTitle());
//                intent.putExtra("url", list.get(position+1).getSourceUrl());
//                startActivity(intent);
//            }
//        });

        //添加点击事件

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                L.i("position:" + i);
                Intent intent = new Intent(getActivity(), WebViewActivity.class);
                intent.putExtra("title", list.get(i).getSubTitle());
                intent.putExtra("url", list.get(i).getSourceUrl());
                startActivity(intent);
            }
        });

    }

    private void parsingJson(String t) throws JSONException {
        JSONObject jsonObject = new JSONObject(t);
        JSONObject object = jsonObject.getJSONObject("result");
        JSONArray jArray = object.getJSONArray("list");

        for (int i = 0; i <jArray.length() ; i++) {
            JSONObject jb1 = (JSONObject) jArray.get(i);

            String title=jb1.getString("subTitle");
            String url=jb1.getString("sourceUrl");

            DuanZi d=new DuanZi();
            d.setSubTitle(title);
            d.setSourceUrl(url);


            L.i(d.toString());
            list.add(d);





        }


        //设置适配器
        DuanZiAdapter adapter=new DuanZiAdapter(getActivity(),list);
        listView.setAdapter(adapter);



    }
}
