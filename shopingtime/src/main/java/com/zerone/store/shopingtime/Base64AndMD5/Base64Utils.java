package com.zerone.store.shopingtime.Base64AndMD5;

import android.util.Base64;

public class Base64Utils {


    /**
     * 加密base64加密字符串
     *
     * @param src
     * @return
     * @throws Exception
     */
    public static byte[] decrypt(String src) throws Exception {
        return Base64.encode(src.getBytes(), Base64.DEFAULT);
    }
}
