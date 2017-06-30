package com.xuewei.mybilibili.presenter;

/**
 * 作者：田学伟 on 2017/6/30 18:38
 * QQ：93226539
 * 作用：
 */

public interface IGetNetPresenter {

    void onSuccess(String json);

    void onError(String error);

    void getDataFromNet();

    void cancelCall();

    void getDataFromNet(String url,ResultListener listener);

}
