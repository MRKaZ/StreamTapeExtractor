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
            URL secondURL = new URL(mHttpURLConnection.getHeaderField("Location"));
            URLConnection mURLConnection = secondURL.openConnection();
            //Log.w("StreamTapeExtractor.java:230", "redirectUrl  --> " + mURLConnection.getURL().toURI());
            return mURLConnection.getURL().toString();
        } catch (Exception e) {
            return null;
        }
    }


    /**
     * @implNote Match the downloadable url using Regex pattern
     * @param url  Url of the video
     * @return String list
     */
    public static List<String> matchUrlRegex(String url) {
        List<String> finalMatchedList = new ArrayList<>();

        final String regex = base64Decode("XCgnKFteXHNdKiknXCkuc3Vic3RyaW5n");

        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(url);

        while (matcher.find())
            finalMatchedList.add(matcher.group(1));

        return finalMatchedList;
    }


    /**
     * @implNote Match the Url token using Regex pattern
     * @param url Url of the response matched
     * @return As a String value
     */
    public static String matchTokenRegex(String url) {
        final String regex = base64Decode("JnRva2VuPShbXlxzXSop");

        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(url);

        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }

}
