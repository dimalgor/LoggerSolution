package com.gorban.iterviewtask;

public class Logger {
    private static volatile Logger loggerInstance = null;

    private Logger() {}

    synchronized public static Logger getLogger() {
        if (loggerInstance == null) {
            synchronized (Logger.class) {
                if (loggerInstance == null) {
                    loggerInstance = new Logger();
                }
            }
        }

        return loggerInstance;
    }

    public void log(String str) {
        new Thread(new LoggerRunnable(str)).start();
    }
}
