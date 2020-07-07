package cn.decision01.bilibilivote.utils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpRequestUtil {
    public static String get(String _url) {
        HttpURLConnection connection = null;
        InputStream inputStream = null;
        BufferedReader bufferedReader = null;
        StringBuilder result = new StringBuilder();
        try {
            URL url = new URL(_url);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(10000);
            connection.setRequestProperty("Accept", "application/json");

            connection.connect();

            if (connection.getResponseCode() == 200) {
                inputStream = connection.getInputStream();
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    result.append(line);
                }
            }
            else {
                // do something
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            connection.disconnect();
        }
        return result.toString();
    }
}
