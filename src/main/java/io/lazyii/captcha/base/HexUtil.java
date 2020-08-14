package io.lazyii.captcha.base;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;

/**
 * Created by admin on 2020/8/13 16:26:23.
 */
public class HexUtil {
    
    public static String hexDecode(String hex) {
        int l = hex.length();
        //判断奇偶性
        if ((l & 1) == 1) {
            //odd 奇数
            throw new RuntimeException("不应该为奇数");
        }
        byte[] data = new byte[l / 2];
        for (int i = 0; i < l; i += 2) {
            data[i / 2] = (byte) ((Character.digit(hex.charAt(i), 16) << 4) + Character.digit(hex.charAt(i + 1), 16));
        }
        return new String(data, StandardCharsets.UTF_8);
    }
    
    public static String hexEncode(String origin) {
        byte[] originBytes = origin.getBytes(StandardCharsets.UTF_8);
        int l = originBytes.length;
        String result = String.format("%0" + 2 * l + "x", new BigInteger(1, originBytes));
        return result;
    }
}
