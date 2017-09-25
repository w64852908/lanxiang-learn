package com.lanxiang.apachecommons.utils;

import org.apache.commons.codec.digest.DigestUtils;

import java.io.UnsupportedEncodingException;

/**
 * Created by lanxiang on 2017/5/9.
 */
public class MD5Util {
    private static final String DEFAULT_ENCODING = "UTF-8";

    /**
     * 对指定的字符串进行md5编码
     *
     * @param plainText
     * @return
     */
    public static String md5(String plainText) {
        return md5(plainText, DEFAULT_ENCODING);
    }

    /**
     * 对指定的字符串进行md5编码
     *
     * @param plainText
     * @return
     */
    public static String md5(String plainText, String encoding) {
        try {
            return DigestUtils.md5Hex(plainText.getBytes(encoding));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("不支持的编码", e);
        }
    }

    public static void main(String[] args) throws Exception {
        String plainText = "api=gateway.sync.cinemas&merCode=test_yyb&signType=MD5&timestamp=1470623359&version=1.0&key=xv9krsbh0vjrj5dvnlcbexgtmlzmxmcv";
        String signMsg = new String(DigestUtils.md5(plainText.getBytes("UTF-8")), "UTF-8");
        System.out.print(MD5Util.md5(plainText));
    }
}
