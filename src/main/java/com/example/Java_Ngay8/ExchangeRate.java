package com.example.Java_Ngay8;

public class ExchangeRate implements DataPoint {
    private long timestamp;
    private double rate;
    private String currencyPair;

    public ExchangeRate(long timestamp, double rate, String currencyPair) {
        this.timestamp = timestamp;
        this.rate = rate;
        this.currencyPair = currencyPair;
    }

    @Override
    public long getTimestamp() {
        return timestamp;
    }

    @Override
    public double getValue() {
        return rate;
    }

    public String getCurrencyPair() {
        return currencyPair;
    }
}
