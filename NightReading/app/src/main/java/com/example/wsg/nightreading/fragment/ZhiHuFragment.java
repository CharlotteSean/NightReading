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
import com.example.wsg.nightreading.adapter.ZhiHuAdapter;
import com.example.wsg.nightreading.entity.ZhiHu;
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
 * 文件名：ZhiHuFragment
 * 创建者：wsg
 * 创建时间：2017/9/6  19:44
 * 描述：知乎日报
 */

public class ZhiHuFragment extends Fragment {

    private ListView mlistview;
    private List<ZhiHu> mlist=new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_zhihu, null);
        findView(view);
        return view;
    }

    private void findView(View view) {
        mlistview=(ListView)view.findViewById(R.id.Zhihu_lv);

        //解析接口
        String url = "https://news-at.zhihu.com/api/4/news/latest";
        RxVolley.get(url, new HttpCallback() {
            @Override
            public void onSuccess(String t) {
                //Toast.makeText(getActivity(), t, Toast.LENGTH_SHORT).show();
                L.i("wechat json:" + t);
                parsingJson(t);
            }
        });




        mlistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                L.i("position:" + i);
                Intent intent = new Intent(getActivity(), WebViewActivity.class);
                intent.putExtra("title", mlist.get(i).getTitle());
                intent.putExtra("url", "https://news-at.zhihu.com/api/4/news/"+mlist.get(i).getId());
                startActivity(intent);

            }
        });



    }

    private void parsingJson(String t) {
        try {
            JSONObject jsonObject = new JSONObject(t);
            JSONArray jArray = jsonObject
                    .getJSONArray("stories");
            for (int i = 0; i <jArray.length() ; i++) {
                JSONObject jb1 = (JSONObject) jArray.get(i);

                String title=jb1.getString("title");
                String id=jb1.getString("id");
                String images=jb1.getString("images");


                ZhiHu z=new ZhiHu();
                z.setTitle(title);
                z.setId(id);
                z.setImages(images);


               mlist.add(z);



                L.i(z.getTitle());
                L.i(z.toString());

                L.i(z.toString());

            }


        }
        catch (JSONException e){
            e.printStackTrace();

        }

        ZhiHuAdapter adapter=new ZhiHuAdapter(getActivity(),mlist);
        mlistview.setAdapter(adapter);



    }


}
