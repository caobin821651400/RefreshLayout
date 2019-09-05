package com.simple.refreshlayout;

import android.app.Application;
import android.content.Context;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDelegate;

import com.simple.refreshlayout.util.DynamicTimeFormat;
import com.simple.smartrefresh.layout.SmartRefreshLayout;
import com.simple.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.simple.smartrefresh.layout.api.DefaultRefreshInitializer;
import com.simple.smartrefresh.layout.api.RefreshHeader;
import com.simple.smartrefresh.layout.api.RefreshLayout;
import com.simple.smartrefresh.layout.header.ClassicsHeader;

/**
 *
 * Created by scwang on 2017/6/11.
 */
public class App extends Application {

    static {
        //启用矢量图兼容
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        //设置全局默认配置（优先级最低，会被其他设置覆盖）
        SmartRefreshLayout.setDefaultRefreshInitializer(new DefaultRefreshInitializer() {
            @Override
            public void initialize(@NonNull Context context, @NonNull RefreshLayout layout) {
                //全局设置（优先级最低）
                layout.setEnableAutoLoadMore(true);
                layout.setEnableOverScrollDrag(false);
                layout.setEnableOverScrollBounce(true);
                layout.setEnableLoadMoreWhenContentNotFull(true);
                layout.setEnableScrollContentWhenRefreshed(true);
            }
        });
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @NonNull
            @Override
            public RefreshHeader createRefreshHeader(@NonNull Context context, @NonNull RefreshLayout layout) {
                //全局设置主题颜色（优先级第二低，可以覆盖 DefaultRefreshInitializer 的配置，与下面的ClassicsHeader绑定）
                return new ClassicsHeader(context).setAccentColorId(android.R.color.black).setTimeFormat(new DynamicTimeFormat("更新于 %s"));
            }
        });
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
