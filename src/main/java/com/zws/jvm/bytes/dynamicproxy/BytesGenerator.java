package com.zws.jvm.bytes.dynamicproxy;


import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

/**
 * @author zhengws
 * @date 2019-10-13 11:43
 */
public class BytesGenerator {
    public static void main(String[] args) throws Exception {
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(bao);
        dos.writeInt(-889275714);
        dos.writeShort(0);
        dos.writeShort(49);
        byte[] bytes = bao.toByteArray();
        String s = bytesToHex(bytes);
        System.out.println(s);
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < bytes.length; i++) {
            int valueTen = bytes[i] & 0xFF;
            System.out.println(valueTen);
            String hexStr = String.format("%08x", valueTen);
            String hex = Integer.toHexString(valueTen);
            if(hex.length() < 2){
                sb.append(0);
            }
            sb.append(hex);
        }
        return sb.toString();
    }
}
