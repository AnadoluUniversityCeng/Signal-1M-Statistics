package edu.ceng;

import com.google.gson.annotations.SerializedName;

import java.time.Month;
import java.util.Date;
import java.util.TimeZone;

/**
 * The Signal Media One-Million News Articles Dataset
 */
class Article {

    @SerializedName("media-type")
    TYPE type;
    private String id;
    String content;
    String title;
    private String source;
    private Date published;

    Month getMonth() {
        return published.toInstant().atZone(TimeZone.getTimeZone("UTC").toZoneId()).getMonth();
    }
}
