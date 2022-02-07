package com.mrkazofficial.mrkazstreamtape;

import static com.mrkazofficial.mrkazstreamtape.StreamTapeUtils.DEFAULT_USER_AGENT;

import android.annotation.SuppressLint;
import android.util.Log;

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

    private static String cookies;
    private static String userAgent;

    public static void setCookies(String cookies) {
        StreamTapeHttpUtils.cookies = cookies;
    }

    public static void setUserAgent(String userAgent) {
        StreamTapeHttpUtils.userAgent = userAgent != null ? userAgent : DEFAULT_USER_AGENT;
    }

    @SuppressLint("LongLogTag")
    public static String getHTTPResponse(String url) {
        try {

            if (cookies != null) {
                // Default timeout
                OkHttpClient.Builder builder = new OkHttpClient.Builder();
                builder.connectTimeout(30, TimeUnit.SECONDS);
                builder.readTimeout(30, TimeUnit.SECONDS);
                builder.writeTimeout(30, TimeUnit.SECONDS);

                OkHttpClient client = builder.build();

                // Headers map
                HashMap<String, String> addHeaders = new HashMap<>();
                addHeaders.put("Accept-Language", "en-US,en;q=0.9");
                addHeaders.put("Cookie", "cf_clearance=" + cookies);
                //addHeaders.put("Cookie", "_b=kube11; _csrf=855d645e56fc348f90e66983131b05d8d05bb03c02e53c3af5c060f5f170ecb3a:2:{i:0;s:5:\"_csrf\";i:1;s:32:\"yqTO__NKLFDiZhjsFLBi73PcZMXHNTsR\";}; cf_clearance=TDlcGeU_hgVTF_tC944cPq4O8bb7oR1vcgveHOAFh2I-1644185957-0-150; cf_session_id=4ml5m0bs8rl55biisnkakvdbau; sauth=a744821b16c66665da3dd11ed4343c05a5141bba86e08e1615c18850d35cd2bba:2:{i:0;s:5:\"sauth\";i:1;s:95:\"[\"fb837afc75a8cc35e512\",\"$2y$10$Y79.NT/6JvtmJIL3QoU1JesDxRzJX9NTi56IaHntU/9g9Gb0aacmK\",5184000]\";}");
                addHeaders.put("User-Agent", DEFAULT_USER_AGENT);
                //addHeaders.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/98.0.4758.82 Safari/537.36");

                // OkHttp headers builder
                Headers headers = Headers.of(addHeaders);

                // Request
                Request request = new Request.Builder()
                        .headers(headers)
                        .url(url)
                        .get()
                        .build();

                Response response = client.newCall(request).execute();

                // Check status
                if (response.body().toString().contains("Video not found!")) {
                    return "Video not found!";
                } else
                    return response.body().string();

            } else
                return "Cookies null!";
        } catch (Exception ex) {
            Log.e("StreamTapeExtractor.java:56", "Error  --> " + ex.getMessage());
            return null;
        }
    }
}
