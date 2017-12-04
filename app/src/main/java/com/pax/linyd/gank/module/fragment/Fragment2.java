package com.pax.linyd.gank.module.fragment;

import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.pax.linyd.gank.R;
import com.pax.linyd.gank.module.adapter.GankAdapter;
import com.pax.linyd.gank.module.adapter.MyRecyclerAdapter;
import com.pax.linyd.gank.module.network.RetrofitHelper;
import com.pax.linyd.gank.module.network.gank;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class Fragment2 extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    int[] lastVisibleItemPositions;
    public int page = 1;
    public int pageNumber = 20;
    @BindView(R.id.recycle)
    RecyclerView mRecycle;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout mSwipeRefresh;


    // TODO: Rename and change types of parameters
    private String title;
    private String mParam2;
    private GankAdapter myRecyclerAdapter;


    public static Fragment2 newInstance(String param1, String param2) {
        Fragment2 fragment = new Fragment2();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            title = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Log.i("setUserVisibleHint","fragment2");
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i("onCreateView","fragment2");
        View view = inflater.inflate(R.layout.fragment_fragment2, container, false);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
        initSwipeRefreshLayout();
        initRecycleView();
        loadData();
    }
    private void initSwipeRefreshLayout(){
        //下拉刷新
        mSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().post(new Runnable() {
                    @Override
                    public void run() {
                        myRecyclerAdapter.removeAll();
                        page = 1;
                        loadData();
                        mSwipeRefresh.setRefreshing(false);
                    }
                });
            }
        });

    }

    private void initRecycleView() {
        mRecycle.setHasFixedSize(true);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        mRecycle.setLayoutManager(linearLayoutManager);
//        final StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
//        mRecycle.setLayoutManager(layoutManager);
         myRecyclerAdapter = new GankAdapter(getContext());
        mRecycle.setAdapter(myRecyclerAdapter);
       
        //上拉加载更多
       
        mRecycle.addOnScrollListener(new RecyclerView.OnScrollListener(){
            public int lastVisibleItemPosition;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if( lastVisibleItemPosition + 1 == myRecyclerAdapter.getItemCount()){

                    myRecyclerAdapter.changeMoreStatus(MyRecyclerAdapter.LOADING_MORE);
                    new Handler().post(new Runnable() {
                        @Override
                        public void run() {
                            page++;
                            loadData();
                            //myRecyclerAdapter.changeMoreStatus(MyRecyclerAdapter.PULLUP_LOAD_MORE);

                        }
                    });
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                 lastVisibleItemPosition = linearLayoutManager.findLastVisibleItemPosition();
//                int [] a = {0,0};
//                 lastVisibleItemPositions = layoutManager.findLastVisibleItemPositions(a);
//                System.out.println(a[0] + " " + a[1]);
                //System.out.println("lastVisibleItemPosition" + lastVisibleItemPosition);
            }
        });






    }

    public void loadData(){
        RetrofitHelper.getGankApi().getGankDatas(title,pageNumber,page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<gank>() {
                    @Override
                    public void onCompleted() {
                        mSwipeRefresh.setRefreshing(false);
                    }

                    @Override
                    public void onError(Throwable e) {
                        //myRecyclerAdapter.setFootViewGone();
                        mSwipeRefresh.setRefreshing(true);
                        Snackbar.make(mRecycle, "连接不上网络", Snackbar.LENGTH_LONG).show();

                    }

                    @Override
                    public void onNext(gank gank) {
                        myRecyclerAdapter.addMoreItem(gank.results);
                    }
                });
    }
}
