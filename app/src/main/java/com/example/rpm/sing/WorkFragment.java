package com.example.rpm.sing;


import android.content.Intent;
import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class WorkFragment extends Fragment {

    private ListView listView;

    private List<WorkSong> workSongList;

    public WorkFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_work, container, false);

        initWorkList();
        WorkListAdapter adapter = new WorkListAdapter(getContext(), R.layout.work_item, workSongList);
        listView = (ListView) view.findViewById(R.id.work_list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(getContext(),PlayActivity.class);
                intent.putExtra("workSongList",new Gson().toJson(workSongList));
                intent.putExtra("position",i);
                startActivity(intent);
            }
        });
        return view;
    }

    private void initWorkList(){
        workSongList=new ArrayList<WorkSong>();
        String[] workStrings=getContext().getExternalFilesDir("").list();

        for (int i=0;i<workStrings.length;i++){
            String eachstring=workStrings[i];
            eachstring=eachstring.substring(0,eachstring.length()-4);
            String[] nameAndDate=eachstring.split("_");
            WorkSong workSong=new WorkSong(nameAndDate[0],nameAndDate[1]);
            workSongList.add(workSong);
        }

    }

}
