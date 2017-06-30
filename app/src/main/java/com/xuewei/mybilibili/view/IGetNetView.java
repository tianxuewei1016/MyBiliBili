package com.xuewei.mybilibili.view;

/**
 * 作者：田学伟 on 2017/6/30 18:37
 * QQ：93226539
 * 作用：
 */

public interface IGetNetView {
    void onSuccess(String json);

    void onError(String error);

    void showLoading();

    void hideLoading();

    String setUrl();

    boolean isShowView();

}
