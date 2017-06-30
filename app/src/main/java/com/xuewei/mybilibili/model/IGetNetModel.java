package com.xuewei.mybilibili.model;

import com.xuewei.mybilibili.presenter.ResultListener;

/**
 * 作者：田学伟 on 2017/6/30 18:39
 * QQ：93226539
 * 作用：
 */

public interface IGetNetModel {

    void getDataFromNet(String url);

    void cancelCall();

    void getDataFromNet(String url, ResultListener listener);

}
