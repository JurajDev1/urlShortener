package com.urlshortener.application;

import java.util.Random;

public class Helper {

    public static String generatePassword(int length) {
        String capitalCaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerCaseLetters = "abcdefghijklmnopqrstuvwxyz";
        String specialCharacters = "!@#$";
        String numbers = "1234567890";
        String combinedChars = capitalCaseLetters + lowerCaseLetters + specialCharacters + numbers;
        Random random = new Random();
        char[] password = new char[length];

        password[0] = lowerCaseLetters.charAt(random.nextInt(lowerCaseLetters.length()));
        password[1] = capitalCaseLetters.charAt(random.nextInt(capitalCaseLetters.length()));
        password[2] = specialCharacters.charAt(random.nextInt(specialCharacters.length()));
        password[3] = numbers.charAt(random.nextInt(numbers.length()));

        for(int i = 4; i< length ; i++) {
            password[i] = combinedChars.charAt(random.nextInt(combinedChars.length()));
        }
        return String.valueOf(password);
    }

    private static final String allowedString = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final char[] allowedCharacters = allowedString.toCharArray();

    public static String generateShortURL(int number)
    {

        StringBuilder shorturl = new StringBuilder();

        // Convert given integer id to a base 62 number
        while (number > 0)
        {
            // use above map to store actual character
            // in short url
            shorturl.append(allowedCharacters[number % 62]);
            number = number / 62;
        }

        // Reverse shortURL to complete base conversion
        return shorturl.reverse().toString();
    }
}
