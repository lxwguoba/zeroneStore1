package com.store.zerone.zeronestore.fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.store.zerone.zeronestore.R;
import com.store.zerone.zeronestore.fragment.order.DFFragment;
import com.store.zerone.zeronestore.fragment.order.YCFragment;
import com.store.zerone.zeronestore.fragment.order.YFFragment;

/**
 * Created by on 2018/1/30 0030 11 31.
 * Author  LiuXingWen
 */

public class OrderControlFrgment extends Fragment   implements View.OnClickListener {
    private View view;
    private LinearLayout ll_order_df, ll_order_yf, ll_order_yc;
    private TextView order_df,order_yf, order_yc, tvCurrent;
    private FragmentManager fragmentManager;
    private FragmentTransaction beginTransaction;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        if (view==null){
            view = inflater.inflate(R.layout.activity_order_main,null);
        }
        initView();
        return view;
    }

    /**
     *
     */
    private void initView() {
        ll_order_df = (LinearLayout) view.findViewById(R.id.ll_order_df);
        ll_order_yf = (LinearLayout) view.findViewById(R.id.ll_order_yf);
        ll_order_yc = (LinearLayout) view.findViewById(R.id.ll_order_yc);
        ll_order_df.setOnClickListener(this);
        ll_order_yf.setOnClickListener(this);
        ll_order_yc.setOnClickListener(this);
        order_df = (TextView) view.findViewById(R.id.order_df);
        order_yf = (TextView) view.findViewById(R.id.order_yf);
        order_yc = (TextView) view.findViewById(R.id.order_yc);
        order_df.setSelected(true);
        tvCurrent = order_df;
        fragmentManager = getFragmentManager();
        beginTransaction = fragmentManager.beginTransaction();
        beginTransaction.replace(R.id.ll_order_main,new DFFragment());
        beginTransaction.commit();
    }


    @Override
    public void onClick(View v) {
      tvCurrent.setSelected(false);
      FragmentManager fragmentManager = getFragmentManager();
      FragmentTransaction beginTransaction = fragmentManager
                .beginTransaction();
        switch (v.getId()) {
            case R.id.ll_order_df:
                beginTransaction.replace(R.id.ll_order_main, new DFFragment());
            case 0:
                order_df.setSelected(true);
                order_df.setTextColor(Color.parseColor("#252429"));
                order_yf.setTextColor(Color.parseColor("#838383"));
                order_yc.setTextColor(Color.parseColor("#838383"));
                tvCurrent = order_df;
                break;
            case R.id.ll_order_yf:
                beginTransaction.replace(R.id.ll_order_main,new YFFragment());
            case 1:
                order_yf.setSelected(true);
                order_df.setTextColor(Color.parseColor("#838383"));
                order_yf.setTextColor(Color.parseColor("#252429"));
                order_yc.setTextColor(Color.parseColor("#838383"));
                tvCurrent = order_yf;
                break;
            case R.id.ll_order_yc:
                beginTransaction.replace(R.id.ll_order_main, new YCFragment());

            case 2:
                order_yc.setSelected(true);
                order_df.setTextColor(Color.parseColor("#838383"));
                order_yf.setTextColor(Color.parseColor("#838383"));
                order_yc.setTextColor(Color.parseColor("#252429"));
                tvCurrent = order_yc;
                break;
            default:
                break;
        }
        beginTransaction.commit();
    }

}
