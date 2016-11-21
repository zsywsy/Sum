package com.zsy.sumtest;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zsy.sum.utils.depend.Lg;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by mzs on 2016/11/16.
 */

public class TestRvAdapter extends RecyclerView.Adapter<TestRvAdapter.VH> {

    private List<String> mTests;

    private OnItemClickLsn onItemClickLsn;

    public TestRvAdapter(List<String> tests) {
        mTests = tests;
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_test, parent, false);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        holder.rvTestTv.setText(mTests.get(position));
    }

    @Override
    public int getItemCount() {
        return mTests.size();
    }

    public void setOnItemClickLsn(OnItemClickLsn onItemClickLsn) {
        this.onItemClickLsn = onItemClickLsn;
    }

    class VH extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_rv_test) TextView rvTestTv;

        public VH(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.tv_rv_test)
        void doClick(View view) {
            if (onItemClickLsn != null) {
                onItemClickLsn.onItemClick(view, getLayoutPosition());
            }
        }

    }

    public interface OnItemClickLsn {
        void onItemClick(View v, int position);
    }

}
