package com.pax.linyd.gank.module.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.pax.linyd.gank.R;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by linyd on 2017/4/19.
 */
public class MyRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private LayoutInflater mInflater;
    //private String[] mTitles = null;
    private List<String> data = null;
    private static final int TYPE_ITEM =0;  //普通Item View
    private static final int TYPE_FOOTER = 1;  //顶部FootView
    //上拉加载更多
    public static final int  PULLUP_LOAD_MORE=0;
    //正在加载中
    public static final int  LOADING_MORE=1;
    //上拉加载更多状态-默认为0
    private int load_more_status=0;
    public Context context;
    public MyRecyclerAdapter(Context context){
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        this.data = new ArrayList<String>();
        for(int i = 0;i < 20; i++){

            data.add("Item" + i);

        }
    }

    @Override
    public int getItemViewType(int position) {
        //System.out.println("position" + position);
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
            View footview = mInflater.inflate(R.layout.recycler_load_more_layout, parent, false);
            FootViewHolder footViewHolder = new FootViewHolder(footview);
            return  footViewHolder;
        }
        return  null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof   ItemViewHolder){
            ((ItemViewHolder) holder).tv.setText(data.get(position));
            ((ItemViewHolder) holder).getParentView().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "点击",Toast.LENGTH_SHORT).show();
                }
            });

        }else if (holder instanceof FootViewHolder) {
                FootViewHolder footViewHolder = (FootViewHolder) holder;
                switch (load_more_status){
                    case PULLUP_LOAD_MORE:
                        footViewHolder.foot_view_item_tv.setText("上拉加载更多");
                        break;
                    case LOADING_MORE:
                        footViewHolder.foot_view_item_tv.setText("正在加载更多数据");
                        break;

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
    public void addItem(List<String> newDatas){
        newDatas.addAll(data);
        data.removeAll(data);
        data.addAll(newDatas);
        notifyDataSetChanged();
    }

    public void addMoreItem(List<String> moreData){
        data.addAll(moreData);
        notifyDataSetChanged();
    }

    public void changeMoreStatus(int status){
        load_more_status=status;
        notifyDataSetChanged();
    }


}
