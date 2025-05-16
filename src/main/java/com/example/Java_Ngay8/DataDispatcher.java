package com.example.Java_Ngay8;

import java.util.Queue;

public class DataDispatcher <T extends DataPoint> implements Runnable {
    private final Queue<T> processedQueue;

    public DataDispatcher(Queue<T> processedQueue) {
        this.processedQueue = processedQueue;
    }

    @Override
    public void run() {
        while (true) {
            T data;
            synchronized (processedQueue) {
                data = processedQueue.poll();
            }
            if (data != null) {
                System.out.println("[Dispatcher] Dispatching data: " + data.getValue());
            }
            try {
                Thread.sleep(100); // Giả lập gửi dữ liệu
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
