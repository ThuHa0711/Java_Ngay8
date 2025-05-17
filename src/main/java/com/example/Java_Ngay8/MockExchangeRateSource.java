package com.example.Java_Ngay8;

import java.util.Random;

public class MockExchangeRateSource implements DataSource<ExchangeRate> {
    private final Random random = new Random();

    @Override
    public ExchangeRate fetchData() {
        long timestamp = System.currentTimeMillis();
        double value = 1.0 + (random.nextDouble() * 0.1);
        return new ExchangeRate(timestamp, value, "USD/EUR");
    }
}
