package com.example.rpm.sing;


import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class SongFragment extends Fragment {

    private ListView listView;

    private List<RecordSong> recordSongList;

    public SongFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_song, container, false);

        initRecordSong();

        RecordListAdapter adapter = new RecordListAdapter(getContext(), R.layout.record_item, recordSongList);
        listView = (ListView) view.findViewById(R.id.song_list);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(getContext(),RecordActivity.class);
                intent.putExtra("recordSongList",new Gson().toJson(recordSongList));
                intent.putExtra("position",i);
                startActivity(intent);
            }
        });
        return view;
    }

    private void initRecordSong() {
        recordSongList=new ArrayList<RecordSong>();
        AssetManager assetManager=getContext().getAssets();
        String[] files=null;
        RecordSong recordSong;
        List<String>strings=new ArrayList<String>();
        try {
            files=assetManager.list("song");
        }catch (IOException e){
            e.printStackTrace();
        }
        for (int i=0;i<files.length;i++){
            strings.add(files[i]);
        }
        for (int i=0;i<strings.size();i++){
            String substring=strings.get(i).substring(0,strings.get(i).length()-4);
            String[] songInfo=substring.split("_");
            if (songInfo.length==1){
                break;
            }
            recordSong=new RecordSong(songInfo[0],songInfo[1],"5"
                    ,strings.get(i),songInfo[0]+".txt");
            recordSongList.add(recordSong);
        }
    }

}
