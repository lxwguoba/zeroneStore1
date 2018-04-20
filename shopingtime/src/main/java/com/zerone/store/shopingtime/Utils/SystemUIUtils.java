package com.zerone.store.shopingtime.Utils;

import android.view.View;

/**
 * Created by on 2018/4/20 0020 09 08.
 * Author  LiuXingWen
 * sunmi提供的可以activity的全屏
 */

public class SystemUIUtils {
    public static void setStickFullScreen(View view) {
        int systemUiVisibility = view.getSystemUiVisibility();
        int flags = View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        systemUiVisibility |= flags;
        view.setSystemUiVisibility(systemUiVisibility);
    }
}
