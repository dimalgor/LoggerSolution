package com.gorban.iterviewtask;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Logger {
    private static volatile Logger loggerInstance = null;

    private Logger(){}

    public static Logger getLogger() {
        if (loggerInstance == null){
            synchronized (Logger.class){
                if (loggerInstance == null){
                    loggerInstance = new Logger();
                }
            }
        }

        return loggerInstance;
    }

    public void log(String str){
        List<Runnable> logTasks = new ArrayList<>();
        logTasks.add(new ConsoleLoggerRunnable(str));
        logTasks.add(new FileLoggerRunnable(str));
        logTasks.add(new NetworkLoggerRunnable(str));

        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(3);

        for (Runnable runnableTask : logTasks){
            executor.execute(runnableTask);
        }

        executor.shutdown();
    }
}
