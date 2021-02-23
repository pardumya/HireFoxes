package com.hire.foxes.bottom_navigation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.hire.foxes.R;
import com.hire.foxes.bottom_navigation.chat_fragments.chat;
import com.hire.foxes.bottom_navigation.home_fragments.home;
import com.hire.foxes.bottom_navigation.profile_fragments.account;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

public class bottom_navigation extends Fragment {

    ChipNavigationBar chipNavigationBar;

    public bottom_navigation() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_bottom_navigation, container, false);
        chipNavigationBar = layout.findViewById(R.id.navigation);


        if (savedInstanceState == null) {
            chipNavigationBar.setItemSelected(R.id.home, true);
            FragmentManager fragmentManager = getFragmentManager();
            home home = new home();
            assert fragmentManager != null;
            fragmentManager.beginTransaction()
                    .replace(R.id.container, home)
                    .commit();
        }
        chipNavigationBar.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int i) {
                Fragment fragment;
                switch (i) {
                    case R.id.home:
                        fragment = new home();
                        openFragment(fragment);
                        break;
                    case R.id.chat:
                        fragment = new chat();
                        openFragment(fragment);
                        break;
                    case R.id.account:
                        fragment = new account();
                        openFragment(fragment);
                        break;
                }
            }
        });
        return layout;
    }

    public void openFragment(Fragment fragment) {
        assert getFragmentManager() != null;
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}