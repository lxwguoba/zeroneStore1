package com.zerone.store.shopingtime.Utils;

import android.content.Context;

import com.zyao89.view.zloading.ZLoadingDialog;
import com.zyao89.view.zloading.Z_TYPE;

/**
 * Created by on 2018/1/16 0016 14 57.
 * Author  LiuXingWen
 */

public class LoadingUtils {
    /**
     * @param context  上文
     * @param color    加载的颜色
     * @param hintText 加载提示文字
     * @return
     */
    public static ZLoadingDialog getDailog(Context context, int color, String hintText) {
        ZLoadingDialog dialog = new ZLoadingDialog(context);
        dialog.setLoadingBuilder(Z_TYPE.DOUBLE_CIRCLE)//设置类型
                .setLoadingColor(color)//颜色
                .setHintTextSize(14) // 设置字体大小 dp
                .setHintText(hintText);
        return dialog;
    }
}
