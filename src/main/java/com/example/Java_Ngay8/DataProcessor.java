package com.example.Java_Ngay8;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;

public class DataProcessor <T extends DataPoint> implements Runnable {
    private final Queue<T> inputQueue;
    private final Queue<T> outputQueue;
    private final List<T> processedData = new ArrayList<>(); // Danh sách lưu trữ lịch sử


    public DataProcessor(Queue<T> inputQueue, Queue<T> outputQueue) {
        this.inputQueue = inputQueue;
        this.outputQueue = outputQueue;
    }

    @Override
    public void run() {
        while (true) {
            T data;
            synchronized (inputQueue) {
                data = inputQueue.poll();
            }
            if (data != null) {
                System.out.println("[Processor] Processing: " + data.getValue());

                // Sau khi xử lý, đưa vào hàng đợi đầu ra
                synchronized (outputQueue) {
                    outputQueue.offer(data);
                }
            }
            try {
                Thread.sleep(200); // Giả lập thời gian xử lý
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    // Lọc dữ liệu theo thời gian
    public List<T> filterByTimeRange(List<T> data, long from, long to) {
        List<T> filteredData = new ArrayList<>();
        for (T d : data) {
            if (d.getTimestamp() >= from && d.getTimestamp() <= to) {
                filteredData.add(d);
            }
        }
        return filteredData;
    }

//    // Tính toán trung bình giá trị
//    public double calculateAverage(List<T> data) {
//        if (data.isEmpty()) {
//            return 0.0;
//        }
//        double sum = 0.0;
//        for (T d : data) {
//            sum += d.getValue();
//        }
//        return sum / data.size();
//    }

    // ✅ Thêm phương thức tính trung bình
    public double calculateAverage() {
        synchronized (processedData) {
            if (processedData.isEmpty()) {
                return 0.0;
            }
            double sum = 0.0;
            for (T d : processedData) {
                sum += d.getValue();
            }
            return sum / processedData.size();
        }
    }

    // Tìm giá trị lớn nhất
    public double findMaxValue(List<T> data) {
        if (data.isEmpty()) {
            return Double.NaN;
        }
        double max = Double.NEGATIVE_INFINITY;
        for (T d : data) {
            if (d.getValue() > max) {
                max = d.getValue();
            }
        }
        return max;
    }

    // Tìm giá trị nhỏ nhất
    public double findMinValue(List<T> data) {
        if (data.isEmpty()) {
            return Double.NaN;
        }
        double min = Double.POSITIVE_INFINITY;
        for (T d : data) {
            if (d.getValue() < min) {
                min = d.getValue();
            }
        }
        return min;
    }
}
