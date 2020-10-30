package com.gorban.iterviewtask;

import org.junit.jupiter.api.Test;

class LoggerTest {


    @Test
    void loggerTest() {

        Runnable runnableOne = new Runnable() {
            @Override
            public void run() {
                Logger.getLogger().log("Thread one log ");
            }
        };

        Runnable runnableTwo = new Runnable() {
            @Override
            public void run() {
                Logger.getLogger().log("Thread two log ");
            }
        };

        Runnable runnableThree = new Runnable() {
            @Override
            public void run() {
                Logger.getLogger().log("Thread three log ");
            }
        };

        new Thread(runnableOne).start();
        new Thread(runnableTwo).start();
        new Thread(runnableThree).start();

    }
}
