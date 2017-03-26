package com.github.xch168.gank;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

/**
 * Created by xch on 2017/3/6.
 */

public class Gank {

    @SerializedName("_id")
    public String _id;

    @SerializedName("createdAt")
    public Date createdAt;

    @SerializedName("desc")
    public String desc;

    @SerializedName("publishedAt")
    public Date publishedAt;

    @SerializedName("type")
    public String type;

    @SerializedName("url")
    public String url;

    @SerializedName("used")
    public boolean used;

    @SerializedName("who")
    public String who;

    @SerializedName("images")
    public List<String> images;



}
