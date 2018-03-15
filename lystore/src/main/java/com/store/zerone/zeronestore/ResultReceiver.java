package com.store.zerone.zeronestore;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
/**
 * 结果接收者
 * Created by xurong on 2017/5/15.
 */
public class ResultReceiver extends BroadcastReceiver {

    private static final String TAG = "ResultReceiver";
    private String resultInfo;
    private int errorCode;

    @Override
    public void onReceive(final Context context, Intent intent) {
        String action = intent.getAction();
        if (action.equals("sunmi.payment.L3.RESULT")) {
            final int resultCode = intent.getIntExtra("resultCode", -1);
            final long amount = intent.getLongExtra("amount", 0);
            // 原交易凭证号
            String voucherNo = intent.getStringExtra("voucherNo");
            // 原参考号
            String referenceNo = intent.getStringExtra("referenceNo");
            String date = intent.getStringExtra("transDate");
            String transId = intent.getStringExtra("transId");
            String batchNo = intent.getStringExtra("batchNo");
            String cardNo = intent.getStringExtra("cardNo");
            String cardType = intent.getStringExtra("cardType");
            String issue = intent.getStringExtra("issue");
            String terminalId = intent.getStringExtra("terminalId");
            String merchantId = intent.getStringExtra("merchantId");
            String merchantName = intent.getStringExtra("merchantName");
            String merchantNameEn = intent.getStringExtra("merchantNameEn");
            int paymentType = intent.getIntExtra("paymentType", -2);
            String transTime = intent.getStringExtra("transTime");
            errorCode = intent.getIntExtra("errorCode", 0);
            final String errorMsg = intent.getStringExtra("errorMsg");
            long balance = intent.getLongExtra("balance", 0);
            int transNum = intent.getIntExtra("transNum", 0);
            long totalAmount = intent.getLongExtra("totalAmount", 0L);
            resultInfo = resultCode + "";
            if (amount != 0) {
                resultInfo = resultInfo + "\namount:" + amount;
            }
            if (!TextUtils.isEmpty(voucherNo)) {
                resultInfo = resultInfo + "\nvoucherNo:" + voucherNo;
            }
            if (!TextUtils.isEmpty(referenceNo)) {
                resultInfo = resultInfo + "\nreferenceNo:" + referenceNo;
            }
            if (!TextUtils.isEmpty(batchNo)) {
                resultInfo = resultInfo + "\nbatchNo:" + batchNo;
            }
            if (!TextUtils.isEmpty(cardNo)) {
                resultInfo = resultInfo + "\ncardNo:" + cardNo;
            }
            if (!TextUtils.isEmpty(cardType)) {
                resultInfo = resultInfo + "\ncardType:" + cardType;
            }
            if (!TextUtils.isEmpty(issue)) {
                resultInfo = resultInfo + "\nissue:" + issue;
            }
            if (!TextUtils.isEmpty(terminalId)) {
                resultInfo = resultInfo + "\nterminalId:" + terminalId;
            }
            if (!TextUtils.isEmpty(merchantId)) {
                resultInfo = resultInfo + "\nmerchantId:" + merchantId;
            }
            if(!TextUtils.isEmpty(merchantName)){
                resultInfo = resultInfo + "\nmerchantName:" + merchantName;
            }
            if (paymentType != -2) {
                resultInfo = resultInfo + "\npaymentType:" + paymentType;
            }
            if (!TextUtils.isEmpty(date)) {
                resultInfo = resultInfo + "\ntransDate:" + date;
            }
            if (!TextUtils.isEmpty(transTime)) {
                resultInfo = resultInfo + "\ntransTime:" + transTime;
            }
            if (errorCode != 0) {
                resultInfo = resultInfo + "\nerrorCode:" + errorCode;
            }
            if (!TextUtils.isEmpty(errorMsg)) {
                resultInfo = resultInfo + "\nerrorMsg:" + errorMsg;
            }
            if (balance != 0) {
                resultInfo = resultInfo + "\nbalance:" + balance;
            }
            if (TextUtils.isEmpty(transId)) {
                resultInfo = resultInfo + "\ntransId:" + transId;
            }
            if (!TextUtils.isEmpty(merchantNameEn)) {
                resultInfo = resultInfo + "\nmerchantNameEn:" + merchantNameEn;
            }
            if (transNum != 0) {
                resultInfo = resultInfo + "\ntransNum:" + transNum;
            }
            if (totalAmount != 0) {
                resultInfo = resultInfo + "\ntotalAmount:" + totalAmount;
            }

            Log.e(TAG, resultInfo);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent myIntent = new Intent(context, ResultActivity.class);
                    myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    myIntent.putExtra("resultCode", resultCode);
                    myIntent.putExtra("resultInfo", resultInfo);
                    myIntent.putExtra("errorMsg", errorMsg);
                    myIntent.putExtra("errorCode", errorCode);
                    myIntent.putExtra("money", amount+"");
                    context.startActivity(myIntent);
                }
            }, 500);

        }
    }
}
