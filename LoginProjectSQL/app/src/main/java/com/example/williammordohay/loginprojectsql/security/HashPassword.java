package com.example.williammordohay.loginprojectsql.security;

import java.security.MessageDigest;
import java.security.MessageDigestSpi;
import java.security.NoSuchAlgorithmException;

/**
 * Created by william.mordohay on 28/04/2017.
 */

public class HashPassword {
    public static String hashPassword(String password) throws NoSuchAlgorithmException{
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes(),0,password.length());
        byte[] byteTable = md.digest();
        StringBuffer sb = new StringBuffer();
        for(byte b1 : byteTable){
            sb.append(Integer.toHexString(b1 & 0xff).toString());
        }
        return  sb.toString();
    }
}
