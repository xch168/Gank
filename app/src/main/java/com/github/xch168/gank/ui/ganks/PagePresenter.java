package com.github.xch168.gank.ui.ganks;


import com.github.xch168.gank.entity.Gank;
import com.github.xch168.gank.entity.History;
import com.github.xch168.gank.ui.ganks.data.GankData;
import com.github.xch168.gank.ui.ganks.data.GankService;
import com.github.xch168.gank.ui.ganks.data.PageGankData;
import com.github.xch168.gank.helper.RetrofitHelper;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

import static com.github.xch168.gank.ui.ganks.PageFragment.PAGE_ANDROID;
import static com.github.xch168.gank.ui.ganks.PageFragment.PAGE_APP;
import static com.github.xch168.gank.ui.ganks.PageFragment.PAGE_EX_RESOURCE;
import static com.github.xch168.gank.ui.ganks.PageFragment.PAGE_FRONT_END;
import static com.github.xch168.gank.ui.ganks.PageFragment.PAGE_IOS;
import static com.github.xch168.gank.ui.ganks.PageFragment.PAGE_NEWEST;
import static com.github.xch168.gank.ui.ganks.PageFragment.PAGE_RECOMMEND;
import static com.github.xch168.gank.ui.ganks.PageFragment.PAGE_REST_VIDEO;
import static com.github.xch168.gank.ui.ganks.PageFragment.PAGE_WELFARE;


/**
 * Created by XuCanHui on 2017/8/2.
 */

public class PagePresenter implements PageContract.Presenter {
    
    private PageContract.View mView;
    
    private GankService mGankService;

    private int mCurrentPage = 1;


    public PagePresenter(PageContract.View view) {
        mView = view;
        mGankService = RetrofitHelper.getService(GankService.class);

        mView.setPresenter(this);
    }


    @Override
    public void loadData(int pageType) {
        loadPageData(pageType, mCurrentPage);
    }

    @Override
    public void refresh(int pageType) {
        loadData(pageType);
    }

    @Override
    public void loadMore(int pageType) {
        mCurrentPage++;
        loadData(pageType);
    }

    private void loadPageNewestData() {

        mGankService.listHistory()
                .flatMap(new Function<History, ObservableSource<GankData>>() {
                    @Override
                    public ObservableSource<GankData> apply(History history) throws Exception {
                        Date lastDate = history.results.get(0);
                        if (lastDate != null) {
                            Calendar calendar = Calendar.getInstance();
                            calendar.setTime(lastDate);
                            return mGankService.getNewest(calendar.get(Calendar.YEAR), (calendar.get(Calendar.MONTH) + 1), calendar.get(Calendar.DAY_OF_MONTH));
                        }
                        return null;
                    }
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GankData>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mView.showRefreshing();
                    }

                    @Override
                    public void onNext(GankData value) {
                        List<Gank> gankList = new ArrayList<>();

                        if (value.results.androidList != null) {
                            gankList.addAll(value.results.androidList);
                        }
                        if (value.results.iOSList != null) {
                            gankList.addAll(value.results.iOSList);
                        }
                        if (value.results.frontEndList != null) {
                            gankList.addAll(value.results.frontEndList);
                        }
                        if (value.results.exResouceList != null) {
                            gankList.addAll(value.results.exResouceList);
                        }
                        if (value.results.recommendList != null) {
                            gankList.addAll(value.results.recommendList);
                        }
                        if (value.results.appList != null) {
                            gankList.addAll(value.results.appList);
                        }
                        if (value.results.restVideoList != null) {
                            gankList.addAll(value.results.restVideoList);
                        }
                        if (value.results.welfareList != null) {
                            gankList.addAll(value.results.welfareList);
                        }
                        mView.renderUI(gankList);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.hideRefreshing();
                    }

                    @Override
                    public void onComplete() {
                        mView.hideRefreshing();
                    }
                });


    }

    private void loadPageData(int pageType, int pageNum) {
        Observable<PageGankData> observable = getObservable(pageType, pageNum);
        if (observable != null) {
            observable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<PageGankData>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                            mView.showRefreshing();
                        }

                        @Override
                        public void onNext(PageGankData value) {
                            if (value.results != null) {
                                mView.renderUI(value.results);
                            }
                        }

                        @Override
                        public void onError(Throwable e) {
                            mView.hideRefreshing();
                        }

                        @Override
                        public void onComplete() {
                            mView.hideRefreshing();
                        }
                    });
        }
    }

    private Observable<PageGankData> getObservable(int pageType, int pageNum) {
        switch (pageType) {
            case PAGE_NEWEST:
                loadPageNewestData();
                return null;
            case PAGE_ANDROID:
                return mGankService.listAndroid(pageNum);
            case PAGE_IOS:
                return mGankService.listIos(pageNum);
            case PAGE_FRONT_END:
                return mGankService.listFrontEnd(pageNum);
            case PAGE_EX_RESOURCE:
                return mGankService.listExResource(pageNum);
            case PAGE_RECOMMEND:
                return mGankService.listRecommend(pageNum);
            case PAGE_APP:
                return mGankService.listApp(pageNum);
            case PAGE_REST_VIDEO:
                return mGankService.listRestVideo(pageNum);
            case PAGE_WELFARE:
                return mGankService.listWelfare(pageNum);
        }
        return null;
    }
}
