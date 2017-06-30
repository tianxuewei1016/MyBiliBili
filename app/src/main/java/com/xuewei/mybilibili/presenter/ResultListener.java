package com.xuewei.mybilibili.presenter;

/**
 * 作者：田学伟 on 2017/6/30 18:38
 * QQ：93226539
 * 作用：需要请求多个网址的回调接口
 */

public interface ResultListener {
    //成功
    void onSuccess(String json);
    //失败
    void onError(String error);
}
