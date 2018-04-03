package com.example.rpm.sing;


import android.content.Context;
import android.icu.text.AlphabeticIndex;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by RPM on 2018/4/1.
 */

public class RecordListAdapter extends ArrayAdapter<RecordSong> {

    private final int resourceId;

    public RecordListAdapter(Context context, int textViewResourceId, List<RecordSong> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        RecordSong recordSong = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId, null);

        TextView recordName =  view.findViewById(R.id.record_name);
        TextView recordSinger =  view.findViewById(R.id.record_singer);
        TextView recordDifficult = view.findViewById(R.id.record_difficult);


        recordName.setText(recordSong.getSongName());
        recordSinger.setText(recordSong.getSongSinger());
        recordDifficult.setText(recordSong.getSongDifficult());

        return view;
    }
}
