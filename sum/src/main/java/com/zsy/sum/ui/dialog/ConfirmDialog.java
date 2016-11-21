package com.zsy.sum.ui.dialog;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.zsy.sum.R;
import com.zsy.sum.utils.depend.Lg;


/**
 * Created by 24275 on 2016/9/29.
 */

public class ConfirmDialog extends DialogFragment {

    private static final String BuilderKey = "BuilderKey";

    private Builder builder;
    private ClickLsn clickLsn;

    private static ConfirmDialog newInstance(Builder builder) {
        ConfirmDialog cusDialog = new ConfirmDialog();
        Bundle bundle = new Bundle();
        bundle.putParcelable(BuilderKey, builder);
        cusDialog.setArguments(bundle);
        return cusDialog;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Lg.i("Parent:" + getParentFragment());
        builder = (Builder) getArguments().get(BuilderKey);
        if (builder.getKey() == null) {
            return;
        }
        ClickLsnGet clickLsnGet = null;
        Fragment parentFragment;
        if ((parentFragment = getParentFragment()) != null && parentFragment instanceof ClickLsnGet) {
            clickLsnGet = (ClickLsnGet) parentFragment;
        }
        if (clickLsnGet == null && context instanceof ClickLsnGet) {
            clickLsnGet = (ClickLsnGet) context;
        }
        clickLsn = clickLsnGet.get(builder.getKey());
    }



//    @Override
//    public Dialog onCreateDialog(Bundle savedInstanceState) {
//        Activity activity = getActivity();
//        if (activity == null) {
//            return null;
//        }
//        AlertDialog.Builder builder = new AlertDialog.
//                Builder(activity)
//                .setTitle("title")
//                .setMessage("msg")
//                .setIcon(R.mipmap.ic_launcher)
//                .setPositiveButton("pos", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        DebugUtils.log("pos click");
//                    }
//                });
//        return builder.create();

//    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        View rootView = inflater.inflate(R.layout.dialog_confirm, container, false);
        if (builder.getTitile() != null) {
            TextView titleTv = (TextView) rootView.findViewById(R.id.tv_title);
            titleTv.setText(builder.getTitile());
            View titleLineV = rootView.findViewById(R.id.v_title_line);
            titleTv.setVisibility(View.VISIBLE);
            titleLineV.setVisibility(View.VISIBLE);
        }

        if (builder.getMsg() != null) {
            TextView msgTv = (TextView) rootView.findViewById(R.id.tv_msg);
            msgTv.setText(builder.getMsg());
            View msgLineV = rootView.findViewById(R.id.v_msg_line);
            msgTv.setVisibility(View.VISIBLE);
            msgLineV.setVisibility(View.VISIBLE);
        }

        Lg.i("leftText:" + builder.getLeftText());
        if (builder.getLeftText() != null) {
            final TextView leftTv = (TextView) rootView.findViewById(R.id.tv_left);
            leftTv.setText(builder.getLeftText());
            leftTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dismiss();
                    if (clickLsn != null) {
                        clickLsn.leftClick();
                    }
                }
            });
            leftTv.setVisibility(View.VISIBLE);
            Lg.i("show left");
        }

        if (builder.getRightText() != null) {
            final TextView rightTv = (TextView) rootView.findViewById(R.id.tv_right);
            rightTv.setText(builder.getRightText());
            rightTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dismiss();
                    if (clickLsn != null) {
                        clickLsn.rightClick();
                    }
                }
            });
            rightTv.setVisibility(View.VISIBLE);
            Lg.i("show right");

        }

        if (builder.getLeftText() != null && builder.getRightText() != null) {
            View clickLineV = rootView.findViewById(R.id.v_click_line);
            clickLineV.setVisibility(View.VISIBLE);
        }

        return rootView;
    }

    public interface ClickLsn {

        void leftClick();

        void rightClick();
    }

    public interface ClickLsnGet {
        ClickLsn get(String key);
    }

    public static class Builder implements Parcelable {
        private String titile;
        private String msg;
        private String leftText;
        private String rightText;
        private String clickLsnKey;

        public Builder() {
        }

        public Builder setTitile(String titile) {
            this.titile = titile;
            return this;
        }

        public Builder setMsg(String msg) {
            this.msg = msg;
            return this;
        }

        public Builder setLeft(String leftText) {
            this.leftText = leftText;
            return this;
        }

        public Builder setRight(String rightText) {
            this.rightText = rightText;
            return this;
        }

        public Builder setKey(String clickLsnKey) {
            this.clickLsnKey = clickLsnKey;
            return this;
        }

        public String getTitile() {
            return titile;
        }

        public String getMsg() {
            return msg;
        }

        public String getLeftText() {
            return leftText;
        }

        public String getRightText() {
            return rightText;
        }

        public String getKey() {
            return clickLsnKey;
        }


        public ConfirmDialog build() {
            return ConfirmDialog.newInstance(this);
        }


        protected Builder(Parcel in) {
            titile = in.readString();
            msg = in.readString();
            leftText = in.readString();
            rightText = in.readString();
            clickLsnKey = in.readString();
        }

        public static final Creator<Builder> CREATOR = new Creator<Builder>() {
            @Override
            public Builder createFromParcel(Parcel in) {
                return new Builder(in);
            }

            @Override
            public Builder[] newArray(int size) {
                return new Builder[size];
            }
        };

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(titile);
            dest.writeString(msg);
            dest.writeString(leftText);
            dest.writeString(rightText);
            dest.writeString(clickLsnKey);
        }
    }

}
