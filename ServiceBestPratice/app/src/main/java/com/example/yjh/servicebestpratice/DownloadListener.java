package com.example.yjh.servicebestpratice;

public interface DownloadListener {
    //显示进度
    void onPregress(int progress);
    //成功下载
    void onSuccess();
    //下载失败
    void onFailed();
    //暂停下载
    void onPaused();
    //取消下载
    void onCancled();
}
