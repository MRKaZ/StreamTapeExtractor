package com.mrkazofficial.mrkazstreamtape;

/*
Copyright [2021] [MRKaZ]

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.*/

import static com.mrkazofficial.mrkazstreamtape.StreamTapeHttpUtils.getHTTPResponse;
import static com.mrkazofficial.mrkazstreamtape.StreamTapeUtils.VIDEO_NOT_FOUND;
import static com.mrkazofficial.mrkazstreamtape.StreamTapeUtils.matchTokenRegex;
import static com.mrkazofficial.mrkazstreamtape.StreamTapeUtils.redirectUrl;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;

import org.conscrypt.Conscrypt;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.security.Security;
import java.util.ArrayList;
import java.util.List;

/**
 * @author MRKaZ
 * @since 5:18 AM, 5/6/2021, 2021
 */

@SuppressLint({"LongLogTag", "StaticFieldLeak"})
public class StreamTapeExtractor {

    /**
     * @implNote This is an important function to register network thread policy
     */
    public static void initiate() {
        StrictMode.ThreadPolicy threadPolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(threadPolicy);
        Security.insertProviderAt(Conscrypt.newProvider(), 1);
    }


    /**
     * @param Url             of the video in StreamTape to extract
     * @param RequestListener for the listening the given data from the Volley
     */
    @SuppressLint("SetJavaScriptEnabled")
    public static void extractData(String Url, RequestListener RequestListener) {
        // <StreamTapeModel> Model array list
        ArrayList<StreamTapeModel> mStreamTapeModelArrayList = new ArrayList<>();
        // <StreamTapeModel> Model
        StreamTapeModel mStreamTapeModel = new StreamTapeModel();
        try {
            // Getting HTTP response
            String response = getHTTPResponse(Url);
            if (response != null) {
                if (!response.contains(VIDEO_NOT_FOUND)) {
                    // Set extracted Download link to model class
                    mStreamTapeModel.setDownloadUrl(generatedDownloadLink(response));
                    // Set Direct Download link to model class
                    mStreamTapeModel.setDirectDownloadUrl(redirectUrl(generatedDownloadLink(response)));
                    // Set extracted Title link to model class
                    mStreamTapeModel.setTitle(extractTitle(response));
                    // Set extracted Thumbnail image link to model class
                    mStreamTapeModel.setThumbnail(extractThumbnail(response));
                    // Add all the data in to the StreamTapeModel ArrayList
                    mStreamTapeModelArrayList.add(mStreamTapeModel);
                    // Adding data values to the RequestListener
                    RequestListener.onResponse(mStreamTapeModelArrayList);
                } else
                    RequestListener.onError("Video not found!. Please check your url and try again!.");
            } else
                RequestListener.onError("Response null");
        } catch (Exception exception) {
            // OnError
            //exception.printStackTrace();
            RequestListener.onError(exception.getMessage());
        }
    }

    /**
     * @param Response Response to get the Title of the given url
     *                 [INFORMATION]
     *                 - Title position --> 1
     *                 - Description position --> 2
     *                 - Type position --> 3
     *                 - Actual url position --> 4
     */
    private static String extractTitle(String Response) {
        if (Response != null) {
            Document responseDoc = Jsoup.parse(Response);
            Elements metaElements = responseDoc.select("meta");

            // Get the title
            Element titleElement = metaElements.get(1);
            // Get the Description
            //Element descElement = metaElements.get(2);
            // Get the Type
            //Element typeElement = metaElements.get(3);
            // Get the Actual url
            //Element aUrlElement = metaElements.get(4);

            // Title with extension
            String titleWithExt = titleElement.attr("content");
            // Title without extension
            //String titleWithoutExt = titleWithExt.substring(0, titleWithExt.lastIndexOf("."));

            //Log.w("StreamTapeExtractor.java:89", "Title With Ext --> " + titleWithExt);
            //Log.w("StreamTapeExtractor.java:89", "Title Without Ext --> " + titleWithoutExt);
            //Log.w("StreamTapeExtractor.java:89", "Description --> " + descElement.toString());
            //Log.w("StreamTapeExtractor.java:89", "Type --> " + typeElement.toString());
            //Log.w("StreamTapeExtractor.java:89", "Actual url --> " + aUrlElement.toString());

            return titleWithExt.substring(0, titleWithExt.lastIndexOf("."));
        }
        return null;
    }

    /**
     * @param Response Response to get the Thumbnail of the given url
     */
    private static String extractThumbnail(String Response) {
        if (Response != null) {
            // Parse the response using JSOUP
            Document responseDoc = Jsoup.parse(Response);
            // Parse the Thumbnail link
            Element main_video_element = responseDoc.getElementById("mainvideo");
            if (main_video_element != null) {
                //String posterLink = main_video_element.attr("poster");
                //Log.w("StreamTapeExtractor.java:139", "posterLink  --> " + posterLink);
                return main_video_element.attr("poster");
            }
        }
        return null;
    }

    /**
     * @param mResponse Response to generate the downloadable video link
     */
    private static String generatedDownloadLink(String mResponse) {

        String globalValue = "https:/";

        if (mResponse != null) {
            // Parse the response using JSOUP
            Document responseDoc = Jsoup.parse(mResponse);

            // Get the download token
            List<String> getToken = getToken(mResponse);

            // Actual link's... Redirecting to streamtape_do_not_delete.mp4
            //Element ideoo_link_elements = responseDoc.getElementById("ideoolink");
            //String strIdeooLink = ideoo_link_elements.text();

            Element robot_link_elements = responseDoc.getElementById("robotlink");
            if (robot_link_elements != null) {
                String strRobotLink = robot_link_elements.text();
                strRobotLink = strRobotLink.substring(0, strRobotLink.lastIndexOf("="));

                if (getToken != null) {
                    return globalValue + strRobotLink + "=" + getToken.get(getToken.size() - 1) + "&stream=1";
                } else
                    return null;
            }
        } else
            return null;

        return null;
    }

    /**
     * @param mResponse HTML response to parse Token
     * @return As a String with parsed Token
     */
    private static List<String> getToken(String mResponse) {

        Document responseDoc = Jsoup.parse(mResponse);
        // Search CSS Query <script> </script>
        Elements elements = responseDoc.select("script");

        if (elements.toString().contains("&token=")) {
            List<String> listTokens = new ArrayList<>();
            listTokens.add(matchTokenRegex(elements.toString()));
            return listTokens;
        }

        return null;
    }
}
