package com.example.nrs.entity;

import java.util.HashMap;
import java.util.Map;

public enum Rate {
  One("One",1.0f),
  Two("Two",2.0f),
  Three("Three",3.0f),
  Four("Four",4.0f),
  Five("Five",5.0f);

    private static final Map<String, Rate> BY_RATE = new HashMap<>();
    private static final Map<Integer, Rate> BY_RATE_NUMBER = new HashMap<>();

    static {
        for (Rate e : values()) {
            BY_RATE.put(e.rate, e);
            BY_RATE_NUMBER.put((int) e.rateNumber, e);
        }
    }

    public final String rate;
    public final float rateNumber;


    private Rate(String rate, float rateNumber) {
        this.rate = rate;
        this.rateNumber = rateNumber;
    }

    public static Rate valueOfRate(String rate) {
        return BY_RATE.get(rate);
    }

    public static Rate valueOfRateNumber(float rateNumber) {
        return BY_RATE_NUMBER.get(rateNumber);
    }

    public static void main(String[] args) {
        System.out.println(Rate.valueOfRateNumber(4));
    }
}
