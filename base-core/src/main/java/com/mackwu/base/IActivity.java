package com.mackwu.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;

/**
 * ===================================================
 * Created by MackWu on 2020/7/8 15:59
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public interface IActivity<B extends ViewBinding> {

    /**
     * 获取布局id
     */
    int getLayoutId();

    /**
     * 获取视图绑定
     * @return 视图绑定
     */
    B getViewBinding();

    /**
     * 初始化view
     */
    void initView(@Nullable Bundle savedInstanceState);

    /**
     * 初始化数据
     */
    void initData(@Nullable Bundle savedInstanceState);

}
