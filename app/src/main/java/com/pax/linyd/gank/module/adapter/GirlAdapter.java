package com.pax.linyd.gank.module.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.pax.linyd.gank.R;
import com.pax.linyd.gank.module.network.gank;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by linyd on 2017/5/25.
 */


public class GirlAdapter extends RecyclerView.Adapter<GirlAdapter.ViewHolder> {
    
    public Context mContext;
    public LayoutInflater mInflater;
    private List<gank.GankInfo> data = null;
    public GirlAdapter(Context context){
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
        this.data = new ArrayList<gank.GankInfo>();
        //data.addAll(gankInfo);

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.girlsiitem, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GirlAdapter.ViewHolder holder, int position) {
        String url = data.get(position).getUrl();
        if(!url.isEmpty()){
            Glide.with(mContext)
                    .load(url)
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .placeholder(R.drawable.loading)
                    .error(R.mipmap.splash8)
                    .crossFade()
                    .into(holder.ivGirls);
        }
    }

    public void addMoreItem(List<gank.GankInfo> moreData){
        data.addAll(moreData);
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView ivGirls;
        public ViewHolder(View itemView) {
            super(itemView);
            ivGirls = (ImageView) itemView.findViewById(R.id.ivGirls);
        }
    }
}