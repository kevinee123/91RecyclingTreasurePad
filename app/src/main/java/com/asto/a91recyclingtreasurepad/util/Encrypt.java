package com.asto.a91recyclingtreasurepad.util;

import android.util.Base64;

/**
 * Created by zj on 2018/10/25.
 * is use for:
 */
public class Encrypt {
    public static void main(String[] args) {
    }
    /**
     * 字符串加密
     * @param text 要加密的字符串
     * @return 加密后的字符串
     */
    public static String encrypt(String text){
//        String after=new String(xorEncode(text.getBytes()));
        String after = xor(text);
        String after2 = encode64(after);
        return after2;
    }

    /**
     * 解密字符串
     * @param text 要解密的字符串
     * @return 解密后的字符串
     */
    public static String decrypt(String text) {
        String before = decode64(text);
//        String before2=new String(xorEncode(before.getBytes()));
        String before2 = xor(before);
        return before2;
    }

    private static final String secretKey ="20181025asto91zs";

    public static byte[] xorEncode(byte[] data){

        //key，用于异或
        String key="20181025asto91zs";
        byte[] keyBytes=key.getBytes();

        byte[] encryptBytes=new byte[data.length];
        for(int i=0; i<data.length; i++){
            encryptBytes[i]=(byte) (data[i]^keyBytes[i%keyBytes.length]);
        }
        return encryptBytes;
    }

    public static String xor(String text){
        char[]array=text.toCharArray();//获取字符数组
        byte[] keyBytes=secretKey.getBytes();
        for(int i=0;i<array.length;i++)//遍历字符数组
        {
            array[i]=(char)(array[i] ^ keyBytes[i%keyBytes.length]);//对每个数组元素进行异或运算，异或的值可以自己选择
        }
        System.out.println("加密或者解密结果如下：");
        System.out.println(new String(array));//输出加密或者解密结果

        return new String(array);
    }

    /**
     * 字符串进行Base64编码加密
     *
     * @param str
     * @return
     */
    public static final String encode64(String str) {
        return Base64.encodeToString(str.getBytes(), Base64.DEFAULT);
    }

    /**
     * 字符串进行Base64解码解密
     *
     * @param encodedString
     * @return
     */

    public static final String decode64(String encodedString) {
        return new String(Base64.decode(encodedString, Base64.DEFAULT));
    }
}