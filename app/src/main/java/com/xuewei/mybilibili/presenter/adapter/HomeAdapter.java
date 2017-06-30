package com.xuewei.mybilibili.presenter.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * 作者：田学伟 on 2017/6/30 18:58
 * QQ：93226539
 * 作用：
 */

public class HomeAdapter extends FragmentPagerAdapter {
    private  List<Fragment> fragments;
    String[] titles = {"直播","推荐","追番","分区","发现"};

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

    public HomeAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
