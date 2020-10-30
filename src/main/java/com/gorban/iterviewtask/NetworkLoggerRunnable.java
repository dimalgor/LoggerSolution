package com.gorban.iterviewtask;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

class NetworkLoggerRunnable implements Runnable {
    private final String logStr;
    private static final String USER_AGENT = "Mozilla/5.0";
    private static final String POST_URL = "http://example.com/";
    private static final String POST_PARAMS = "networkLog=";

    NetworkLoggerRunnable(String logStr) {
        this.logStr = logStr;
    }

    @Override
    public void run() {
        try {
            sendLogInPost();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private void sendLogInPost() throws Exception {
        URL obj = new URL(POST_URL);
        HttpURLConnection httpURLConnection = (HttpURLConnection) obj.openConnection();
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setRequestProperty("User-Agent", USER_AGENT);
        httpURLConnection.setDoOutput(false);
        OutputStream os = httpURLConnection.getOutputStream();

        StringBuilder stringBuilder = new StringBuilder(POST_PARAMS);
        stringBuilder.append(logStr);

        os.write(stringBuilder.toString().getBytes());
        os.flush();
        os.close();
    }
}
