package com.pax.linyd.gank.module.home;

/**
 * Created by linyd on 2017/3/28.
 */

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.dl7.tag.TagView;
import com.pax.linyd.gank.R;
import com.pax.linyd.gank.module.base.BaseActivity;
import com.trello.rxlifecycle.LifecycleTransformer;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class SplashActivity extends BaseActivity {


    @BindView(R.id.iv_splash)
    ImageView mIvSplash;

    @BindView(R.id.tag_skip)
    TagView mTagSkip;

    private boolean mIsSkip = false;
    String url1 = "http://cn.bing.com/az/hprichbg/rb/Dongdaemun_ZH-CN10736487148_1920x1080.jpg";
    String url2 = "http://p2.zhimg.com/10/7b/107bb4894b46d75a892da6fa80ef504a.jpg";

    @Override
    public int getLayoutId() {
        return R.layout.splash_activity;
    }

    @Override
    public void initView() {
        setTranslucentStatus();
        loadImage(url1);
        initTabView();
        updateView();
    }

    private void initTabView() {
        mTagSkip.setTagClickListener(new TagView.OnTagClickListener() {
            @Override
            public void onTagClick(int pos, String text, int mode) {
                skipSplash();
            }
        });
    }

    private void updateView() {
        countdown(3).compose(this.<Integer>bindToLife())
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onCompleted() {
                       skipSplash();
                    }

                    @Override
                    public void onError(Throwable e) {
                        skipSplash();
                    }

                    @Override
                    public void onNext(Integer integer) {
                        mTagSkip.setText("跳过 " + integer);
                    }
                });
    }

    private void skipSplash() {
        if(!mIsSkip){
            mIsSkip = true;
            Intent intent = new Intent(SplashActivity.this,HomeActivity.class);
            startActivity(intent);
            finish();
        }


    }

    public <T> LifecycleTransformer<T> bindToLife() {
        return this.<T>bindToLifecycle();
    }

    public static Observable<Integer> countdown(int time) {
        if (time < 0) {
            time = 0;
        }
        final int countTime = time;

        return Observable.interval(0, 1, TimeUnit.SECONDS)
                .map(new Func1<Long, Integer>() {
                    @Override
                    public Integer call(Long increaseTime) {
                        return countTime - increaseTime.intValue();
                    }
                })
                .take(countTime + 1)//指定轮训次数
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread());

    }

    @Override
    public void initData() {


    }

    private void setTranslucentStatus() {
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // Android 5.0
            int visibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                // Android 6.0
                // 亮色模式,避免系统状态栏的图标不可见
                // visibility |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            }
            window.getDecorView().setSystemUiVisibility(visibility);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            // 自定义状态栏背景色
            window.setStatusBarColor(Color.TRANSPARENT);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // Android 4.4
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }


    private void loadImage(String url) {
        Glide.with(this).
                load(url).
                placeholder(R.drawable.loading).
                error(R.mipmap.splash8).
                crossFade().
                into(mIvSplash);

    }



}
