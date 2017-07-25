package com.github.xch168.gank;

import com.github.xch168.gank.entity.AndroidItem;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by xch on 2017/3/6.
 */

public class AndroidModel {

    @SerializedName("error")
    public boolean error;

    @SerializedName("results")
    public List<AndroidItem> androidItemList;
}
