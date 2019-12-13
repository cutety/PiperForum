package com.cutety.util;

import java.security.MessageDigest;

/**
 * Description:生成MD5
 * Created by cutety on 2019/11/21,1:21 下午.
 **/
public class ProduceMD5 {

    /**
     * 生成MD5
     * @param message
     * @return
     */
    public static String getMD5(String message) {
        String md5 = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");  // 创建一个md5算法对象
            byte[] messageByte = message.getBytes("UTF-8");
            byte[] md5Byte = md.digest(messageByte);              // 获得MD5字节数组,16*8=128位
            md5 = HexConversion.bytesToHex(md5Byte);                            // 转换为16进制字符串
        } catch (Exception e) {
            e.printStackTrace();
        }
        return md5;
    }
}
