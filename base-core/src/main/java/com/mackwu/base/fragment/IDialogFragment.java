package com.mackwu.base.fragment;

/**
 * ===================================================
 * Created by MackWu on 2020/7/31 17:23
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public interface IDialogFragment extends IFragment{

    /**
     * 宽
     */
    int getWidth();

    /**
     * 高
     */
    int getHeight();

    /**
     * 透明度
     */
    int getDimAmount();

    /**
     * 权重
     */
    int getGravity();

    /**
     * 是否可以取消
     */
    boolean isCanceledOnTouchOutside();
}
