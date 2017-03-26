package com.github.xch168.gank;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by xch on 2017/3/27.
 */

public class PageGankData {

    @SerializedName("results")
    public List<Gank> results;
}
