package com.xuewei.mybilibili.base;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xuewei.mybilibili.presenter.GetNetPresenter;
import com.xuewei.mybilibili.presenter.ResultListener;
import com.xuewei.mybilibili.view.IGetNetView;

import butterknife.ButterKnife;

/**
 * 作者：田学伟 on 2017/6/30 18:40
 * QQ：93226539
 * 作用：
 */

public abstract class BaseFragment extends Fragment implements IGetNetView {

    protected Context mContext;
    private boolean isShow;
    private GetNetPresenter mGetNetPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (setLayoutId() == 0) {
            TextView textView = new TextView(mContext);
            textView.setText("还没有创建布局");
            textView.setTextSize(25);
            textView.setTextColor(Color.RED);
            textView.setGravity(Gravity.CENTER);
            return textView;
        }
        View view = inflater.inflate(setLayoutId(), null);
        ButterKnife.bind(this, view);
        isShow = true;
        mGetNetPresenter = new GetNetPresenter(this);
        return view;
    }

    protected abstract void initListener();

    @LayoutRes
    protected abstract int setLayoutId();

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initNetData();
        initListener();
    }

    protected abstract void initView();

    public void refresh() {
        initNetData();
    }

    private void initNetData() {
        if (TextUtils.isEmpty(setUrl())) {
            initData(null, "url为空无法请求数据");
            return;
        }
        mGetNetPresenter.getDataFromNet();
    }

    protected void getDataFromNet(String url, ResultListener listener) {
        mGetNetPresenter.getDataFromNet(url, listener);
    }

    @Override
    public final void onSuccess(String json) {
        initData(json, null);
    }

    @Override
    public final void onError(String error) {
        initData(null, error);
    }

    @Override
    public abstract void showLoading();

    @Override
    public abstract void hideLoading();

    public abstract String setUrl();

    @Override
    public final boolean isShowView() {
        return isShow;
    }

    public void startActivity(Class clazz) {
        startActivity(new Intent(mContext, clazz));
    }

    protected abstract void initData(String json, String error);

//    @Override
//    public void onDestroyView() {
//        isShow = false;
//        mGetNetPresenter.cancelCall();
//        ButterKnife.unbind(this);
//        super.onDestroyView();
//    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}

