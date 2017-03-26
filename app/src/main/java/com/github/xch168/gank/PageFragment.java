package com.github.xch168.gank;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.SafeObserver;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.Subject;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.PUT;


/**
 * A simple {@link Fragment} subclass.
 */
public class PageFragment extends Fragment {

    private static final String TAG = "PageFragment";

    public static final int PAGE_NEWEST = 0;
    public static final int PAGE_ANDROID = 1;
    public static final int PAGE_IOS = 2;
    public static final int PAGE_FRONT_END = 3;
    public static final int PAGE_EX_RESOURCE = 4;
    public static final int PAGE_RECOMMEND = 5;
    public static final int PAGE_APP = 6;
    public static final int PAGE_REST_VIDEO = 7;
    public static final int PAGE_WELFARE = 8;

    public static final String PAGE_TYPE = "page_type";


    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;

    private DataAdapter mDataAdapter;

    private List<Gank> datas;

    private int mPageType;

    public PageFragment() {
        // Required empty public constructor
    }

    public static PageFragment newInstance(int pageType) {
        PageFragment fragment = new PageFragment();
        Bundle args = new Bundle();
        args.putInt(PAGE_TYPE, pageType);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPageType = getArguments().getInt(PAGE_TYPE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_page, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initView();

        loadData();

    }

    private void initView() {
        mSwipeRefreshLayout = (SwipeRefreshLayout) getView().findViewById(R.id.srl);
        mRecyclerView = (RecyclerView) getView().findViewById(R.id.recycler_view);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        mDataAdapter = new DataAdapter(getActivity());
        datas = new ArrayList<>();

        mDataAdapter.setDatas(datas);
        mRecyclerView.setAdapter(mDataAdapter);

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
            }
        });

    }

    private void loadData() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://gank.io/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        GankService gankService = retrofit.create(GankService.class);

        switch (mPageType) {
            case PAGE_NEWEST:
                loadPageNewestData(gankService);
                break;
            case PAGE_ANDROID:
                loadPageAndroidData(gankService);
                break;
            case PAGE_IOS:
                loadPageIosData(gankService);
                break;
            case PAGE_FRONT_END:
                loadPageFrontEndData(gankService);
                break;
            case PAGE_EX_RESOURCE:
                loadPageExResourceData(gankService);
                break;
            case PAGE_RECOMMEND:
                loadPageRecommendData(gankService);
                break;
            case PAGE_APP:
                loadPageAppData(gankService);
                break;
            case PAGE_REST_VIDEO:
                loadPageRestVideoData(gankService);
                break;
            case PAGE_WELFARE:
                loadPageWelfareData(gankService);
                break;
        }

    }

    private void refresh() {
        datas.clear();
        loadData();
    }

    private void loadPageNewestData(GankService gankService) {
        gankService.getNewest(2017, 3, 24)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GankData>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mSwipeRefreshLayout.setRefreshing(true);
                    }

                    @Override
                    public void onNext(GankData value) {
                        Log.i(TAG, "size:" + value.results.androidList.size());

                        if (value.results.androidList != null) {
                            datas.addAll(value.results.androidList);
                        }
                        if (value.results.iOSList != null) {
                            datas.addAll(value.results.iOSList);
                        }
                        if (value.results.frontEndList != null) {
                            datas.addAll(value.results.frontEndList);
                        }
                        if (value.results.exResouceList != null) {
                            datas.addAll(value.results.exResouceList);
                        }
                        if (value.results.recommendList != null) {
                            datas.addAll(value.results.recommendList);
                        }
                        if (value.results.appList != null) {
                            datas.addAll(value.results.appList);
                        }
                        if (value.results.restVideoList != null) {
                            datas.addAll(value.results.restVideoList);
                        }
                        if (value.results.welfareList != null) {
                            datas.addAll(value.results.welfareList);
                        }
                        mDataAdapter.notifyDataSetChanged();

                    }

                    @Override
                    public void onError(Throwable e) {
                        mSwipeRefreshLayout.setRefreshing(false);
                    }

                    @Override
                    public void onComplete() {
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                });
    }


    private void loadPageAndroidData(GankService gankService) {
        gankService.listAndroid(1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PageGankData>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mSwipeRefreshLayout.setRefreshing(true);
                    }

                    @Override
                    public void onNext(PageGankData value) {
                        if (value.results != null) {
                            datas.addAll(value.results);
                            mDataAdapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        mSwipeRefreshLayout.setRefreshing(false);
                    }

                    @Override
                    public void onComplete() {
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                });
    }

    private void loadPageIosData(GankService gankService) {
        gankService.listIos(1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PageGankData>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mSwipeRefreshLayout.setRefreshing(true);
                    }

                    @Override
                    public void onNext(PageGankData value) {
                        if (value.results != null) {
                            datas.addAll(value.results);
                            mDataAdapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        mSwipeRefreshLayout.setRefreshing(false);
                    }

                    @Override
                    public void onComplete() {
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                });
    }

    private void loadPageFrontEndData(GankService gankService) {
        gankService.listFrontEnd(1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PageGankData>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mSwipeRefreshLayout.setRefreshing(true);
                    }

                    @Override
                    public void onNext(PageGankData value) {
                        if (value.results != null) {
                            datas.addAll(value.results);
                            mDataAdapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        mSwipeRefreshLayout.setRefreshing(false);
                    }

                    @Override
                    public void onComplete() {
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                });
    }

    private void loadPageExResourceData(GankService gankService) {
        gankService.listExResource(1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PageGankData>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mSwipeRefreshLayout.setRefreshing(true);
                    }

                    @Override
                    public void onNext(PageGankData value) {
                        if (value.results != null) {
                            datas.addAll(value.results);
                            mDataAdapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        mSwipeRefreshLayout.setRefreshing(false);
                    }

                    @Override
                    public void onComplete() {
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                });
    }

    private void loadPageRecommendData(GankService gankService) {
        gankService.listRecommend(1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PageGankData>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mSwipeRefreshLayout.setRefreshing(true);
                    }

                    @Override
                    public void onNext(PageGankData value) {
                        if (value.results != null) {
                            datas.addAll(value.results);
                            mDataAdapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        mSwipeRefreshLayout.setRefreshing(false);
                    }

                    @Override
                    public void onComplete() {
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                });
    }

    private void loadPageAppData(GankService gankService) {
        gankService.listApp(1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PageGankData>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mSwipeRefreshLayout.setRefreshing(true);
                    }

                    @Override
                    public void onNext(PageGankData value) {
                        if (value.results != null) {
                            datas.addAll(value.results);
                            mDataAdapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        mSwipeRefreshLayout.setRefreshing(false);
                    }

                    @Override
                    public void onComplete() {
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                });
    }

    private void loadPageRestVideoData(GankService gankService) {
        gankService.listRestVideo(1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PageGankData>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mSwipeRefreshLayout.setRefreshing(true);
                    }

                    @Override
                    public void onNext(PageGankData value) {
                        if (value.results != null) {
                            datas.addAll(value.results);
                            mDataAdapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        mSwipeRefreshLayout.setRefreshing(false);
                    }

                    @Override
                    public void onComplete() {
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                });
    }

    private void loadPageWelfareData(GankService gankService) {
        gankService.listWelfare(1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PageGankData>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mSwipeRefreshLayout.setRefreshing(true);
                    }

                    @Override
                    public void onNext(PageGankData value) {
                        if (value.results != null) {
                            datas.addAll(value.results);
                            mDataAdapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        mSwipeRefreshLayout.setRefreshing(false);
                    }

                    @Override
                    public void onComplete() {
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                });
    }




}
