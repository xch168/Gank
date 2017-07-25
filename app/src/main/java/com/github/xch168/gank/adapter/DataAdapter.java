package com.github.xch168.gank.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.xch168.gank.R;
import com.github.xch168.gank.entity.Gank;
import com.github.xch168.gank.util.DateUtil;

import java.util.List;

/**
 * Created by xch on 2017/3/5.
 */

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {

    private Context mContext;
    private LayoutInflater mLayoutInflater;

    private List<Gank> mDatas;

    public DataAdapter(Context contxt) {
        mContext = contxt;
        mLayoutInflater = LayoutInflater.from(contxt);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mLayoutInflater.inflate(R.layout.view_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Gank gank = mDatas.get(position);
        holder.titleView.setText(gank.desc);
        holder.typeView.setText(gank.type);
        holder.createdAtView.setText(DateUtil.format(gank.createdAt));
        if (gank.images != null && gank.images.size() > 0) {
            holder.imageView.setVisibility(View.VISIBLE);
            Glide.with(mContext).load(gank.images.get(0)).into(holder.imageView);
        }
        else
        {
            holder.imageView.setVisibility(View.GONE);
        }
    }


    @Override
    public int getItemCount() {
        return mDatas == null ? 0 : mDatas.size();
    }

    public void setDatas(List<Gank> datas) {
        mDatas = datas;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView titleView;
        TextView typeView;
        TextView createdAtView;
        ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);

            titleView = (TextView) itemView.findViewById(R.id.tv_title);
            typeView = (TextView) itemView.findViewById(R.id.tv_type);
            createdAtView = (TextView) itemView.findViewById(R.id.tv_created_at);
            imageView = (ImageView) itemView.findViewById(R.id.iv_img);
        }
    }
}
