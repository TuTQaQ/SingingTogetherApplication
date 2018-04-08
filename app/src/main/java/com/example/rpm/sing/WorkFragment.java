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
        View view = inflater.inflate(R.layout.fragment_work, container, false);

        initWorkList();
        WorkListAdapter adapter = new WorkListAdapter(getContext(), R.layout.work_item, workSongList);
        listView = view.findViewById(R.id.work_list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
<<<<<<< HEAD
                Intent intent = new Intent(getContext(), PlayActivity.class);
||||||| merged common ancestors
                Intent intent=new Intent(getContext(),PlayActivity.class);
=======
                Intent intent=new Intent(getContext(),PlayActivity.class);
                intent.putExtra("workSongList",new Gson().toJson(workSongList));
                intent.putExtra("position",i);
>>>>>>> 68928087bf5de52923106228c77d8f03f89240ae
                startActivity(intent);
            }
        });
        return view;
    }

<<<<<<< HEAD
    private void initWorkList() {
        workSongList = new ArrayList<WorkSong>();
        WorkSong workSong1 = new WorkSong("name1", "date1");
        workSongList.add(workSong1);
        WorkSong workSong2 = new WorkSong("name2", "date2");
        workSongList.add(workSong2);
        WorkSong workSong3 = new WorkSong("name3", "date3");
        workSongList.add(workSong3);
        WorkSong workSong4 = new WorkSong("name4", "date4");
        workSongList.add(workSong4);
        WorkSong workSong5 = new WorkSong("name5", "date5");
        workSongList.add(workSong5);
        WorkSong workSong6 = new WorkSong("name6", "date6");
        workSongList.add(workSong6);
        WorkSong workSong7 = new WorkSong("name7", "date7");
        workSongList.add(workSong7);
        WorkSong workSong8 = new WorkSong("name8", "date8");
        workSongList.add(workSong8);
        WorkSong workSong9 = new WorkSong("name9", "date9");
        workSongList.add(workSong9);
        WorkSong workSong10 = new WorkSong("name10", "date10");
        workSongList.add(workSong10);
||||||| merged common ancestors
    private void initWorkList(){
        workSongList=new ArrayList<WorkSong>();
        WorkSong workSong1=new WorkSong("name1","date1");
        workSongList.add(workSong1);
        WorkSong workSong2=new WorkSong("name2","date2");
        workSongList.add(workSong2);
        WorkSong workSong3=new WorkSong("name3","date3");
        workSongList.add(workSong3);
        WorkSong workSong4=new WorkSong("name4","date4");
        workSongList.add(workSong4);
        WorkSong workSong5=new WorkSong("name5","date5");
        workSongList.add(workSong5);
        WorkSong workSong6=new WorkSong("name6","date6");
        workSongList.add(workSong6);
        WorkSong workSong7=new WorkSong("name7","date7");
        workSongList.add(workSong7);
        WorkSong workSong8=new WorkSong("name8","date8");
        workSongList.add(workSong8);
        WorkSong workSong9=new WorkSong("name9","date9");
        workSongList.add(workSong9);
        WorkSong workSong10=new WorkSong("name10","date10");
        workSongList.add(workSong10);
=======
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

>>>>>>> 68928087bf5de52923106228c77d8f03f89240ae
    }

}
