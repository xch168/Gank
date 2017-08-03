package com.github.xch168.gank.ui.ganks.data;

import com.github.xch168.gank.entity.History;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by xch on 2017/3/5.
 */

public interface GankService {

    @GET("day/history")
    Observable<History> listHistory();

    @GET("day/{year}/{month}/{day}")
    Observable<GankData> getNewest(@Path("year") int year, @Path("month") int month, @Path("day") int day);

    @GET("data/Android/20/{page}")
    Observable<PageGankData> listAndroid(@Path("page") int page);

    @GET("data/iOS/20/{page}")
    Observable<PageGankData> listIos(@Path("page") int page);

    @GET("data/前端/20/{page}")
    Observable<PageGankData> listFrontEnd(@Path("page") int page);

    @GET("data/拓展资源/20/{page}")
    Observable<PageGankData> listExResource(@Path("page") int page);

    @GET("data/瞎推荐/20/{page}")
    Observable<PageGankData> listRecommend(@Path("page") int page);

    @GET("data/App/20/{page}")
    Observable<PageGankData> listApp(@Path("page") int page);

    @GET("data/休息视频/20/{page}")
    Observable<PageGankData> listRestVideo(@Path("page") int page);

    @GET("data/福利/20/{page}")
    Observable<PageGankData> listWelfare(@Path("page") int page);
}
