package com.mackwu.app;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.mackwu.xmvvm.BaseActivity;

public class LoginActivity extends BaseActivity<LoginViewModel> {

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        viewModel.login();
    }

}
