package com.mrkazofficial.mrkazstreamtape;

/**
 * @author MRKaZ
 * @since 3:33 AM, 5/10/2021, 2021
 */

public class StreamTapeUtils {

    // Thumbnail Image Regex Patterns
    public static final String StreamTapeUrlRegexStart = "<script>document.getElementById('vid'+'eolink')";
    public static final String StreamTapeUrlRegexEnd = ";</script>";

    // Thumbnail Image Regex Patterns
    public static final String StreamTapeThumbRegexStart = "<meta name=\"og:image\" content=\"";
    public static final String StreamTapeThumbRegexEnd = "\">";

    // Title Regex Patterns
    public static final String StreamTapeTitleRegexStart = "<meta name=\"og:title\" content=\"";
    public static final String StreamTapeTitleRegexEnd = "\">";
}
