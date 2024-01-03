package com.octopus;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class CountDownLatchTest {
    public static void main(String[] args) {
        CountDownLatch downLatch = new CountDownLatch(10);
        downLatch.countDown();
        int maxRetryCount = 3;
        int retryCount = 0;
        while (!Thread.currentThread().isInterrupted() && retryCount < maxRetryCount) {
            try {
                boolean downLatchResult = downLatch.await(10, TimeUnit.SECONDS);
                if (downLatchResult) {
                    break;
                }
            } catch (InterruptedException ex) {
                retryCount++;
                Thread.interrupted();
            } catch (Exception ex) {
                retryCount++;
            }
        }
        if (retryCount >= maxRetryCount) {

            System.out.println("error");
        }


    }
}
