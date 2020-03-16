package com.raines.raineslearn.interesting.base64;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;

public class Base64_ByteTest {
    public static void main(String args[]) throws IOException {
        /**
         * 编码
         */
        // 定义一个BASE64Encoder
        BASE64Encoder encode = new BASE64Encoder();
        // 将byte[]转换为base64
        String base64 = encode.encode("五笔字型电子计算机".getBytes());
        // 输出base64
        System.out.println(base64);

        /**
         * 解码
         */
        // 新建一个BASE64Decoder
        BASE64Decoder decode = new BASE64Decoder();
        // 将base64转换为byte[]
        byte[] b = decode.decodeBuffer(base64);
        // 输出转换后的byte[]
        System.out.println(new String(b));
    }
}