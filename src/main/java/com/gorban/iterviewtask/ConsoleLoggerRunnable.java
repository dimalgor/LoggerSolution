package com.gorban.iterviewtask;

class ConsoleLoggerRunnable implements Runnable {
    private final String logStr;

    public ConsoleLoggerRunnable(String str){
        this.logStr = str;
    }

    @Override
    public void run() {
        System.out.println(logStr);
    }
}
