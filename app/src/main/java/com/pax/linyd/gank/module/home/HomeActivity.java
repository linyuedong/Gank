package com.pax.linyd.gank.module.home;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.pax.linyd.gank.R;
import com.pax.linyd.gank.module.base.BaseActivity;
import com.pax.linyd.gank.module.fragment.Fragment1;
import com.pax.linyd.gank.module.fragment.Fragment2;
import com.pax.linyd.gank.module.fragment.Fragment3;
import com.pax.linyd.gank.module.fragment.GirlsFragment;

import butterknife.BindView;

public class HomeActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public String currentFragmentTag;
    public static final String FRAGMENT_TAG_ZHIHUDAILY = "ZhiHuDaily";
    public static final String FRAGMENT_TAG_PICTURE = "Picture";
    public static final String FRAGMENT_TAG_VIDEO = "Video";
    public static final String FRAGMENT_TAG_SETTING = "Setting";
    @BindView(R.id.content)
    FrameLayout mContent;
    @BindView(R.id.nav_view)
    NavigationView mNavView;
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;


    @Override
    public int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    public void initView() {
        initNavigationView();
        initFragment();

    }


    private void initNavigationView() {
        mNavView.setNavigationItemSelectedListener(this);
    }

    private void initFragment() {
        switchContent(FRAGMENT_TAG_ZHIHUDAILY);
    }
    public void initDrawer(Toolbar toolbar) {
        if (toolbar != null) {
            ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
                @Override
                public void onDrawerOpened(View drawerView) {
                    super.onDrawerOpened(drawerView);
                }

                @Override
                public void onDrawerClosed(View drawerView) {
                    super.onDrawerClosed(drawerView);
                }
            };
            mDrawerToggle.syncState();
            mDrawerLayout.addDrawerListener(mDrawerToggle);

        }

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
//            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
//            //将侧边栏顶部延伸至status bar
//            mDrawerLayout.setFitsSystemWindows(true);
//            //将主页面顶部延伸至status bar
//            mDrawerLayout.setClipToPadding(false);
//        }
    }
    @Override
    public void initData() {

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        switch (id) {
            case R.id.ZhiHuDaily:
                item.setChecked(true);
                switchContent(FRAGMENT_TAG_ZHIHUDAILY);
                break;
            case R.id.picture:
                //item.setChecked(true);
                switchContent(FRAGMENT_TAG_PICTURE);
                break;
            case R.id.video:
                //item.setChecked(true);
                switchContent(FRAGMENT_TAG_VIDEO);
                break;
            case R.id.setting:
                //item.setChecked(true);
                switchContent(FRAGMENT_TAG_SETTING);
                break;
            default:
                break;
        }
        //关闭抽屉
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void switchContent(String tag) {
        //点击相同Item不做处理
        if (currentFragmentTag != null && currentFragmentTag.equals(tag))
            return;
        FragmentManager mFragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = mFragmentManager.beginTransaction();
        //隐藏当前Fragment
        Fragment currentFragment = mFragmentManager.findFragmentByTag(currentFragmentTag);
        if (currentFragment != null) {
            ft.hide(currentFragment);
        }

       Fragment tagetFragment = mFragmentManager.findFragmentByTag(tag);
        if (tagetFragment == null) {
            switch (tag) {
                case FRAGMENT_TAG_ZHIHUDAILY:
                    tagetFragment = Fragment1.newInstance("", "");
                   // tagetFragment = new Fragment();
                    break;
                case FRAGMENT_TAG_PICTURE:
                    tagetFragment = new GirlsFragment();
                    break;
                case FRAGMENT_TAG_VIDEO:
                    //tagetFragment = new GirlsFragment();
                   tagetFragment = Fragment3.newInstance("", "");
                    break;
                case FRAGMENT_TAG_SETTING:
                    //tagetFragment = new GirlsFragment();
                    tagetFragment = Fragment1.newInstance("", "");
                    break;
            }
        }

        if(tagetFragment != null){
            if(!tagetFragment.isAdded()){
                ft.add(R.id.content, tagetFragment, tag);
            }
            ft.show(tagetFragment);
        }
        ft.commit();
        currentFragmentTag = tag;
    }


}
