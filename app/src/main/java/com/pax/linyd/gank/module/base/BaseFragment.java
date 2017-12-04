package com.pax.linyd.gank.module.base;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.trello.rxlifecycle.components.RxFragment;

import butterknife.ButterKnife;

/**
 * Created by linyd on 2017/4/18.
 */
public abstract class BaseFragment extends Fragment {

    public boolean hasLoadData = false;
    public boolean isViewPrepared = false;
    public View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

         view = inflater.inflate( getLayoutById(), container, false);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    public abstract int getLayoutById();
    public abstract void initdata() ;
    protected abstract void initView();


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser){
            lazyLoad();
        }

    }

    private void lazyLoad() {
        if(getUserVisibleHint() && isViewPrepared && !hasLoadData){
            hasLoadData = true;
            initdata();
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        isViewPrepared = true;
        lazyLoad();

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        hasLoadData = false;
        isViewPrepared = false;

    }
    protected <T extends View> T findView(@IdRes int id) {
        return (T) view.findViewById(id);
    }

}
