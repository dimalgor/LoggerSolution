package com.gorban.iterviewtask;

import java.io.IOException;
import java.io.OutputStream;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class LoggerRunnable implements Runnable {

    private static final String LOG_FILE_NAME = "./fileA.log";
    private static final String USER_AGENT = "Mozilla/5.0";
    private static final String POST_URL = "http://example.com/";
    private static final String POST_PARAMS = "networkLog=";
    private final String logStr;

    public LoggerRunnable(String logStr) {
        this.logStr = logStr;
    }

    @Override
    public void run() {
        printLogIntoConsole();
        printLogIntoFile();
        printLogIntoNetwork();
    }

    private void printLogIntoConsole() {
        System.out.println(logStr);
    }

    void printLogIntoFile() {
        System.out.println("printLogIntoFile() - logStr: " + logStr);
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

    private void printLogIntoNetwork() {
        System.out.println("printLogIntoNetwork() - logStr: " + logStr);
        URL obj = null;
        OutputStream os = null;
        try {
            obj = new URL(POST_URL);
            HttpURLConnection httpURLConnection = (HttpURLConnection) obj.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setRequestProperty("User-Agent", USER_AGENT);
            httpURLConnection.setDoOutput(true);
            os = httpURLConnection.getOutputStream();

            StringBuilder stringBuilder = new StringBuilder(POST_PARAMS);
            stringBuilder.append(logStr);

            os.write(stringBuilder.toString().getBytes());
            os.flush();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (BindException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
