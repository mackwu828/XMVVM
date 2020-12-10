package com.mackwu.mvvm.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public interface IFragment {

    /**
     * 布局id
     */
    @LayoutRes
    int getLayoutId();

    /**
     * 初始化view
     */
    void initView(@NonNull View view, @Nullable Bundle savedInstanceState);

    /**
     * 初始化数据
     */
    void initData(@Nullable Bundle savedInstanceState);
}
