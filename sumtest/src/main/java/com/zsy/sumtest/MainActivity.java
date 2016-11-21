package com.zsy.sumtest;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.gson.Gson;
import com.zsy.sum.app.BaseActivity;
import com.zsy.sum.ui.view.NFlipVp;
import com.zsy.sum.utils.depend.Lg;
import com.zsy.sumtest.model.Info;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity {

    private final String[] mTestArr = new String[]{"Sqlite", "Sqlite", "Sqlite"};

    private List<String> mTests;
    private TestRvAdapter testRvAdapter;

    @BindView(R.id.rv_test) RecyclerView testRv;

    @Override
    public void setLayout() {
        setContentView(R.layout.activity_main);
    }

    @Override
    public void initData() {
        super.initData();
        mTests = Arrays.asList(mTestArr);
        testRvAdapter = new TestRvAdapter(mTests);
    }

    @Override
    public void initView() {
        super.initView();
        testRv.setLayoutManager(new LinearLayoutManager(this));
        testRv.setAdapter(testRvAdapter);
    }

    @Override
    public void initEvent() {
        super.initEvent();
        testRvAdapter.setOnItemClickLsn(new TestRvAdapter.OnItemClickLsn() {
            @Override
            public void onItemClick(View v, int position) {
                Lg.v(NFlipVp.class.getName());
            }
        });
    }
}
