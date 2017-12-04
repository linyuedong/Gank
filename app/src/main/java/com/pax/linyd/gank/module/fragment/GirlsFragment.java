package com.pax.linyd.gank.module.fragment;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pax.linyd.gank.R;
import com.pax.linyd.gank.module.base.BaseFragment;
import com.pax.linyd.gank.module.home.HomeActivity;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class GirlsFragment extends BaseFragment {


    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.tabs)
    TabLayout mTabs;
    @BindView(R.id.viewPager)
    ViewPager mViewPager;
    Unbinder unbinder;

    @Override
    public int getLayoutById() {
        return R.layout.fragment_fragment1;
    }

    @Override
    public void initdata() {

    }

    @Override
    protected void initView() {
        initToolbar();
        initTab();
    }
    private void initToolbar() {
        mToolbar.setTitle("福利");
        ((HomeActivity) getActivity()).initDrawer(mToolbar);
    }


    private void initTab() {
        List<String> titles = Arrays.asList("Gank", "煎蛋", "小清新", "文艺范", "大长腿");
        mViewPager.setAdapter(new myViewPager(getChildFragmentManager(),titles));
        mTabs.setupWithViewPager(mViewPager);
        mTabs.setTabMode(TabLayout.MODE_SCROLLABLE);
    }

    class myViewPager extends FragmentPagerAdapter {
        List<String> title;

        public myViewPager(FragmentManager fm, List<String> title) {
            super(fm);
            this.title = title;
        }

        @Override
        public int getCount() {
            return title.size();
        }

        @Override
        public Fragment getItem(int position) {
            return  new GankgirlFragment();
        }
        @Override
        public CharSequence getPageTitle(int position) {
            return title.get(position);
        }
    }



}
