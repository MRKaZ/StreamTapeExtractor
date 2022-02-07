[![](https://jitpack.io/v/MRKaZ/StreamTapeExtractor.svg)](https://jitpack.io/#MRKaZ/StreamTapeExtractor)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0) ![API](https://img.shields.io/badge/%20StreamTapeExtractor-v1.3-brightgreen) ![API](https://img.shields.io/badge/API-24%2B-brightgreen.svg)


<!-- PROJECT LOGO -->
<br />
<p align="center">

  <h3 align="center">UNOFFICIAL DOWNLOAD AND STREAMING LINK EXTRACTOR FOR STREAMTAPE</h3>
 
</p>

<div align="center">
	<img src="https://streamtape.com/images/Logo@2x.png" width="512">
</div>

<!-- ABOUT THE PROJECT -->
# About The Project

<div align="center">
	<img src="https://i2.paste.pics/85fc8c015ab57d25d8f956970c074c22.png" width="1024">
</div>

##
This is a Simple StreamTape Extractor for the download video files direct using **Jsoup** Html parser library.
Getting a HTTP response with the given url and matched it with some Regex patterns to extract the
**Title, Tumbnail, Download url and the Direct download url**.

## Based Config Method..

**TODO** You can follow this config method to extract video data:

###### Config

```java
 StreamTapeExtractor.extractData(String Url,
                                 RequestListener RequestListener)
```

## Prerequisites

Add this in your root `build.gradle` file (**not** in your `build.gradle.app` add it into your module `build.gradle` file):

```gradle
allprojects {
	repositories {
		...
		maven { url "https://jitpack.io" }
	}
}
```

## Dependency

Add this to your module's `build.gradle` file:

```gradle
dependencies {
	...
	 implementation 'com.github.MRKaZ:StreamTapeExtractor:v1.3'
}
```

## Supports

**Supported url types ;**

| Supported Url Types | Status |
| --- | --- |
| **https://streamtape.com/e/XXXXXXXXXXXXXXX/** | :heavy_check_mark:
| **https://streamtape.com/v/XxXxXxXxXxXxXx/XXXXX.XXXXX.XXXXX.XXXXX.XXXXX-XXXXX.XXX** | :heavy_check_mark:


## Setup Cookies
#### First of all you have to setup Cookies. And also you can follow this steps down below at the screenshots!. Follow each numbers.
#### Pictures are too small in the table. Press the picture to see in big view!
| Sceenshots | Explanation |
| --- | --- |
| <img src="https://i2.paste.pics/a787343d15e074a489a3f53debf74341.png" width="350"> | 01.) Go and press the **Lock** icon in the top left. |
| <img src="https://i2.paste.pics/e7757637655d7f32e73399e7ef37db29.png" width="350"> | 02.) Press the **Cookies** tab. |
| <img src="https://i2.paste.pics/82dd45401049efb27f8cadfc1f62fd97.png" width="350"> | 03.) After pressed the **Cookies** it will display a dialog!, Go and tap the domaintab go to domain.<br>04.) From the drop down menu select **Cookies** bar.|
| <img src="https://i2.paste.pics/cefe30c1298c7646ba52d144f4b3e1fd.png" width="350"> | 05.) Scroll down and find the `cf_clearance` cookie. <br> 06.) And copy that all content. |
| <img src="https://i2.paste.pics/859c2eec3c56985c8eec67eefeebd548.png" width="350"> | 07.) Paste it on the `setCookies` area in the project. |

## Usage

**You can extract the video data following this**

### Initiate the Extractor
```java
/*
* Keep this mind you have to initiate the Extractor before use you can do it following this
* TODO : Initiate the StreamTapeExtractor !!If you missed this! extractor gives error!!
*/
StreamTapeExtractor.initiate();
// TODO : Fill up this with your cookies
StreamTapeExtractor.setCookies("COOKIE"); // Required
// TODO : Setup an User Agent if the extractor display's error!?.
//StreamTapeExtractor.setUserAgent("");

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
                                }
                            }
                        }

                        @Override
                        public void onError(String onError) {
                            Log.e("StreamTapeExtractor", "onError: " + onError);
                        }
                    });
```

## Credits
* [Jsoup](https://github.com/jhy/jsoup)
* [OkHttp](https://github.com/square/okhttp)

## Changelog
```
[v1.3] [07/02/2022]
+ Fixed url extraction error!. (Cloudflare)
[Dependencies]
+ Added OkHttp (Networking Library) dependency.
[Methods]
+ Added Cookies, User-Agent functions to the extractor.
- Removed direct download url.

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

[UNOFFICIAL DOWNLOAD AND STREAMING LINK EXTRACTOR FOR STREAMTAPE]
```

## Disclaimer
**This repository is for research purposes only, the use of this code is your responsibility.**
**I take NO responsibility and/or liability for how you choose to use any of the source code available here.**
**By using any of the files available in this repository, you understand that you are AGREEING TO USE AT YOUR OWN RISK. Once again, ALL files available here are for EDUCATION and/or RESEARCH purposes ONLY.**

<!-- LICENSE -->
## License

Distributed under the Apache License. See `LICENSE` for more information.

[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
