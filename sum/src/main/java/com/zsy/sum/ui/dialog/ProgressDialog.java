package com.zsy.sum.ui.dialog;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.zsy.sum.R;

/**
 * Created by 24275 on 2016/10/25.
 */

public class ProgressDialog extends DialogFragment {

    private static final String MsgKey = "MsgKey";

    private String msg;

    public static ProgressDialog newInstance(String msg) {
        ProgressDialog progressDialog = new ProgressDialog();
        Bundle bundle = new Bundle();
        bundle.putString(MsgKey, msg);
        progressDialog.setArguments(bundle);
        return progressDialog;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        msg = getArguments().getString(MsgKey);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        View rootView = inflater.inflate(R.layout.dialog_progress, container, false);
        TextView msgTv = (TextView) rootView.findViewById(R.id.tv_msg);
        msgTv.setText(msg);
        return rootView;
    }
}
