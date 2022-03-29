package com.revature.hashing;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {
    public static String getMd5(String input) throws NoSuchAlgorithmException {
        try{
            //static getInstance method is called with hashing MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            
            //digest() method is called to calculate message digest of an input digest() return array of byte
            byte[] messageDigest = md.digest(input.getBytes());
            
            //convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);
            
            //convert message digest into hex value
            String hashtext = no.toString(16);
            while(hashtext.length()<32) {
                hashtext = "0" + hashtext;
            }
                return hashtext;
            }
            //for specifying wrong message digest algorithms
            catch(NoSuchAlgorithmException e){
                throw new RuntimeException(e);
            }
        }
    }

