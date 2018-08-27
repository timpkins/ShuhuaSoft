package com.mec.util;

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
}
