package com.example.wutianyu.singing;

/**
 * Created by wutianyu on 2018/3/29.
 */

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class Songlist extends Fragment  {
    ListView contact_list;
    String[] names2 = {"欧若拉", "杂技", "忽然之间", "说散就散", "佛系少女", "迷魂曲", "爱的魔法", "傻笑", "体面", "差一步", "笑中有泪", "不得不爱",
            "许褚", "杨修", "张飞", "赵云", "甄姬", "周瑜", "诸葛亮"};
    String[] names1 = {"郭嘉", "黄月英", "华佗", "刘备", "陆逊", "吕布", "吕蒙", "马超", "司马懿", "孙权", "孙尚香", "夏侯惇",
            "许褚", "杨修", "张飞", "赵云", "甄姬", "周瑜", "诸葛亮"};
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.songlist_fragment,container,false);
        contact_list= (ListView) view.findViewById(R.id.contact_list);
        contact_list.setAdapter(new MyListViewAdapter(getContext()));
        return view;
    }
    class MyListViewAdapter extends BaseAdapter{
        Context context;
        public MyListViewAdapter(Context context) {
            this.context = context;
        }
        @Override
        public int getCount() {
            return names1.length;
        }
        @Override
        public Object getItem(int position) {
            return names1[position];
        }
        @Override
        public long getItemId(int position) {
            return position;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHold vh;
            if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.songlist_item, null);
                vh = new ViewHold();
                vh.iv = (TextView) convertView.findViewById(R.id.friend_iv);
                vh.tv = (TextView) convertView.findViewById(R.id.friend_tv);
                convertView.setTag(vh);
            }
            vh = (ViewHold) convertView.getTag();
            vh.iv.setText(names2[position]);
            vh.tv.setText(names1[position]);
            return convertView;
        }
        class ViewHold {
            TextView iv;
            TextView tv;
        }
    }
}
