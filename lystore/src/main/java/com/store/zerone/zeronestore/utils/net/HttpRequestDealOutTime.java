package com.store.zerone.zeronestore.utils.net;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.store.zerone.zeronestore.application.MyApplication;

/**
 * Created by on 2017/12/26 0026 10 09.
 * Author  LiuXingWen
 * 这个页面的请求已经处理了超时后的问题
 */

public class HttpRequestDealOutTime  {

    static int DEFAULT_TIMEOUT_MS = 10000;
    static int DEFAULT_MAX_RETRIES = 3;

    /**
     *
     * @param url  访问路径
     * @param context 上下文
     * @param handler  handler机制用来处理刷新数据和UI
     * @param responseStaus 设置成功后的状态值
     */
     public static void HttpGet(String url, Context context, final Handler handler, final Integer responseStaus) {
         StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
             @Override
             public void onResponse(String res) {
                 Message mess= new Message();
                  mess.what=responseStaus;
                  mess.obj=res;
                 handler.sendMessage(mess);
             }
         }, new Response.ErrorListener() {
             @Override
             public void onErrorResponse(VolleyError volleyError) {
                 Message mess= new Message();
                 mess.what=0;
                 mess.obj=volleyError.toString();
                 handler.sendMessage(mess);

             }
         });
        // 设置Volley超时重试策略
         stringRequest.setTag("");
         stringRequest.setRetryPolicy(new DefaultRetryPolicy(DEFAULT_TIMEOUT_MS, DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
         RequestQueue requestQueue = MyApplication.getQueues();
         requestQueue.start();
         requestQueue.add(stringRequest);
     }
}
