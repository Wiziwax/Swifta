package com.rapid.swifta.Utils;

import java.util.Random;

public class UniqueNumberGenerator {
    private static final Random RANDOM = new Random();

    public static int generateUniqueDigit(){
        return RANDOM.nextInt(10);
    }

    public static int generateUniqueDigitInRange(int minDigit, int maxDigit) {
        return minDigit + RANDOM.nextInt(maxDigit - minDigit + 1);
    }

    public static int generateUniqueNumberWithDigits(int numberOfDigits) {
        int min = (int) Math.pow(10, numberOfDigits - 1); //10^0 = 0
        int max = (int) Math.pow(10, numberOfDigits) - 1; //10^1 - 1 = 9
        return min + RANDOM.nextInt(max - min + 1); //1 + (9- 0+1)
    }
}
