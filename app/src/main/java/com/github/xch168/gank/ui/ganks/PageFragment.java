package com.github.xch168.gank.ui.ganks;


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

import com.github.xch168.gank.GankData;
import com.github.xch168.gank.GankService;
import com.github.xch168.gank.PageGankData;
import com.github.xch168.gank.R;
import com.github.xch168.gank.adapter.DataAdapter;
import com.github.xch168.gank.entity.Gank;
import com.github.xch168.gank.ui.BaseFragment;
import com.github.xch168.quickrecycleradapter.BaseQuickAdapter;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 */
public class PageFragment extends BaseFragment implements PageContract.View {

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

    private PageContract.Presenter mPresenter;

    private DataAdapter mDataAdapter;

    private int mPageType;

    public PageFragment() {
        // Required empty public constructor
    }

    public static PageFragment newInstance(int pageType) {
        PageFragment fragment = new PageFragment();
        Bundle args = new Bundle();
        args.putInt(PAGE_TYPE, pageType);
        fragment.setArguments(args);
        new PagePresenter(fragment);
        return fragment;
    }

    @Override
    public void setPresenter(PageContract.Presenter presenter) {
        mPresenter = presenter;
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
    public void onResume() {
        super.onResume();

        initView();
        mPresenter.loadData(mPageType);
    }

    private void initView() {
        mSwipeRefreshLayout = (SwipeRefreshLayout) getView().findViewById(R.id.srl);
        mRecyclerView = (RecyclerView) getView().findViewById(R.id.recycler_view);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        mDataAdapter = new DataAdapter(getActivity());
        mDataAdapter.setLoadMoreEnable(true);
        mDataAdapter.setOnLoadMoreListener(new BaseQuickAdapter.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                mPresenter.loadMore(mPageType);
            }
        });
        mRecyclerView.setAdapter(mDataAdapter);

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
            }
        });

    }

    @Override
    public void renderUI(List<Gank> data) {
        mDataAdapter.addAll(data);
    }

    @Override
    public void showRefreshing() {
        mSwipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideRefreshing() {
        mSwipeRefreshLayout.setRefreshing(false);
    }

    private void refresh() {
        mDataAdapter.clear();
        mPresenter.refresh(mPageType);
    }

}
