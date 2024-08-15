package com.shorturl;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

abstract class RandAlphaNumGenerator {

    private static String alphaNum = "ABCDEFGHIJKLMNOPQRS0123456789";
    private static Random random = new Random();

    static String generateAlphaNum(String input) {
        String hashText = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");

            byte[] messageDigest = md.digest(input.getBytes());

            BigInteger no = new BigInteger(1, messageDigest);

            hashText = no.toString(16);
            while (hashText.length() < 32) {
                hashText = "0" + hashText;
            }

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return hashText.substring(0, 9);
    }

    static String generateRandSalt() {
        String randSalt = "";

        for (int i = 1; i <= 3; ++i) {
            int randIndex = random.nextInt(alphaNum.length());
            randSalt += alphaNum.charAt(randIndex);
        }

        return randSalt;

    }

}
