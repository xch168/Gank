package com.github.xch168.gank.ui.ganks.data;

import com.github.xch168.gank.entity.Gank;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by xch on 2017/3/27.
 */

public class PageGankData {

    @SerializedName("results")
    public List<Gank> results;
}
