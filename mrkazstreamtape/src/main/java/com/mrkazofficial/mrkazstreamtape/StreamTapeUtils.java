package com.mrkazofficial.mrkazstreamtape;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author MRKaZ
 * @since 3:33 AM, 5/10/2021, 2021
 */

public class StreamTapeUtils {

    public static final String DEFAULT_USER_AGENT = "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.99 Safari/537.36"; // Chrome/71.0.3578.99
    public static final String DEFAULT_USER_AGENT_2 = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36"; // Chrome/92.0.4515.131
    public static final String STREAM_TAPES_USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:88.0) Gecko/20100101 Firefox/88.0"; // Firefox/88.0

    public static final String VIDEO_NOT_FOUND = "Video not found!";

    /**
     * @param mValues String of the encoded
     * @return As a decoded string
     */
    public static String base64Decode(String mValues) {
        byte[] bytes = new byte[0];
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            bytes = Base64.getDecoder().decode(mValues);
        }
        return new String(bytes);
    }

    /**
     * @param url Url what tou want to redirect!?
     * @return As a String response
     */
    public static String redirectUrl(String url) {
        try {
            HttpURLConnection mHttpURLConnection = (HttpURLConnection) new URL(url).openConnection();
            mHttpURLConnection.setInstanceFollowRedirects(false);
            mHttpURLConnection.setRequestProperty("referer", "https://streamtape.com/");
            mHttpURLConnection.setRequestProperty("user-agent", STREAM_TAPES_USER_AGENT);
            URL secondURL = new URL(mHttpURLConnection.getHeaderField("Location"));
            URLConnection mURLConnection = secondURL.openConnection();
            //Log.w("StreamTapeExtractor.java:230", "redirectUrl  --> " + mURLConnection.getURL().toURI());
            return mURLConnection.getURL().toString();
        } catch (Exception e) {
            return null;
        }
    }


    /**
     * @param url Url of the response matched
     * @return As a String value
     * @implNote Match the Url token using Regex pattern
     */
    public static String matchTokenRegex(String url) {
        final String regex = base64Decode("JnRva2VuPShbXlxzXSopXCdcKQ==");

        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(url);

        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }

    /**
     * @param url video url
     * @return video id
     */
    public static String getVideoId(String url){
        return url.split("/")[4];
    }

    public static String customizeUrl(String url){
        if (url.contains("streamtape.com"))
            url = url.replace("streamtape.com", "stape.fun");

        if (url.contains("/v/"))
            url = url.replace("/v/", "/e/");

        return url;
    }
}
