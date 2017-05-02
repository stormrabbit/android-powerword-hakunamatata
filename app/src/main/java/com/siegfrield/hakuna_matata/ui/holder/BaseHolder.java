package com.siegfrield.hakuna_matata.ui.holder;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.siegfrield.hakuna_matata.model.data.Data;


/**
 * Created by Administrator on 2017/3/23.
 */

public abstract class BaseHolder<V extends ViewDataBinding, T > extends RecyclerView.ViewHolder {
    public V mBinding;
    public int position = -1;
    public BaseHolder(V binding) {
        super(binding.getRoot());
        itemView.setTag(this);
        mBinding = binding;
    }

    public abstract void setData(T data) ;
}
