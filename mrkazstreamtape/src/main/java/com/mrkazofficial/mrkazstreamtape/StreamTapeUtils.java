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

    public static final String DEFAULT_USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/98.0.4758.82 Safari/537.36";

    public static final String VIDEO_NOT_FOUND = "Video not found!";
    public static final String COOKIE_NULL = "\"Cookies null!\"";

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
