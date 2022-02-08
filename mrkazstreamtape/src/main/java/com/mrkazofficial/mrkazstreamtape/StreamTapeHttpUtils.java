package com.mrkazofficial.mrkazstreamtape;

import static com.mrkazofficial.mrkazstreamtape.StreamTapeUtils.DEFAULT_USER_AGENT_2;
import static com.mrkazofficial.mrkazstreamtape.StreamTapeUtils.customizeUrl;
import static com.mrkazofficial.mrkazstreamtape.StreamTapeUtils.getVideoId;

import android.annotation.SuppressLint;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @Class StreamTapeHttpUtils
 * @Author MRKaZ
 * @Since 5:34 AM, 10/13/2021, 2021
 * @Origin Taprobana (LK)
 * @Copyright (c) 2021 MRKaZ . All rights reserved.
 */

public class StreamTapeHttpUtils {

    @SuppressLint("LongLogTag")
    public static String getHTTPResponse(String url) {

        String customizedUrl = customizeUrl(url);

        try {
            // OkHttp headers builder
            Headers headersClientBuild = Headers.of(headers(url));

            OkHttpClient mOkHttpClient;

            OkHttpClient.Builder mOkHttpClientBuilder = new OkHttpClient.Builder();
            mOkHttpClientBuilder.connectTimeout(30, TimeUnit.SECONDS);
            mOkHttpClientBuilder.readTimeout(30, TimeUnit.SECONDS);
            mOkHttpClientBuilder.writeTimeout(30, TimeUnit.SECONDS);

            mOkHttpClient = mOkHttpClientBuilder.build();

            Request request = new Request.Builder()
                    .url(customizedUrl)
                    .headers(headersClientBuild)
                    .get()
                    .build();

            Response responseData = mOkHttpClient.newCall(request).execute();
            String response = responseData.body().string();

            //System.out.println("(StreamTapeHttpUtils.java:55) --> " + response);

            // Check status
            if (!response.contains("Video not found!")) {
                return response;
            } else {
                return "Video not found!";
            }
        } catch (Exception ex) {
            return null;
        }
    }

    private static HashMap<String, String> headers(String url) {
        HashMap<String, String> addHeaders = new HashMap<>();
        addHeaders.put("authority", "stape.fun");
        addHeaders.put("method", "GET");
        addHeaders.put("path", "/e/" + getVideoId(url));
        addHeaders.put("referer", "https://streamtape.com/");
        addHeaders.put("user-agent", DEFAULT_USER_AGENT_2);
        return addHeaders;
    }
}
