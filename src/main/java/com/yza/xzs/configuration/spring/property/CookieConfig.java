package com.yza.xzs.configuration.spring.property;

/**
 * 配置Cookie属性类
 */
public class CookieConfig {
    /**
     * 设置用于唯一表示该应用的cookie键值
     * @return Cookie Name
     */
    public static String getName(){return "yza-xzs-1s5a%^#6fb1dd2gg66z";}

    /**
     * 设置cookie有效时间
     * @return cookie valid time
     */
    public static Integer getInterval(){return 7*24*60*60;}
}
