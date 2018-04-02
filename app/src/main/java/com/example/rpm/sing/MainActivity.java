package com.example.rpm.sing;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationBar bottomNavigationBar;
    private List<Fragment> fragmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initBottomBar();
        initFragment();
        replaceFragment(0);
    }

    private void initBottomBar() {
        bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);

        bottomNavigationBar.addItem(new BottomNavigationItem(R.drawable.list, "SongList"))
                .addItem(new BottomNavigationItem(R.drawable.music, "Work"))
                //.addItem(new BottomNavigationItem(R.drawable.mine, "Mine"))
                .initialise();

        bottomNavigationBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
                replaceFragment(position);
            }

            @Override
            public void onTabUnselected(int position) {
            }

            @Override
            public void onTabReselected(int position) {
            }
        });
    }

    private void initFragment() {
        fragmentList = new ArrayList<Fragment>();
        fragmentList.add(new SongFragment());
        fragmentList.add(new WorkFragment());
        fragmentList.add(new MyFragment());
    }

    private void replaceFragment(int position) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.main_framelayout, fragmentList.get(position));
        transaction.commit();
    }
}
