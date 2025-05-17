package com.example.Java_Ngay8;

import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String[] args) {
        String pluginsDir = "plugins";

        CustomClassLoader classLoader = new CustomClassLoader(pluginsDir);

        classLoader.eagerLoadClasses();

        try {
            Class<?> pluginClass = classLoader.loadClass("com.pluginA.MyPlugin");
            System.out.println("Class loaded: " + pluginClass.getName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Queue<ExchangeRate> fetchQueue = new LinkedList<>();
        Queue<ExchangeRate> processQueue = new LinkedList<>();

        DataFetcher<ExchangeRate> fetcher = new DataFetcher<>(fetchQueue, new MockExchangeRateSource());
        DataProcessor<ExchangeRate> processor = new DataProcessor<>(fetchQueue, processQueue);
        DataDispatcher<ExchangeRate> dispatcher = new DataDispatcher<>(processQueue);

        Thread fetchThread = new Thread(fetcher);
        Thread processThread = new Thread(processor);
        Thread dispatchThread = new Thread(dispatcher);

        fetchThread.start();
        processThread.start();
        dispatchThread.start();

        try {
            Thread.sleep(5000);
            System.out.println("[Main] Average Value: " + processor.calculateAverage());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
