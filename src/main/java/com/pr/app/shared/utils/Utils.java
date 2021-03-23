package com.pr.app.shared.utils;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Random;

@Component
public class Utils {

    private final Random RANDOM = new SecureRandom();
    private final String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvxyz";

    public String generateUserId(int length) {
        return generateRandomString(length);
    }

    private String generateRandomString(int length) {
        StringBuilder randomString = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            randomString.append(SALTCHARS.charAt(RANDOM.nextInt(SALTCHARS.length())));
        }
        return randomString.toString();
    }

}
