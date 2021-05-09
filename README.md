[![](https://jitpack.io/v/MRKaZ/StreamTapeExtractor.svg)](https://jitpack.io/#MRKaZ/StreamTapeExtractor)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

<!-- PROJECT LOGO -->
<br />
<p align="center">
  <a href="StreamTape Extractor">
    <img src="https://image.flaticon.com/icons/png/512/93/93989.png" alt="Logo" width="200" height="200">
  </a>

  <h3 align="center">StreamTape Extractor</h3>

  <p align="center">
    A simple extractor for StreamTape
    <br />
</p>

<!-- ABOUT THE PROJECT -->
# About The Project

<div align="center">
	<img src="https://i.imgur.com/IW7FUuL.png" width="1024">
</div>

##
This is a Simple StreamTape Extractor for the download video files direct using very usefull library called Volley.
**Volley is an HTTP library that makes networking for Android apps easier and, most importantly, faster.**
im using Volley to get the response for the HTTP request, Split the response and using regex patterns to match the
direct download url, title and the thumbnail image url if contains in the given response. Use it at your own risk.

## Based Config Method..

**StreamTape Extractor** Based on This Config Method; Follow This:

###### Activity uses

```java
 StreamTapeExtractor.getHTTPRequest(@NonNull Activity Activity,
                                    String Url,
                                    RequestListener RequestListener)
```

###### Context uses

```java
 StreamTapeExtractor.getHTTPRequest(@NonNull Context Context,
                                    String Url,
                                    RequestListener RequestListener)
```


### Prerequisites

Add this in your root `build.gradle` file (**not** in your `build.gradle.app` add it into your module `build.gradle` file):

```gradle
allprojects {
	repositories {
		...
		maven { url "https://jitpack.io" }
	}
}
```

### Dependency

Add this to your module's `build.gradle` file:

```gradle
dependencies {
	...
	 implementation 'com.github.MRKaZ:StreamTapeExtractor:v1.0'
}
```

### Usage

**You can extract the links and get the data responses following this**

```java
 StreamTapeExtractor.getHTTPRequest(
                    // Context or Activity
                    MainActivity.this,
                    // StreamTape Url
                    "https://streamtape.com/e/02W0ORe2ZgSbooP/",
                    // RequestListener 
                    new RequestListener() {
                        @Override
                        public void onResponse(ArrayList<StreamTapeModel> extractedUrls) {
                            if (extractedUrls != null) {
                                // Get urls, title, thumbnail from the StreamTape Model ArrayList
                                for (StreamTapeModel streamTapeModel : extractedUrls) {
                                    // Get download url
                                    String getDownloadUrl = streamTapeModel.getDownloadUrl();
                                    Log.d("StreamTapeExtractor", "onResponse: getDownloadUrl " + getDownloadUrl);
                                    // Get title of the given url / video
                                    String getTitle = streamTapeModel.getTitle();
                                    Log.d("StreamTapeExtractor", "onResponse: getTitle " + getTitle);
                                    // Get the Thumbnail image / Screenshots @returns String url
                                    String getThumbnail = streamTapeModel.getThumbnail();
                                    Log.d("StreamTapeExtractor", "onResponse: getThumbnail " + getThumbnail);
                                }
                            }
                        }

                        @Override
                        public void onError(String onError) {
                            Log.e("StreamTapeExtractor", "onError: " + onError);
                        }
                    });
```

##
### Built With (Credits)

This app built with the **Volley** HTTP library
* [Volley](https://github.com/google/volley)


## Disclaimer
**This repository is for research purposes only, the use of this code is your responsibility.**
**I take NO responsibility and/or liability for how you choose to use any of the source code available here.**
**By using any of the files available in this repository, you understand that you are AGREEING TO USE AT YOUR OWN RISK. Once again, ALL files available here are for EDUCATION and/or RESEARCH purposes ONLY.**

<!-- LICENSE -->
## License

Distributed under the Apache License. See `LICENSE` for more information.

[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
