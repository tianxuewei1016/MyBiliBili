package com.xuewei.mybilibili.view.activity;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import com.wyt.searchbox.SearchFragment;
import com.xuewei.mybilibili.R;
import com.xuewei.mybilibili.base.BaseActivity;
import com.xuewei.mybilibili.presenter.adapter.HomeAdapter;
import com.xuewei.mybilibili.view.fragment.CartoonFragment;
import com.xuewei.mybilibili.view.fragment.DiscoverFragment;
import com.xuewei.mybilibili.view.fragment.LiveFragment;
import com.xuewei.mybilibili.view.fragment.PartitionFragment;
import com.xuewei.mybilibili.view.fragment.RecommendFragment;
import com.xuewei.mybilibili.view.view.CircleImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

public class MainActivity extends BaseActivity {

    @Bind(R.id.toolbar_user_avatar)
    CircleImageView toolbarUserAvatar;
    @Bind(R.id.navigation_layout)
    LinearLayout navigationLayout;
    @Bind(R.id.toolBar)
    Toolbar toolBar;
    @Bind(R.id.tabLayout)
    TabLayout tabLayout;
    @Bind(R.id.appbar)
    AppBarLayout appbar;
    @Bind(R.id.viewpager)
    ViewPager viewpager;
    @Bind(R.id.coordinatorlayout)
    CoordinatorLayout coordinatorlayout;
    @Bind(R.id.navigation)
    NavigationView navigation;
    @Bind(R.id.drawerlayout)
    DrawerLayout drawerlayout;

    List<Fragment> fragments;
    private HomeAdapter homeAdapter;
    private SearchFragment searchFragment;

    @Override
    public String setUrl() {
        return null;
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initView() {
        toolBar.inflateMenu(R.menu.menu_toolbar);
        searchFragment = SearchFragment.newInstance();
    }

    @Override
    protected void initData(String json, String error) {
        fragments = new ArrayList<>();
        fragments.add(new LiveFragment());
        fragments.add(new RecommendFragment());
        fragments.add(new CartoonFragment());
        fragments.add(new PartitionFragment());
        fragments.add(new DiscoverFragment());

        homeAdapter = new HomeAdapter(getSupportFragmentManager(), fragments);
        viewpager.setAdapter(homeAdapter);
        tabLayout.setupWithViewPager(viewpager);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    private int startY;
    private int startX;
    private boolean isScrollY;
    private boolean isFirst;
    private boolean isOpen = true;

    //tollBar 回弹效果
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        int eventY = (int) ev.getY();
        int eventX = (int) ev.getX();
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startY = eventY;
                startX = eventX;
                isFirst = true;
                break;
            case MotionEvent.ACTION_MOVE:
                if (isFirst) {
                    if (Math.abs(eventX - startX) > Math.abs(eventY - startY) && Math.abs(eventX - startX) > toolBar.getHeight() * 0.30) {
                        isScrollY = false;
                        isFirst = false;
                        appbar.setExpanded(isOpen);
                    } else if (Math.abs(eventY - startY) > Math.abs(eventX - startX) && Math.abs(eventY - startY) > toolBar.getHeight() * 0.30) {
                        isScrollY = true;
                        isFirst = false;
                    }
                }
                if (isOpen) {
                    if (startY < eventY) {
                        startY = eventY;
                    }
                } else {
                    if (startY > eventY) {
                        startY = eventY;
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                if (isScrollY) {
                    if (isOpen) {
                        if (startY - eventY > toolBar.getHeight() * 0.36) {
                            appbar.setExpanded(false);
                            isOpen = false;
                        } else {
                            appbar.setExpanded(true);
                            isOpen = true;
                        }
                    } else {
                        if (eventY - startY > toolBar.getHeight() * 0.36) {
                            appbar.setExpanded(true);
                            isOpen = true;
                        } else {
                            appbar.setExpanded(false);
                            isOpen = false;
                        }
                    }
                }
                break;
        }
        return super.dispatchTouchEvent(ev);
    }
}
