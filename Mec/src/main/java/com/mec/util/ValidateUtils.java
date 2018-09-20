package com.mec.util;

import android.support.annotation.NonNull;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author timpkins
 */
public final class ValidateUtils {

    // 邮箱验证
    public static boolean isEmail(String email) {
        String regex = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
        return Pattern.matches(regex, email);
    }

    // 帐号验证
    public static boolean isAccount(String account){
        String regex = "[a-z,A-Z,0-9,_]{1,15}$";
        return Pattern.matches(regex, account);
    }

    //密码验证
    public static boolean isPasswd(String passwd){
        String regex = "[a-z,A-Z,0-9,_]{6,15}$";
        return Pattern.matches(regex, passwd);
    }

    /**
     * 手机号码验证
     * @param phone 待验证手机号
     * @return true为手机号，反之则不为手机号
     */
    public static boolean isPhone(@NonNull String phone) {
        String regex = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(phone);
        return m.matches();
    }
}
