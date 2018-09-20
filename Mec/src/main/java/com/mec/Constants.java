package com.mec;

/**
 * 应用常量
 * @author timpkins
 */
public final class Constants {

    public static final class Preference {
        public static final String PREFERENCES_HOME = "mpreferences";
        public static final String TOKE = "mtoke";
    }

    public static final class NetUrl {
        private static final String BASE_URL = "http://178.128.212.185:8888/app/app/api/";
        public static final String MENU = BASE_URL + "menu.do"; // 菜单
        public static final String LOGIN = BASE_URL + "authentication.do"; // 登录
        public static final String REGISTER = BASE_URL + "register.do"; // 注册
        public static final String AVATAR = BASE_URL + "userhead.do"; // 更换头像
        public static final String LOGOUT = BASE_URL + "logout.do"; // 退出登录
    }
}
