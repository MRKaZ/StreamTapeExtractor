[![](https://jitpack.io/v/MRKaZ/StreamTapeExtractor.svg)](https://jitpack.io/#MRKaZ/StreamTapeExtractor)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

<!-- PROJECT LOGO -->
<br />
<p align="center">
  <a href="StreamTape Extractor">
    <img src="https://image.flaticon.com/icons/png/512/93/93989.png" alt="Logo" width="200" height="200">
  </a>

  <h3 align="center">StreamTape Extractor</h3>

 
</p>

<div align="center">
	<img src="https://streamtape.com/images/Logo@2x.png" width="512">
</div>

<!-- ABOUT THE PROJECT -->
# About The Project

<div align="center">
	<img src="https://i.imgur.com/E8zobpe.png" width="1024">
</div>

##
This is a Simple StreamTape Extractor for the download video files direct using **Jsoup** Html parser library.
Getting a HTTP response with the given url and matched it with some Regex patterns to extract the 
**Title, Tumbnail, Download url and the Direct download url**

## Based Config Method..

**StreamTape Extractor** You can follow this config method to extract video data:

###### Config

```java
 StreamTapeExtractor.extractData(String Url,
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
	 implementation 'com.github.MRKaZ:StreamTapeExtractor:v1.2'
}
```

### Supports

**Supported url types ;**

| Supported Url Types | Status |
| --- | --- |
| **https://streamtape.com/e/XXXXXXXXXXXXXXX/** | :heavy_check_mark:
| **https://streamtape.com/v/XxXxXxXxXxXxXx/XXXXX.XXXXX.XXXXX.XXXXX.XXXXX-XXXXX.XXX** | :heavy_check_mark:

### Usage

**You can extract the video data following this**

### Initiate the Extractor
```java
/* 
 * Keep this mind you have to initiate the Extractor before use you can do it following this
 * Initiate the StreamTapeExtractor !!If you missed this extractor gives error!!
 */
 StreamTapeExtractor.initiate();

```

### Extract the video data from the given url
```java
 // Extract the video data
 StreamTapeExtractor.extractData(
                    // StreamTape Url
                    "https://streamtape.com/e/XXXXXXXXXXXXXXX/",
                    // RequestListener 
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
```

##
### Credits

This app built with the **Jsoup** HTML parser library
* [JSOUP](https://github.com/jhy/jsoup)

### Changelog
```
[v1.2] [14/10/2021]
+ Fixed url extraction error!.
+ Removed and Changed some extraction methods.
+ Removed and Changed some usage methods.
- Migrated Volley dependency.
+ Added JSOUP (Html parser) dependency.
[Methods]
+ Added new Direct url method
- Removed (Context, Activity).

[v1.0] [10/05/2021]

+ First release!.
+ Simple extraction method for the StreamTape.
+ Extract Url, Title & Thumbnail Image Url.

```

## Disclaimer
**This repository is for research purposes only, the use of this code is your responsibility.**
**I take NO responsibility and/or liability for how you choose to use any of the source code available here.**
**By using any of the files available in this repository, you understand that you are AGREEING TO USE AT YOUR OWN RISK. Once again, ALL files available here are for EDUCATION and/or RESEARCH purposes ONLY.**

<!-- LICENSE -->
## License

Distributed under the Apache License. See `LICENSE` for more information.

[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
