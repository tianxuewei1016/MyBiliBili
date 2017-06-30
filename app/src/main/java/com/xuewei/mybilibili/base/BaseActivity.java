package com.xuewei.mybilibili.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import com.xuewei.mybilibili.presenter.GetNetPresenter;
import com.xuewei.mybilibili.presenter.ResultListener;
import com.xuewei.mybilibili.view.IGetNetView;

import butterknife.ButterKnife;

/**
 * 作者：田学伟 on 2017/6/30 18:37
 * QQ：93226539
 * 作用：
 */

public  abstract class BaseActivity extends AppCompatActivity implements IGetNetView {

    private boolean isShow;
    protected GetNetPresenter mGetNetPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayoutId());
        ButterKnife.bind(this);
        mGetNetPresenter = new GetNetPresenter(this);
        isShow = true;
        //初始化
        initView();
        initDataNet();
        initListener();
    }

    private void initDataNet() {
        if (TextUtils.isEmpty(setUrl())) {
            initData(null, "url为空无法请求数据");
            return;
        }
        mGetNetPresenter.getDataFromNet();
    }
    protected void getDataFromNet(String url, ResultListener listener){
        mGetNetPresenter.getDataFromNet(url,listener);
    }
    @Override
    protected void onDestroy() {
        isShow = false;
        mGetNetPresenter.cancelCall();
        ButterKnife.unbind(this);
        super.onDestroy();
    }

    protected void refresh() {
        initDataNet();
    }

    public abstract String setUrl();

    @Override
    public final boolean isShowView() {
        return isShow;
    }

    protected abstract void initListener();

    protected abstract void initView();

    protected abstract void initData(String json, String error);

    @LayoutRes
    protected abstract int setLayoutId();


    protected void startActivity(Class activityClazz) {
        startActivity(new Intent(this, activityClazz));
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
}

