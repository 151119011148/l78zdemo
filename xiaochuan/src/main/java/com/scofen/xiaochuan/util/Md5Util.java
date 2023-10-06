package com.scofen.xiaochuan.util;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author yunmu
 * @date 2021-09-10 7:01 下午
 */
public class Md5Util {
    public static final String CHARSET_UTF_8 = "utf-8";

    /**
     *
     * */
    private static final String[] hexDigits = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };



    /**
     *
     *
     * @param b
     * @return
     */
    private static String byteArrayToHexString(byte[] b) {
        StringBuilder resultSb = new StringBuilder();
        for (int i = 0; i < b.length; i++) {
            resultSb.append(byteToHexString(b[i]));
        }

        return resultSb.toString();
    }

    /**
     * byteToHesString
     * */
    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0) {
            n += 256;
        }
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }

    /**
     *
     *
     * @param origin
     * @param charsetName
     * @return
     */
    public static String md5(String origin, String charsetName) {
        String resultString = null;
        try {
            resultString = new String(origin);
            MessageDigest md = MessageDigest.getInstance("MD5");
            if (charsetName == null || "".equals(charsetName)) {
                resultString = byteArrayToHexString(md.digest(resultString.getBytes()));
            } else {
                resultString = byteArrayToHexString(md.digest(resultString.getBytes(charsetName)));
            }
        } catch (Exception exception) {
            resultString = null;
        }
        return resultString;
    }


    /**
     * MD5加密
     * @param sourceStr
     * @return
     */
    public static String encryptMd5(String sourceStr) {
        // 用于加密的字符
        String result = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(sourceStr.getBytes());
            byte b[] = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0) {
                    i += 256;
                }
                if (i < 16) {
                    buf.append("0");
                }
                buf.append(Integer.toHexString(i));
            }
            result = buf.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * @param origin
     * @return
     */
    public static String md5(String origin) {
        return md5(origin, CHARSET_UTF_8);
    }
}
