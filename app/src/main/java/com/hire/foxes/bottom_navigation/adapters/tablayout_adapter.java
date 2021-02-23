package com.hire.foxes.bottom_navigation.adapters;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class tablayout_adapter extends FragmentPagerAdapter {

    private Context myContext;
    int totalTabs;

    public tablayout_adapter(Context context, @NonNull FragmentManager fm, int tabCount) {
        super(fm);
        myContext = context;
        totalTabs = tabCount;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
//        switch (position) {
//            case 0:
//                home homeFragment = new home();
//                return homeFragment;
//            case 1:
//                home sportFragment = new home();
//                return sportFragment;
//            case 2:
//                home movieFragment = new home();
//                return movieFragment;
//            default:
//                return null;
//        }
        return null;
    }

    @Override
    public int getCount() {
        return totalTabs;
    }
}
