package com.mrkazofficial.streamtapeextractor;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.mrkazofficial.mrkazstreamtape.RequestListener;
import com.mrkazofficial.mrkazstreamtape.StreamTapeExtractor;
import com.mrkazofficial.mrkazstreamtape.StreamTapeModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
         * Keep this mind you have to initiate the Extractor before use you can do it following this
         * TODO : Initiate the StreamTapeExtractor !!If you missed this! extractor gives error!!
         */
        StreamTapeExtractor.initiate();

        /* Supported */
        //"https://stape.fun",
        //"https://scloud.online",
        //"https://streamtape.com",

        findViewById(R.id.btnExtract).setOnClickListener(v -> {
            StreamTapeExtractor.extractData(
                    // Your StreamTape Url
                    "YOUR_URL_HERE",
                    // Request Listener
                    new RequestListener() {
                        @Override
                        public void onResponse(ArrayList<StreamTapeModel> extractedUrls) {
                            if (extractedUrls != null) {
                                // Get urls, title, thumbnail from the StreamTape Model ArrayList
                                for (StreamTapeModel streamTapeModel : extractedUrls) {
                                    // Get title of the given url / video
                                    String getTitle = streamTapeModel.getTitle();
                                    Log.d("StreamTapeExtractor", "onResponse: getTitle " + getTitle);
                                    // Get the Thumbnail image / Screenshots @returns String url
                                    String getThumbnail = streamTapeModel.getThumbnail();
                                    Log.d("StreamTapeExtractor", "onResponse: getThumbnail " + getThumbnail);
                                    // Get download url
                                    String getDownloadUrl = streamTapeModel.getDownloadUrl();
                                    Log.d("StreamTapeExtractor", "onResponse: getDownloadUrl " + getDownloadUrl);
                                    // Get direct link
                                    String getDirectUrl = streamTapeModel.getDirectDownloadUrl();
                                    Log.d("StreamTapeExtractor", "onResponse: getDirectUrl " + getDirectUrl);
                                }
                            }
                        }

                        @Override
                        public void onError(String onError) {
                            Log.e("StreamTapeExtractor", "onError: " + onError);
                        }
                    });
        });
    }
}