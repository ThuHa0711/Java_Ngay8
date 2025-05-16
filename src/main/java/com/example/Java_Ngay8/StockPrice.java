package com.example.Java_Ngay8;

public class StockPrice implements DataPoint {
    private long timestamp;
    private double price;
    private String symbol;

    public StockPrice(long timestamp, double price, String symbol) {
        this.timestamp = timestamp;
        this.price = price;
        this.symbol = symbol;
    }

    @Override
    public long getTimestamp() {
        return timestamp;
    }

    @Override
    public double getValue() {
        return price;
    }

    public String getSymbol() {
        return symbol;
    }
}
