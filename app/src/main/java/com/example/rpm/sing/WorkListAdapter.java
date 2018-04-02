package com.example.rpm.sing;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by RPM on 2018/4/1.
 */

public class WorkListAdapter extends ArrayAdapter<WorkSong> {
    private final int resourceId;

    public WorkListAdapter(Context context, int textViewResourceId, List<WorkSong> objects){
        super(context,textViewResourceId,objects);
        resourceId=textViewResourceId;
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        WorkSong workSong=(WorkSong)getItem(position);
        View view= LayoutInflater.from(getContext()).inflate(resourceId,null);

        TextView workName=(TextView)view.findViewById(R.id.work_name);
        TextView wirkDate=(TextView)view.findViewById(R.id.work_date);

        workName.setText(workSong.getSongName());
        wirkDate.setText(workSong.getSongRecordDate());

        return view;
    }
}
