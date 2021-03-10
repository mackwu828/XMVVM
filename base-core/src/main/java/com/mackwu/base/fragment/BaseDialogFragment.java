package com.mackwu.base.fragment;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.lifecycle.ViewModelProvider;

import com.mackwu.base.viewmodel.BaseViewModel;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * ===================================================
 * Created by MackWu on 2020/7/31 15:49
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public abstract class BaseDialogFragment<VM extends BaseViewModel> extends AppCompatDialogFragment implements IDialogFragment {

    protected VM viewModel;
    private Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        return inflater.inflate(getLayoutId(), container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initUnBinder(view);
        initViewModel();
        initDialog();
        initView(view, savedInstanceState);
        initData(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog == null) return;
        Window window = dialog.getWindow();
        if (window == null) return;
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        // layoutParams
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        if (getWidth() > 0) {
            layoutParams.width = getWidth();
        } else {
            layoutParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
        }
        if (getHeight() > 0) {
            layoutParams.height = getHeight();
        } else {
            layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        }
        layoutParams.dimAmount = getDimAmount();
        layoutParams.gravity = getGravity();
        window.setAttributes(layoutParams);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (unbinder != null && unbinder != Unbinder.EMPTY) {
            unbinder.unbind();
            unbinder = null;
        }
    }

    private void initUnBinder(View view) {
        unbinder = ButterKnife.bind(this, view);
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

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }

    private void initDialog() {
        Dialog dialog = getDialog();
        if (dialog == null) return;
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(isCancelable());
        dialog.setCanceledOnTouchOutside(isCanceledOnTouchOutside());
    }

    @Override
    public int getDimAmount() {
        return 0;
    }

    @Override
    public int getGravity() {
        return Gravity.CENTER;
    }

    @Override
    public boolean isCancelable() {
        return true;
    }

    @Override
    public boolean isCanceledOnTouchOutside() {
        return true;
    }

}
