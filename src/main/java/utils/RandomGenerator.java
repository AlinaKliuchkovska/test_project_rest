package utils;


import org.apache.commons.lang3.RandomStringUtils;

import java.util.Random;

public class RandomGenerator {
    public static String createRandomString(){
        String loverCharacters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String upperCharacters = "abcdefghijklmnopqrstuvwxyz";
        return RandomStringUtils.random(2, loverCharacters)
                +RandomStringUtils.random(2, upperCharacters)
                +System.currentTimeMillis();
    }

    public static Integer createRandomInteger(){
        Random random = new Random();
        return random.nextInt(1000000000);
    }
}
