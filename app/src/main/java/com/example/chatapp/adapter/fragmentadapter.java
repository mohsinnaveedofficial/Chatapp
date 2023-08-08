package com.example.chatapp.adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;



public class fragmentadapter extends FragmentPagerAdapter {

    private final List<Fragment>fragmentList=new ArrayList<>();
private final List<String> titlefragmentlist=new ArrayList<>();
Context con;

    public fragmentadapter(@NonNull FragmentManager fm,Context con) {
        super(fm);
        this.con=con;

    }



    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    public void addfragment(Fragment fra , String title)
    {
        fragmentList.add(fra);
        titlefragmentlist.add(title);
    }


    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titlefragmentlist.get(position);
    }
}
