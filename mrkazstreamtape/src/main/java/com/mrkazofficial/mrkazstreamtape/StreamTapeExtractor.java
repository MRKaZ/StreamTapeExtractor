package com.mrkazofficial.mrkazstreamtape;

/*Copyright [2021] [MRKaZ]

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.*/

import android.app.Activity;
import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;

import static com.mrkazofficial.mrkazstreamtape.StreamTapeUtils.StreamTapeThumbRegexEnd;
import static com.mrkazofficial.mrkazstreamtape.StreamTapeUtils.StreamTapeThumbRegexStart;
import static com.mrkazofficial.mrkazstreamtape.StreamTapeUtils.StreamTapeTitleRegexEnd;
import static com.mrkazofficial.mrkazstreamtape.StreamTapeUtils.StreamTapeTitleRegexStart;
import static com.mrkazofficial.mrkazstreamtape.StreamTapeUtils.StreamTapeUrlRegexEnd;
import static com.mrkazofficial.mrkazstreamtape.StreamTapeUtils.StreamTapeUrlRegexStart;

/**
 * @author MRKaZ
 * @since 5:18 AM, 5/6/2021, 2021
 */

public class StreamTapeExtractor {

    /**
     * @param Context         Context to the Volley request
     * @param Url             Url of the StreamTape to extract
     * @param RequestListener RequestListener for the listening the given data from the Volley
     */
    private static void getHTTPRequest(Context Context, String Url, RequestListener RequestListener) {
        //mContext = Context;
        RequestQueue mRequestQueue = Volley.newRequestQueue(Context);
        // Request a string response from the provided URL.
        StringRequest mStringRequest = new StringRequest(Request.Method.GET, Url,
                response -> {
                    // Display the response string.
                    if (response != null) {
                        ArrayList<StreamTapeModel> mStreamTapeModelArrayList = new ArrayList<>();
                        StreamTapeModel mStreamTapeModel = new StreamTapeModel();
                        // Set extracted Download link to model class
                        mStreamTapeModel.setSetDownloadUrl(generatedDownloadLink(response));
                        // Set extracted Title link to model class
                        mStreamTapeModel.setTitle(extractTitle(response));
                        // Set extracted Thumbnail image link to model class
                        mStreamTapeModel.setThumbnail(extractThumbnail(response));
                        // Add all the data in to the StreamTapeModel ArrayList
                        mStreamTapeModelArrayList.add(mStreamTapeModel);
                        // Adding data values to the RequestListener
                        RequestListener.onResponse(mStreamTapeModelArrayList);
                    }
                }, error -> {
            // On Error send error message to extractor response
            RequestListener.onError(error.getMessage());
        });
        // Add the request to the RequestQueue.
        mRequestQueue.add(mStringRequest);
    }

    /**
     * @param Context         Context to the Volley request
     * @param Url             Url of the StreamTape to extract
     * @param RequestListener RequestListener for the listening the given data from the Volley
     */
    public static void getHTTPRequest(Activity Activity, String Url, RequestListener RequestListener) {
        //mActivity = Activity;
        RequestQueue mRequestQueue = Volley.newRequestQueue(Activity);
        // Request a string response from the provided URL.
        StringRequest mStringRequest = new StringRequest(Request.Method.GET, Url,
                response -> {
                    // Display the response string.
                    if (response != null) {
                        ArrayList<StreamTapeModel> mStreamTapeModelArrayList = new ArrayList<>();
                        StreamTapeModel mStreamTapeModel = new StreamTapeModel();
                        // Set extracted Download link to model class
                        mStreamTapeModel.setSetDownloadUrl(generatedDownloadLink(response));
                        // Set extracted Title link to model class
                        mStreamTapeModel.setTitle(extractTitle(response));
                        // Set extracted Thumbnail image link to model class
                        mStreamTapeModel.setThumbnail(extractThumbnail(response));
                        // Add all the data in to the StreamTapeModel ArrayList
                        mStreamTapeModelArrayList.add(mStreamTapeModel);
                        // Adding data values to the RequestListener
                        RequestListener.onResponse(mStreamTapeModelArrayList);
                    }
                }, error -> {
            // On Error send error message to extractor response
            RequestListener.onError(error.getMessage());
        });
        // Add the request to the RequestQueue.
        mRequestQueue.add(mStringRequest);
    }

    /**
     * @param Response Response to get the Title of the given url
     */
    private static String extractTitle(String Response) {
        String mFinalTitleResults = null;
        if (Response != null) {
            String mResponseStart = StreamTapeTitleRegexStart;
            int mIdx = Response.indexOf(mResponseStart);
            if (mIdx != -1) {
                Response = Response.substring(mIdx + mResponseStart.length());
                String mSubstring = Response.substring(0, Response.indexOf(StreamTapeTitleRegexEnd));
                mFinalTitleResults = replaceCharacters(mSubstring);
            }
        }
        return mFinalTitleResults;
    }

    /**
     * @param Response Response to get the Thumbnail of the given url
     */
    private static String extractThumbnail(String Response) {
        String mFinalThumbResults = null;
        if (Response != null) {
            String mResponseStart = StreamTapeThumbRegexStart;
            int mIdx = Response.indexOf(mResponseStart);
            if (mIdx != -1) {
                Response = Response.substring(mIdx + mResponseStart.length());
                String mSubstring = Response.substring(0, Response.indexOf(StreamTapeThumbRegexEnd));
                mFinalThumbResults = replaceCharacters(mSubstring);
            }
        }
        return mFinalThumbResults;
    }

    /**
     * @param Response Response to get the generate the final download link
     */
    private static String generatedDownloadLink(String Response) {
        String mFinalUrlResults = null;
        if (Response != null) {
            String mResponseStart = StreamTapeUrlRegexStart;
            int mIdx = Response.indexOf(mResponseStart);
            if (mIdx != -1) {
                Response = Response.substring(mIdx + mResponseStart.length());
                String mSubstring = Response.substring(0, Response.indexOf(StreamTapeUrlRegexEnd));
                mFinalUrlResults = replaceCharacters(mSubstring);
            }
        }

        return mFinalUrlResults;
    }

    /**
     * @param Results Results to replace the Meta Characters
     */
    private static String replaceCharacters(String Results) {
        String mReplaceInnerHTML = Results.replace(".innerHTML = \"", "https:");
        String mReplaceMeta = mReplaceInnerHTML.replace("\" + ", "");
        return mReplaceMeta.replaceAll("'", "");
    }
}
