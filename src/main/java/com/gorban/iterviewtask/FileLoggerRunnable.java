package com.gorban.iterviewtask;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class FileLoggerRunnable implements Runnable{
    private final String LOG_FILE_NAME = "./fileA.log";
    private final String logStr;

    public FileLoggerRunnable(String str) {
        this.logStr = str;
    }

    @Override
    public void run() {
        try {
            Files.writeString(Paths.get(LOG_FILE_NAME),
                    logStr,
                    StandardCharsets.UTF_8,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
