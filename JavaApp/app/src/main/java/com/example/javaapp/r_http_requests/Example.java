package com.example.javaapp.r_http_requests;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class Example {
    private static final String URL_NETWORK = "https://api.kiparo.com/static/test.json";

    public static void main(String[] args) {
        InputStream inputStream = null;
        FileOutputStream outputStream = null;
        try {
            URL url = new URL(URL_NETWORK);
            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();
            int responseCode = httpsURLConnection.getResponseCode();
            if (responseCode == HttpsURLConnection.HTTP_OK) {
                inputStream = httpsURLConnection.getInputStream();

                File file = new File("test.json");
                outputStream = new FileOutputStream(file);

                int byteRead = -1;
                byte[] buffer = new byte[1024];
                while ((byteRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, 500);
                }
            }
        } catch (IOException e) {
            System.out.println(e.toString());
        } finally {
            try {
                inputStream.close();
                outputStream.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
