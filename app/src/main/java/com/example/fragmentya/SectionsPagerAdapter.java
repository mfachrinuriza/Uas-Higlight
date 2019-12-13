package com.example.fragmentya;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    //fragment list for keeping track of the list fragment
    private final List<Fragment> mFragmentList = new ArrayList<>();
    //fragment title for keeping track of the name of fragment
    private final List<String> mFragmentTitleList = new ArrayList<>();

    //default constructor
    public SectionsPagerAdapter(FragmentManager supportFragmentManager) {
        super(supportFragmentManager);
    }

    //add fragment to fragment list
    public void addFragment(Fragment fragment, String title){
        mFragmentList.add(fragment);
        mFragmentTitleList.add(title);
    }

    //return page title
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        //returning page title
        return mFragmentTitleList.get(position);
    }

    //return actual fragment
    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }
}