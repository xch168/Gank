package com.github.xch168.gank.entity;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

/**
 * Created by xch on 2017/3/6.
 */

public class History {

    @SerializedName("results")
    public List<Date> results;
}
