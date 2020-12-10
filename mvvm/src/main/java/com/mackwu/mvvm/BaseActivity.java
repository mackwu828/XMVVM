package com.mackwu.mvvm;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.mackwu.mvvm.util.ActivityStackUtil;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * ===================================================
 * Created by MackWu on 2020/6/19 23:26
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public abstract class BaseActivity<VM extends BaseViewModel> extends AppCompatActivity implements IActivity {

    protected VM viewModel;
    private Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("TAG", "What activity is created? " + this.getClass().getSimpleName());
        setContentView(getLayoutId());
        ActivityStackUtil.addActivity(this);
        initUnBinder();
        initViewModel();
        initView(savedInstanceState);
        initData(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unbinder != null && unbinder != Unbinder.EMPTY) {
            unbinder.unbind();
            unbinder = null;
        }
        ActivityStackUtil.finishActivity(this);
    }

    private void initUnBinder() {
        unbinder = ButterKnife.bind(this);
    }

    @SuppressWarnings("unchecked")
    private void initViewModel() {
        Class<VM> vmCls = (Class<VM>) getActualTypeArgument(getClass());
        viewModel = new ViewModelProvider(this).get(vmCls != null ? vmCls : (Class<VM>) BaseViewModel.class);
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

}
