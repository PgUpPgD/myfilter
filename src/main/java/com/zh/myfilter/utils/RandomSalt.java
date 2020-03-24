package com.zh.myfilter.utils;

import java.security.SecureRandom;

public class  RandomSalt {

    public static String getRandomSalt(){

        SecureRandom random = new SecureRandom();
        byte bytes[] = new byte[15];
        random.nextBytes(bytes);
        String salt = org.apache.commons.codec.binary.Base64.encodeBase64String(bytes);
        return salt;
    }
}
