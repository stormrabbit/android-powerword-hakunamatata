package com.siegfrield.hakuna_matata.ui.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.siegfrield.hakuna_matata.model.data.Data;


/**
 * Created by Administrator on 2017/3/23.
 */

public abstract class BaseHolder extends RecyclerView.ViewHolder {
    public int position = -1;
    public BaseHolder(View itemView) {
        super(itemView);
        itemView.setTag(this);
        initView(itemView);
    }

    public abstract void initView(View itemView);

    public abstract void setData(Data data) ;
}
