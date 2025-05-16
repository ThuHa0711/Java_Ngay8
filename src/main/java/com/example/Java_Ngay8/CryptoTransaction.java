package com.example.Java_Ngay8;

public class CryptoTransaction implements DataPoint {
    private long timestamp;
    private double amount;
    private String cryptoType;

    public CryptoTransaction(long timestamp, double amount, String cryptoType) {
        this.timestamp = timestamp;
        this.amount = amount;
        this.cryptoType = cryptoType;
    }

    @Override
    public long getTimestamp() {
        return timestamp;
    }

    @Override
    public double getValue() {
        return amount;
    }

    public String getCryptoType() {
        return cryptoType;
    }
}
