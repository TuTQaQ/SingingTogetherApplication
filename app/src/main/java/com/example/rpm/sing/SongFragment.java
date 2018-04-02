package com.example.rpm.sing;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class SongFragment extends Fragment {

    private ListView listView;

    private List<RecordSong> recordSongList = new ArrayList<RecordSong>();

    public SongFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
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
                startActivity(intent);
            }
        });
        return view;
    }

    private void initRecordSong() {
        RecordSong recordSong1 = new RecordSong("name1", "singer1", "5");
        recordSongList.add(recordSong1);
        RecordSong recordSong2 = new RecordSong("name2", "singer2", "5");
        recordSongList.add(recordSong2);
        RecordSong recordSong3 = new RecordSong("name3", "singer3", "5");
        recordSongList.add(recordSong3);
        RecordSong recordSong4 = new RecordSong("name4", "singer4", "5");
        recordSongList.add(recordSong4);
        RecordSong recordSong5 = new RecordSong("name5", "singer5", "5");
        recordSongList.add(recordSong5);
        RecordSong recordSong6 = new RecordSong("name6", "singer6", "5");
        recordSongList.add(recordSong6);
        RecordSong recordSong7 = new RecordSong("name7", "singer7", "5");
        recordSongList.add(recordSong7);
        RecordSong recordSong8 = new RecordSong("name8", "singer8", "5");
        recordSongList.add(recordSong8);
        RecordSong recordSong9 = new RecordSong("name9", "singer9", "5");
        recordSongList.add(recordSong9);
        RecordSong recordSong10 = new RecordSong("name10", "singer10", "5");
        recordSongList.add(recordSong10);
    }

}
