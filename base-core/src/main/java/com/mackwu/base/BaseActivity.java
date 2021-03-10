package com.mackwu.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewbinding.ViewBinding;

import com.mackwu.base.util.ActivityStackUtil;
import com.mackwu.base.util.LogUtil;
import com.mackwu.base.viewmodel.BaseViewModel;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * ===================================================
 * Created by MackWu on 2020/6/19 23:26
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public abstract class BaseActivity<VM extends BaseViewModel, B extends ViewBinding> extends AppCompatActivity implements IActivity<B> {

    protected VM viewModel;
    protected B binding;

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtil.d("What activity is created? " + this.getClass().getSimpleName());
        initViewModel();
        initViewBinding();
        ActivityStackUtil.addActivity(this);
        initView(savedInstanceState);
        initData(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityStackUtil.removeActivity(this);
    }

    @SuppressWarnings("unchecked")
    private void initViewModel() {
        Class<VM> vmCls = (Class<VM>) getActualTypeArgument(getClass());
        viewModel = new ViewModelProvider(this).get(vmCls != null ? vmCls : (Class<VM>) BaseViewModel.class);
        getLifecycle().addObserver(viewModel);
    }

    private void initViewBinding() {
        binding = getViewBinding();
        setContentView(binding.getRoot());
    }

    private Class<?> getActualTypeArgument(Class<?> cls) {
        Class<?> actualTypeArgument = null;
        Type genericSuperclass = cls.getGenericSuperclass();
        if (genericSuperclass instanceof ParameterizedType) {
            Type[] actualTypeArguments = ((ParameterizedType) genericSuperclass).getActualTypeArguments();
            if (actualTypeArguments.length > 0) {
                actualTypeArgument = (Class<?>) actualTypeArguments[0];
            }
        }
        return actualTypeArgument;
    }

    @Override
    public int getLayoutId() {
        return 0;
    }
}
