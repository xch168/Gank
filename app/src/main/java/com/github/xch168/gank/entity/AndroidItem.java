package com.github.xch168.gank.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by xch on 2017/3/5.
 */

public class AndroidItem {

    @SerializedName("_id")
    public String _id;

    @SerializedName("createdAt")
    public String createdAt;

    @SerializedName("desc")
    public String desc;

    @SerializedName("publishedAt")
    public String publishedAt;

    @SerializedName("source")
    public String source;

    @SerializedName("type")
    public String type;

    @SerializedName("url")
    public String url;

    @SerializedName("used")
    public String used;

    @SerializedName("who")
    public String who;

}
