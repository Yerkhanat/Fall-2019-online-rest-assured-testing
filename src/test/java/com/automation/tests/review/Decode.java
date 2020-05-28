package com.automation.tests.review;
import org.apache.commons.codec.binary.Base64;
public class Decode {
    public static void main(String[] args) {
        // Encode data on your side using BASE64
// Decode data on other side, by processing encoded data
        byte[] valueDecoded = Base64.decodeBase64("YnJlYWsgdW50aWwgODoxMw==");
        System.out.println("Decoded value is " + new String(valueDecoded));
    }
}