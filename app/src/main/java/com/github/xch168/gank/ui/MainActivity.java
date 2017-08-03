package com.github.xch168.gank.ui;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.xch168.gank.R;
import com.github.xch168.gank.ui.ganks.PageFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    private String[] titles = new String[] { "最新", "Android", "iOS", "前端", "扩展资源", "瞎推荐", "APP", "休息视频", "福利"};

    private List<Fragment> mFragmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTabLayout = findViewById(R.id.tab_layout);
        mViewPager = findViewById(R.id.view_pager);

        mTabLayout.addTab(mTabLayout.newTab().setText(titles[0]));
        mTabLayout.addTab(mTabLayout.newTab().setText(titles[1]));
        mTabLayout.addTab(mTabLayout.newTab().setText(titles[2]));
        mTabLayout.addTab(mTabLayout.newTab().setText(titles[3]));
        mTabLayout.addTab(mTabLayout.newTab().setText(titles[4]));
        mTabLayout.addTab(mTabLayout.newTab().setText(titles[5]));
        mTabLayout.addTab(mTabLayout.newTab().setText(titles[6]));
        mTabLayout.addTab(mTabLayout.newTab().setText(titles[7]));
        mTabLayout.addTab(mTabLayout.newTab().setText(titles[8]));

        mFragmentList = new ArrayList<>();
        mFragmentList.add(PageFragment.newInstance(PageFragment.PAGE_NEWEST));
        mFragmentList.add(PageFragment.newInstance(PageFragment.PAGE_ANDROID));
        mFragmentList.add(PageFragment.newInstance(PageFragment.PAGE_IOS));
        mFragmentList.add(PageFragment.newInstance(PageFragment.PAGE_FRONT_END));
        mFragmentList.add(PageFragment.newInstance(PageFragment.PAGE_EX_RESOURCE));
        mFragmentList.add(PageFragment.newInstance(PageFragment.PAGE_RECOMMEND));
        mFragmentList.add(PageFragment.newInstance(PageFragment.PAGE_APP));
        mFragmentList.add(PageFragment.newInstance(PageFragment.PAGE_REST_VIDEO));
        mFragmentList.add(PageFragment.newInstance(PageFragment.PAGE_WELFARE));



        PageAdapter pageAdapter = new PageAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(pageAdapter);

        mTabLayout.setupWithViewPager(mViewPager);

    }

    class PageAdapter extends FragmentPagerAdapter {

        public PageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return titles.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }
    }
}
