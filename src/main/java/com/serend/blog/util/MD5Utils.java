package com.serend.blog.util;

import org.springframework.util.DigestUtils;

public class MD5Utils {
    public static String EncodeByMd5(String password) {
        String md5Password = DigestUtils.md5DigestAsHex(password.getBytes());
        return md5Password;
    }

    public static void main(String[] args) {
        System.out.println(MD5Utils.EncodeByMd5("123456"));
    }
}
