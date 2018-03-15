package com.store.zerone.zeronestore;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.qzs.voiceannouncementlibrary.VoiceUtils;
import com.store.zerone.zeronestore.baseacticity.BaseAppActivity;
/**
 * @author xurong on 2017/5/15.
 */

public class ResultActivity extends BaseAppActivity {

    private static final String TAG = "ResultActivity";

    TextView result_tv;
    private ImageView result_iv;
    private TextView result_info;
    private int errorCode;
    private TextView pay_complete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        result_tv = (TextView) findViewById(R.id.result_tv);
        result_iv = (ImageView) findViewById(R.id.result_iv);
        result_info = (TextView) findViewById(R.id.result_info);
        pay_complete = (TextView) findViewById(R.id.pay_complete);
        int resultCode = getIntent().getIntExtra("resultCode", -1);
        String errorMsg = getIntent().getStringExtra("errorMsg");
        String resultInfo = getIntent().getStringExtra("resultInfo");
        String money=  getIntent().getStringExtra("money");
        errorCode = getIntent().getIntExtra("errorCode",0);

        if (resultCode == 0) {
            // 交易成功
//            Toast.makeText(this, "交易成功, 具体信息请查看控制台的Log", Toast.LENGTH_SHORT).show();
//            Log.e(TAG, "交易成功");
            result_info.setText("支付成功！");
            double dmoney= Double.parseDouble(money)/100;
            //语音播报
            VoiceUtils.with(this).Play(dmoney+"",true);
        } else if (resultCode == -1) {
            // 交易失败
            Toast.makeText(this, errorMsg, Toast.LENGTH_SHORT).show();
            Log.e(TAG, errorMsg);
            result_info.setText("支付失败！");
            result_info.setTextColor(Color.parseColor("#ff0000"));
            result_iv.setImageResource(R.mipmap.pay_fails);
            if (errorCode==401){
                result_tv.setText("交易失败");
            }else  if (errorCode== 418){
                result_tv.setText("支付取消");
            }else {
                result_tv.setText("支付失败！！！！");
            }
            result_tv.setText("Result:" + resultInfo);
        }
        pay_complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ResultActivity.this.finish();
            }
        });
    }
}
