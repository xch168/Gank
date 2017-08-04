package com.github.xch168.gank.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.github.xch168.gank.R;
import com.github.xch168.gank.entity.Gank;
import com.github.xch168.gank.ui.WebActivity;
import com.github.xch168.gank.util.DateUtil;
import com.github.xch168.quickrecycleradapter.QuickAdapter;
import com.github.xch168.quickrecycleradapter.QuickViewHolder;

/**
 * Created by xch on 2017/3/5.
 */

public class DataAdapter extends QuickAdapter<Gank> implements View.OnClickListener {

    public DataAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getLayoutResId(int viewType) {
        return R.layout.view_item;
    }

    @Override
    protected void convert(QuickViewHolder holder, Gank gank) {
        holder.getView().setOnClickListener(this);
        holder.getView().setTag(gank);
        holder.setText(R.id.tv_title, gank.desc);
        holder.setText(R.id.tv_type, gank.type);
        holder.setText(R.id.tv_created_at, DateUtil.format(gank.createdAt));
        if (gank.images != null && gank.images.size() > 0) {
            holder.setVisible(R.id.iv_img, true);
            Glide.with(context).load(gank.images.get(0)).into((ImageView) holder.getView(R.id.iv_img));
        }
        else
        {
            holder.setVisible(R.id.iv_img, false);
        }
    }

    @Override
    public void onClick(View view) {
        Gank gank = (Gank) view.getTag();
        WebActivity.startActivity(context, gank.desc, gank.url);
    }
}
