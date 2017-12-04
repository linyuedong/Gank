package com.pax.linyd.gank.module.fragment;


import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.aspsine.irecyclerview.IRecyclerView;
import com.aspsine.irecyclerview.OnLoadMoreListener;
import com.aspsine.irecyclerview.OnRefreshListener;
import com.pax.linyd.gank.R;
import com.pax.linyd.gank.module.adapter.GirlAdapter;
import com.pax.linyd.gank.module.base.BaseFragment;
import com.pax.linyd.gank.module.network.RetrofitHelper;
import com.pax.linyd.gank.module.network.gank;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class GankgirlFragment extends BaseFragment {

    //    @BindView(R.id.pullLoadMoreRecyclerView)
//    PullLoadMoreRecyclerView mPullLoadMoreRecyclerView;
//    @BindView(R.id.swipe_refresh)
//    LinearLayout mSwipeRefresh;
    public static final String title = "福利";
    public int pageNumber = 10;
    public int page = 1;
    @BindView(R.id.iRecyclerView)
    IRecyclerView mIRecyclerView;
    @BindView(R.id.swipe_refresh)
    LinearLayout mSwipeRefresh;
    Unbinder unbinder;

    private GirlAdapter girlAdapter;

//   /* @BindView(R.id.recycle)
//    RecyclerView mRecycleView;
//    @BindView(R.id.swipe_refresh)
//    SwipeRefreshLayout mSwipeRefresh;*/


    @Override
    public int getLayoutById() {
        return R.layout.fragment_gankgirl;
    }

    @Override
    public void initdata() {
        loadData();
    }

    @Override
    protected void initView() {
        initSwipeRefreshLayout();
        initRecycleView();
    }

//    private void initSwipeRefreshLayout() {
//        mRecycle.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));
//        girlAdapter = new GirlAdapter(getContext());
//        mRecycle.setAdapter(girlAdapter);
//    }

    private void initSwipeRefreshLayout() {
        mIRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        girlAdapter = new GirlAdapter(getContext());
        mIRecyclerView.setAdapter(girlAdapter);
        mIRecyclerView.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                mIRecyclerView.setRefreshing(true);
                page = 1;
                System.out.println("page : " + page);
                loadData();
            }
        });
        mIRecyclerView.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                page++;
                System.out.println("page : " + page);
                loadData();
            }
        });
    }

    private void initRecycleView() {

    }

    public void loadData() {
        RetrofitHelper.getGankApi().getGankDatas(title, pageNumber, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<gank>() {
                    @Override
                    public void onCompleted() {
                        // mSwipeRefresh.setRefreshing(false);
                        mIRecyclerView.setRefreshing(false);
                    }

                    @Override
                    public void onError(Throwable e) {
                        //myRecyclerAdapter.setFootViewGone();
                        // mSwipeRefresh.setRefreshing(true);
                        Snackbar.make(mIRecyclerView, "连接不上网络", Snackbar.LENGTH_LONG).show();

                    }

                    @Override
                    public void onNext(gank gank) {
                        girlAdapter.addMoreItem(gank.results);
                    }
                });
    }



}
