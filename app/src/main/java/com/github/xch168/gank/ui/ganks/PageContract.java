package com.github.xch168.gank.ui.ganks;

import com.github.xch168.gank.base.mvp.BasePresenter;
import com.github.xch168.gank.base.mvp.BaseView;
import com.github.xch168.gank.entity.Gank;

import java.util.List;

/**
 * Created by XuCanHui on 2017/8/2.
 */

public interface PageContract {

    interface View extends BaseView<Presenter> {
        void renderUI(List<Gank> data);
        void showRefreshing();
        void hideRefreshing();
    }

    interface Presenter extends BasePresenter {
        void loadData(int pageType);
        void refresh(int pageType);
        void loadMore(int pageType);
    }
}
