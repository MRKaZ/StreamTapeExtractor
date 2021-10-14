package com.mrkazofficial.mrkazstreamtape;

import android.annotation.SuppressLint;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @Class StreamTapeHttpUtils
 * @Author MRKaZ
 * @Since 5:34 AM, 10/13/2021, 2021
 * @Origin Taprobana (LK)
 * @Copyright (c) 2021 MRKaZ . All rights reserved.
 */

public class StreamTapeHttpUtils {

    @SuppressLint("LongLogTag")
    public static String getHTTPResponse(String Url) {
        HttpURLConnection mHttpURLConnection;
        StringBuilder source = new StringBuilder();
        try {
            mHttpURLConnection = (HttpURLConnection) new URL(Url).openConnection();
            mHttpURLConnection.setRequestMethod("GET");
            //mHttpURLConnection.setRequestProperty("cookie", "cf_session_id=3mq0dsbu8qb4h0jvarngoj2ce7; _csrf=df2b2ad1699a195edd1442c9999a9856a768c2efefd29311145e2b021e60dffda:2:{i:0;s:5:\"_csrf\";i:1;s:32:\"PhTcc-jexPjiTsTJgaumCwYKjhYIfXpT\";}; _b=kube17");

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(mHttpURLConnection.getInputStream()));

            String readLine;
            while ((readLine = bufferedReader.readLine()) != null)
                source.append(readLine);

        } catch (IOException ex) {
            Log.e("StreamTapeExtractor.java:56", "Error  --> " + ex.getMessage());
        }
        return source.toString();
    }
}
