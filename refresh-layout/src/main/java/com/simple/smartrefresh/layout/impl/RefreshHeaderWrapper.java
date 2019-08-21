package com.simple.smartrefresh.layout.impl;

import android.annotation.SuppressLint;
import android.view.View;

import com.simple.smartrefresh.layout.api.RefreshHeader;
import com.simple.smartrefresh.layout.internal.InternalAbstract;

/**
 * 刷新头部包装
 * Created by scwang on 2017/5/26.
 */
@SuppressLint("ViewConstructor")
public class RefreshHeaderWrapper extends InternalAbstract implements RefreshHeader/*, InvocationHandler*/ {

    public RefreshHeaderWrapper(View wrapper) {
        super(wrapper);
    }

}
