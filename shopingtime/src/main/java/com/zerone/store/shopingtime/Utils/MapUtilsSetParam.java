package com.zerone.store.shopingtime.Utils;

import android.content.Context;

import com.zerone.store.shopingtime.Base64AndMD5.CreateToken;
import com.zerone.store.shopingtime.Bean.UserInfo;
import com.zerone.store.shopingtime.DB.impl.UserInfoImpl;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/17.
 * 把相同的数据封装起来 这样有利于数据的维护
 */

public class MapUtilsSetParam {
    /**
     * 反问网络获取数据 这个是反问时的参数  把相同的参数整合起来；
     *
     * @return map 返回的是带有参数的map 那些参数是不变的 所以集合在一起
     */
    public static Map<String, String> getMap(Context context) {
        UserInfo userInfo = initGetUserInfo(context);
        if (userInfo != null) {
            try {
                String timestamp = System.currentTimeMillis() + "";
                String token = CreateToken.createToken(userInfo.getUuid(), timestamp, userInfo.getAccount());
                Map<String, String> loginMap = new HashMap<String, String>();
                loginMap.put("organization_id", userInfo.getOrganization_id());
                loginMap.put("account_id", userInfo.getAccount_id());
                loginMap.put("token", token);
                loginMap.put("timestamp", timestamp);
                return loginMap;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 获取用户信息
     */
    private static UserInfo initGetUserInfo(Context context) {
        UserInfoImpl userInfoImpl = new UserInfoImpl(context);
        try {
            UserInfo userInfo = userInfoImpl.getUserInfo("10");
            return userInfo;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }
}
