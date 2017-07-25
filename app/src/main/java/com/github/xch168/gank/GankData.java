package com.github.xch168.gank;

import com.github.xch168.gank.entity.Gank;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by xch on 2017/3/6.
 */

public class GankData {

    @SerializedName("results")
    public Result results;

    public class Result {
        @SerializedName("Android")
        public List<Gank> androidList;

        @SerializedName("iOS")
        public List<Gank> iOSList;

        @SerializedName("前端")
        public List<Gank> frontEndList;

        @SerializedName("App")
        public List<Gank> appList;

        @SerializedName("扩展资源")
        public List<Gank> exResouceList;

        @SerializedName("瞎推荐")
        public List<Gank> recommendList;

        @SerializedName("福利")
        public List<Gank> welfareList;

        @SerializedName("休息视频")
        public List<Gank> restVideoList;

    }
}
