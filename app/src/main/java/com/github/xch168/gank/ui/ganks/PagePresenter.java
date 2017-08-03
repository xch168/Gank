package com.github.xch168.gank.ui.ganks;


import com.github.xch168.gank.GankData;
import com.github.xch168.gank.GankService;
import com.github.xch168.gank.PageGankData;
import com.github.xch168.gank.helper.RetrofitHelper;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
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

    public PagePresenter(PageContract.View view) {
        mView = view;
        mGankService = RetrofitHelper.getService(GankService.class);

        mView.setPresenter(this);
    }


    @Override
    public void loadData(int pageType) {
        switch (pageType) {
            case PAGE_NEWEST:
                loadPageNewestData();
                break;
            case PAGE_ANDROID:
                loadPageAndroidData();
                break;
            case PAGE_IOS:
                loadPageIosData();
                break;
            case PAGE_FRONT_END:
                loadPageFrontEndData();
                break;
            case PAGE_EX_RESOURCE:
                loadPageExResourceData();
                break;
            case PAGE_RECOMMEND:
                loadPageRecommendData();
                break;
            case PAGE_APP:
                loadPageAppData();
                break;
            case PAGE_REST_VIDEO:
                loadPageRestVideoData();
                break;
            case PAGE_WELFARE:
                loadPageWelfareData();
                break;
        }
    }

    @Override
    public void refresh(int pageType) {
        loadData(pageType);
    }

    @Override
    public void loadMore(int pageType) {

    }

    private void loadPageNewestData() {

        mGankService.getNewest(2017, 3, 24)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GankData>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mView.showRefreshing();
                    }

                    @Override
                    public void onNext(GankData value) {

                        if (value.results.androidList != null) {
                            mView.renderUI(value.results.androidList);
                        }
                        if (value.results.iOSList != null) {
                            mView.renderUI(value.results.iOSList);
                        }
                        if (value.results.frontEndList != null) {
                            mView.renderUI(value.results.frontEndList);
                        }
                        if (value.results.exResouceList != null) {
                            mView.renderUI(value.results.exResouceList);
                        }
                        if (value.results.recommendList != null) {
                            mView.renderUI(value.results.recommendList);
                        }
                        if (value.results.appList != null) {
                            mView.renderUI(value.results.appList);
                        }
                        if (value.results.restVideoList != null) {
                            mView.renderUI(value.results.restVideoList);
                        }
                        if (value.results.welfareList != null) {
                            mView.renderUI(value.results.welfareList);
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

    private void loadPageAndroidData() {
        mGankService.listAndroid(1)
                .subscribeOn(Schedulers.io())
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

    private void loadPageIosData() {
        mGankService.listIos(1)
                .subscribeOn(Schedulers.io())
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

    private void loadPageFrontEndData() {
        mGankService.listFrontEnd(1)
                .subscribeOn(Schedulers.io())
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

    private void loadPageExResourceData() {
        mGankService.listExResource(1)
                .subscribeOn(Schedulers.io())
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

    private void loadPageRecommendData() {
        mGankService.listRecommend(1)
                .subscribeOn(Schedulers.io())
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

    private void loadPageAppData() {
        mGankService.listApp(1)
                .subscribeOn(Schedulers.io())
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

    private void loadPageRestVideoData() {
        mGankService.listRestVideo(1)
                .subscribeOn(Schedulers.io())
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

    private void loadPageWelfareData() {
        mGankService.listWelfare(1)
                .subscribeOn(Schedulers.io())
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