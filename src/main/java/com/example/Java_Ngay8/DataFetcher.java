package com.example.Java_Ngay8;

import java.util.Queue;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class DataFetcher <T extends DataPoint> implements Runnable {
    private final Queue<T> queue;
    private final DataSource<T> dataSource;

    public DataFetcher(Queue<T> queue, DataSource<T> dataSource) {
        this.queue = queue;
        this.dataSource = dataSource;
    }

    @Override
    public void run() {
        Random random = new Random();
        while (true) {
            T data = dataSource.fetchData();
            synchronized (queue) {
                queue.offer(data);
                System.out.println("[Fetcher] Fetched: " + data.getValue());
            }
            try {
                TimeUnit.MILLISECONDS.sleep(random.nextInt(500));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
