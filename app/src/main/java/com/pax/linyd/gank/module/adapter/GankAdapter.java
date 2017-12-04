package com.pax.linyd.gank.module.adapter;

import android.content.Context;
import android.support.transition.Visibility;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.pax.linyd.gank.R;
import com.pax.linyd.gank.module.home.WebActivity;
import com.pax.linyd.gank.module.network.gank;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by linyd on 2017/4/19.
 */
public class GankAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private  ImageView iv;
    private LayoutInflater mInflater;
    //private String[] mTitles = null;
    private List<gank.GankInfo> data = null;
    private static final int TYPE_ITEM =0;  //普通Item View
    private static final int TYPE_FOOTER = 1;  //顶部FootView
    //上拉加载更多
    public static final int  PULLUP_LOAD_MORE=0;
    //正在加载中
    public static final int  LOADING_MORE=1;
    //上拉加载更多状态-默认为0
    private int load_more_status=0;
    public Context context;
    private View footview;

    public GankAdapter(Context context){
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        this.data = new ArrayList<gank.GankInfo>();
        //data.addAll(gankInfo);

    }

    @Override
    public int getItemViewType(int position) {
       //  System.out.println("position" + position);

            if(position + 1 == getItemCount()){
                return  TYPE_FOOTER;
            }else{
                return TYPE_ITEM;
            }




    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == TYPE_ITEM){
            View view = mInflater.inflate(R.layout.recycleview_item, parent, false);

            ItemViewHolder viewHolder = new ItemViewHolder(view);
            return viewHolder;
        }
        if (viewType == TYPE_FOOTER) {
             footview = mInflater.inflate(R.layout.recycler_load_more_layout, parent, false);
            FootViewHolder footViewHolder = new FootViewHolder(footview);
            return  footViewHolder;
        }
        return  null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if(holder instanceof   ItemViewHolder){
             final gank.GankInfo gankInfo = data.get(position);
            if(gankInfo.getImages() != null){
                String url = gankInfo.getImages().get(0);
                if(!url.isEmpty()){
                    Glide.with(context).
                            load(url).
                            placeholder(R.drawable.loading).
                            error(R.mipmap.splash8).
                            crossFade().
                            into(iv);
                }
            }else{
                iv.setVisibility(View.GONE);
            }
            ((ItemViewHolder) holder).tv.setText(gankInfo.getDesc());

            ((ItemViewHolder) holder).getParentView().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    WebActivity.launch(context,gankInfo.getUrl(),gankInfo.getDesc());
                    Toast.makeText(context, "点击",Toast.LENGTH_SHORT).show();
                }
            });

        }else if (holder instanceof FootViewHolder) {
            FootViewHolder footViewHolder = (FootViewHolder) holder;
            if(getItemCount() != 1){
                switch (load_more_status){
                    case PULLUP_LOAD_MORE:
                        footViewHolder.foot_view_item_tv.setText("上拉加载更多");
                        break;
                    case LOADING_MORE:
                        footViewHolder.foot_view_item_tv.setText("正在加载更多数据");
                        break;

                }
            }else{
                footViewHolder.foot_view_item_tv.setVisibility(View.GONE);
            }


        }

    }



    @Override
    public int getItemCount() {
        return data.size() + 1;
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        public TextView tv;
        public View parentView;
        public ItemViewHolder(View itemView) {
            super(itemView);
            this.parentView = itemView;
            tv = (TextView) itemView.findViewById(R.id.item_desc);
            iv = (ImageView) itemView.findViewById(R.id.item_image);
        }
        public View getParentView(){
            return  parentView;
        }

    }

    class FootViewHolder extends  RecyclerView.ViewHolder{
        private TextView foot_view_item_tv;

        public FootViewHolder(View view) {
            super(view);

            foot_view_item_tv=(TextView)view.findViewById(R.id.foot_view_item_tv);
        }


    }
    public void addItem(List<gank.GankInfo> newDatas){
        newDatas.addAll(data);
        data.removeAll(data);
        data.addAll(newDatas);
        notifyDataSetChanged();
    }

    public void addMoreItem(List<gank.GankInfo> moreData){
        data.addAll(moreData);
        notifyDataSetChanged();
    }

    public void changeMoreStatus(int status){
        load_more_status=status;
        notifyDataSetChanged();
    }

    public void removeAll(){
        data.clear();
    }

    public void setFootViewGone(){
        if(footview != null){
            footview.setVisibility(View.GONE);
        }
        notifyDataSetChanged();
    }
}
