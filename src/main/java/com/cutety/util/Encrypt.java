
package com.cutety.util;

import org.apache.commons.codec.binary.Base64;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Base64组件
 *
 * @author wbw
 * @version 1.0
 * @since 1.0
 */
public abstract class Encrypt {

    /**
     * 字符编码
     */
    public final static String ENCODING = "UTF-8";

    /**
     * Base64编码
     *
     * @param data 待编码数据
     * @return String 编码数据
     * @throws Exception
     */
    public static String encode(String data) throws Exception {

        // 执行编码
        byte[] b = Base64.encodeBase64(data.getBytes(ENCODING));

        return new String(b, ENCODING);
    }

    /**
     * Base64安全编码<br>
     * 遵循RFC 2045实现
     *
     * @param data
     *            待编码数据
     * @return String 编码数据
     *
     * @throws Exception
     */
    public static String encodeSafe(String data) throws Exception {

        // 执行编码
        byte[] b = Base64.encodeBase64(data.getBytes(ENCODING), true);

        return new String(b, ENCODING);
    }

    /**
     * Base64解码
     *
     * @param data 待解码数据
     * @return String 解码数据
     * @throws Exception
     */
    public static String decode(String data) throws Exception {

        // 执行解码
        byte[] b = Base64.decodeBase64(data.getBytes(ENCODING));

        return new String(b, ENCODING);
    }

    public static void main(String[] args) throws Exception {
        String aksd = encode("");
        String decode = decode("VGh1IERlYyAwNSAxMzo0NjowNCBDU1QgMjAxOUBhbGV4");
        System.out.println(decode);
        Date currentTime = new Date();
       SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
       String dateString = formatter.format(currentTime);
        System.out.println(dateString);
        System.out.println("Thu Dec 05 13:53:43 CST 2019@alex".split("@")[0]);

        DateFormat fmt =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = fmt.parse("2019-12-05 14:09:26");
        System.out.println(date.toString());
        String codes = decode("MjAxOS0xMi0xMyAxMzowODo0MkBhbGV4");
        String[] split = codes.split("@");
        System.out.println(split+"====="+split[1]);

    }
}
