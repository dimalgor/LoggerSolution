package com.gorban.iterviewtask;

import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

class LoggerTest {


    @Test
    void loggerTest() throws InterruptedException {

        Runnable runnableOne = new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 2; i++){
                    Logger.getLogger().log("Thread 1 message: " + i + "; ");
                }
            }
        };

        Runnable runnableTwo = new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 2; i++){
                    Logger.getLogger().log("Thread 2 message: " + i + "; ");
                }
            }
        };

        Runnable runnableThree = new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 2; i++){
                    Logger.getLogger().log("Thread 3 message: " + i + "; ");
                }
            }
        };

        new Thread(runnableOne).start();
        new Thread(runnableTwo).start();
        new Thread(runnableThree).start();

        TimeUnit.MILLISECONDS.sleep(1000);

    }
}
