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
	<img src="https://i2.paste.pics/22a866a2a5b2c07e14102acde1ebd451.png" width="1024">
</div>

##
This is a Simple StreamTape Extractor for the download video files direct using **Jsoup** Html parser library. **OkHttp** doing the networking side.
Getting a **HTTP/HTTPS** response with the given url and matched it with some Regex patterns to get the essentials from the scraped web page and use jsoup to get the postions of the data.
## Based Config Method..

##### **TODO** You can follow this config method to extract video data:

###### Config

```java
 StreamTapeExtractor.extractData(String Url,
                                 RequestListener RequestListener)
```

## Prerequisites

##### Add this in your root `build.gradle` file (**not** in your `build.gradle.app` add it into your module `build.gradle` file):

```gradle
allprojects {
	repositories {
		...
		maven { url "https://jitpack.io" }
	}
}
```

## Dependency

##### Add this to your module's `build.gradle` file:

```gradle
dependencies {
	...
	 implementation 'com.github.MRKaZ:StreamTapeExtractor:v1.4'
}
```

## Supports

##### **Add new domains**

| Domains | Status |
| --- | --- |
| **https://stape.fun** | :heavy_check_mark:
| **https://scloud.online** | :heavy_check_mark:
| **https://streamtape.com** | :heavy_check_mark:

## Usage

##### **You can extract the video data following this**

### Initiate the Extractor
```java
/*
* Keep this mind you have to initiate the Extractor before use you can do it following this
* TODO : Initiate the StreamTapeExtractor !!If you missed this! extractor gives error!!
*/
StreamTapeExtractor.initiate();
```

### Extract the video data from the given url
```java
 // Extract the video data
 StreamTapeExtractor.extractData(
                    // StreamTape Url
                    "PUT_YOUR_URL_HERE",
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
```

## Credits
* [Jsoup](https://github.com/jhy/jsoup)
* [OkHttp](https://github.com/square/okhttp)
* [Conscrypt](https://github.com/google/conscrypt)

## Changelog
```
[v1.4] [09/02/2022]
+ Fixed url extraction error!. (Cloudflare)
+ Added new StreamTapes domains. Extractor works for it.
+ Target SDK updated (31).
+ Garbage cleaned.
• [Dependencies]
+ OkHttp (BOM).
+ ConsCrypt.
• [Methods]
+ Added back Direct url method. (Rollback)
- Removed Cookies, User-Agent functions to the extractor.

[MRKaZ] [Taprobana (LK)

[v1.3] [07/02/2022]
+ Fixed url extraction error!. (Cloudflare)
• [Dependencies]
+ Added OkHttp (Networking Library) dependency.
• [Methods]
+ Added Cookies, User-Agent functions to the extractor.
- Removed direct download url.

[MRKaZ] [Taprobana (LK)]

[v1.2] [14/10/2021]
+ Fixed url extraction error!.
+ Removed and Changed some extraction methods.
+ Removed and Changed some usage methods.
- Migrated Volley dependency.
• [Dependencies]
+ Added JSOUP (Html parser) dependency.
• [Methods]
+ Added new Direct url method
- Removed (Context, Activity).

[MRKaZ] [Taprobana (LK)]

[v1.0] [10/05/2021]

+ First release!.
+ Simple extraction method for the StreamTape.
+ Extract Url, Title & Thumbnail Image Url.

[MRKaZ] [Taprobana (LK)]

[UNOFFICIAL DOWNLOAD AND STREAMING LINK EXTRACTOR FOR STREAMTAPE]

```

## Disclaimer
```
This repository is for research purposes only, the use of this code is your responsibility.
I take NO responsibility and/or liability for how you choose to use any of the source code
available here. By using any of the files available in this repository,
you understand that you are AGREEING TO USE AT YOUR OWN RISK. Once again,
ALL files available here are for EDUCATION and/or RESEARCH purposes ONLY.
```
<!-- LICENSE -->
## License

##### Distributed under the Apache License. See `LICENSE` for more information.

[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
