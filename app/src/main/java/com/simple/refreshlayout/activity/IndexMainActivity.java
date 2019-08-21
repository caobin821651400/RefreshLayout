package com.simple.refreshlayout.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.simple.refreshlayout.R;
import com.simple.refreshlayout.adapter.BaseRecyclerAdapter;
import com.simple.refreshlayout.adapter.SmartViewHolder;
import com.simple.smartrefresh.layout.api.RefreshLayout;
import com.simple.smartrefresh.layout.listener.OnLoadMoreListener;
import com.simple.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static android.support.v7.widget.DividerItemDecoration.VERTICAL;

public class IndexMainActivity extends AppCompatActivity {
    private BaseRecyclerAdapter<Void> mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index_main);
        final Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        if (recyclerView != null) {
            recyclerView.setNestedScrollingEnabled(false);
            recyclerView.addItemDecoration(new DividerItemDecoration(this, VERTICAL));
            recyclerView.setAdapter(mAdapter = new BaseRecyclerAdapter<Void>(android.R.layout.simple_list_item_2) {
                @Override
                protected void onBindViewHolder(SmartViewHolder holder, Void model, int position) {
                    holder.text(android.R.id.text1, getString(R.string.item_example_number_title, position));
                    holder.text(android.R.id.text2, getString(R.string.item_example_number_abstract, position));
                    holder.textColorId(android.R.id.text2, R.color.colorTextAssistant);
                }
            });
        }

        RefreshLayout refreshLayout = findViewById(R.id.refreshLayout);
        if (refreshLayout != null) {
            refreshLayout.autoRefresh();
            refreshLayout.setEnableLoadMoreWhenContentNotFull(false);
            refreshLayout.setOnRefreshListener(new OnRefreshListener() {
                @Override
                public void onRefresh(@NonNull final RefreshLayout refreshLayout) {
                    refreshLayout.getLayout().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Collection<Void> data = loadData();
                            mAdapter.refresh(data);
                            if (data.size() < 9) {
                                refreshLayout.finishRefreshWithNoMoreData();
                            } else {
                                refreshLayout.finishRefresh();
                            }
                        }
                    }, 1000);
                }
            });
            refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
                @Override
                public void onLoadMore(@NonNull final RefreshLayout refreshLayout) {
                    refreshLayout.getLayout().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Collection<Void> list = loadData();
                            mAdapter.loadMore(list);
                            if (list.size() < 10) {
                                refreshLayout.finishLoadMoreWithNoMoreData();
                            } else {
                                refreshLayout.finishLoadMore();
                            }
                        }
                    }, 1000);
                }
            });
        }

    }


    private Collection<Void> loadData() {
        List<Void> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add(null);
        }
        return list;
    }

}
