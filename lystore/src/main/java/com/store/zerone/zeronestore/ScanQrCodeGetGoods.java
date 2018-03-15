package com.store.zerone.zeronestore;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Camera;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by on 2018/3/15 0015 10 18.
 * Author  LiuXingWen
 */

public class ScanQrCodeGetGoods extends AppCompatActivity {

    private List list;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrscan);
        initView();
//        /**
//         * 执行扫面Fragment的初始化操作
//         */
//        CaptureFragment captureFragment = new CaptureFragment();
//        // 为二维码扫描界面设置定制化界面
//        CodeUtils.setFragmentArgs(captureFragment, R.layout.my_camera);
//        captureFragment.setAnalyzeCallback(analyzeCallback);
//        /**
//         * 替换我们的扫描控件
//         */
//        getSupportFragmentManager().beginTransaction().replace(R.id.fl_my_container, captureFragment).commit();
        list = new ArrayList<String>();
        Intent intent = new Intent(this, CaptureActivity.class);
        startActivityForResult(intent, 520);
    }

    private void initView() {
        Button btn= (Button) findViewById(R.id.second_button1);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ScanQrCodeGetGoods.this.finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 520) {
            //处理扫描结果（在界面上显示）
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    list.add(result);
                    Toast.makeText(this, "解析结果:" + result, Toast.LENGTH_LONG).show();
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    Toast.makeText(ScanQrCodeGetGoods.this, "解析二维码失败", Toast.LENGTH_LONG).show();
                }
            }
            Message ms=new Message();
            ms.what=10;
            mHandler.sendMessage(ms);
        }
    }

    /**
     *
     * 二维码解析回调函数
     *
     */
    CodeUtils.AnalyzeCallback analyzeCallback = new CodeUtils.AnalyzeCallback() {
        @Override
        public void onAnalyzeSuccess(Bitmap mBitmap, String result) {
            Toast.makeText(ScanQrCodeGetGoods.this, result, Toast.LENGTH_LONG).show();
//            Intent resultIntent = new Intent();
//            Bundle bundle = new Bundle();
//            bundle.putInt(CodeUtils.RESULT_TYPE, CodeUtils.RESULT_SUCCESS);
//            bundle.putString(CodeUtils.RESULT_STRING, result);
//            resultIntent.putExtras(bundle);
//            ScanQR.this.setResult(RESULT_OK, resultIntent);
//            ScanQR.this.finish();
        }
        @Override
        public void onAnalyzeFailed() {
            Intent resultIntent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putInt(CodeUtils.RESULT_TYPE, CodeUtils.RESULT_FAILED);
            bundle.putString(CodeUtils.RESULT_STRING, "");
            resultIntent.putExtras(bundle);
            ScanQrCodeGetGoods.this.setResult(RESULT_OK, resultIntent);
            ScanQrCodeGetGoods.this.finish();
        }
    };
    Handler mHandler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 10:

                    Intent intent = new Intent(ScanQrCodeGetGoods.this, CaptureActivity.class);
                    startActivityForResult(intent, 520);
                    break;
            }

        }
    };


//    Runnable runnable = new Runnable() {
//
//        @Override
//        public void run() {
//            // handler自带方法实现定时器
//            try {
//                Thread.sleep(1000);
//            } catch (Exception e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//                System.out.println("exception...");
//            }
//        }
//    };
}
