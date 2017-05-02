package com.example.williammordohay.loginprojectsql.security;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by william.mordohay on 02/05/2017.
 */

public class HashPassword {
    public static String hashPassword(String password) throws  NoSuchAlgorithmException{

        String md5Password = null;

        if(password ==null) return null;

        try {

            //Create MessageDigest object for MD5
            MessageDigest digest = MessageDigest.getInstance("MD5");

            //Update input string in message digest
            digest.update(password.getBytes(), 0, password.length());

            //Converts message digest value in base 16 (hex)
            md5Password = new BigInteger(1, digest.digest()).toString(16);

        } catch (NoSuchAlgorithmException e) {

            e.printStackTrace();
        }
        return md5Password;
    }
}
