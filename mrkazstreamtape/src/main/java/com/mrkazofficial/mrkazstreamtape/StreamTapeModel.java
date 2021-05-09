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

/**
 * @author MRKaZ
 * @since 1:20 AM, 5/7/2021, 2021
 */

public class StreamTapeModel {

    private String setTitle;
    private String setDownloadUrl;
    private String getThumbnail;

    public String getTitle() {
        return setTitle;
    }

    public void setTitle(String setFileName) {
        this.setTitle = setFileName;
    }

    public String getDownloadUrl() {
        return setDownloadUrl;
    }

    public void setSetDownloadUrl(String setDownloadUrl) {
        this.setDownloadUrl = setDownloadUrl;
    }

    public String getThumbnail() {
        return getThumbnail;
    }

    public void setThumbnail(String getThumbnail) {
        this.getThumbnail = getThumbnail;
    }
}
