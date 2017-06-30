package com.xuewei.mybilibili.presenter;

import com.xuewei.mybilibili.model.GetNetModel;
import com.xuewei.mybilibili.model.IGetNetModel;
import com.xuewei.mybilibili.view.IGetNetView;

/**
 * 作者：田学伟 on 2017/6/30 18:38
 * QQ：93226539
 * 作用：
 */

public class GetNetPresenter implements IGetNetPresenter {

    private IGetNetView mIGetNetView;
    private IGetNetModel mIGetNetModel;

    public GetNetPresenter(IGetNetView iGetNetView) {
        this.mIGetNetView = iGetNetView;
        mIGetNetModel = new GetNetModel(this);
    }

    @Override
    public void onSuccess(String json) {
        if (mIGetNetView.isShowView()) {
            mIGetNetView.hideLoading();
            mIGetNetView.onSuccess(json);
        }
    }

    @Override
    public void onError(String error) {
        if (mIGetNetView.isShowView()) {
            mIGetNetView.hideLoading();
            mIGetNetView.onError(error);
        }
    }

    @Override
    public void getDataFromNet() {
        if (mIGetNetView.isShowView()) {
            mIGetNetView.showLoading();
            mIGetNetModel.getDataFromNet(mIGetNetView.setUrl());
        }
    }

    @Override
    public void cancelCall() {
        mIGetNetModel.cancelCall();
    }

    @Override
    public void getDataFromNet(String url, ResultListener listener) {
        mIGetNetModel.getDataFromNet(url, listener);
    }
}

