package com.siegfrield.hakuna_matata.ui.adapter;

import android.view.View;

import com.siegfrield.hakuna_matata.ui.holder.BaseHolder;
import com.siegfrield.hakuna_matata.ui.holder.TVHolder;

/**
 * Created by Administrator on 2017/4/21.
 */

public abstract class TVDataAdapter extends BaseDataAdapter implements View.OnFocusChangeListener{
    private OnAdapterFocusChangeListener oafcl = null;

    @Override
    public void onBindViewHolder(BaseHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        holder.itemView.setOnFocusChangeListener(this);
    }

    public void setOafcl(OnAdapterFocusChangeListener oafcl) {
        this.oafcl = oafcl;
    }

    public interface OnAdapterFocusChangeListener{
        void onItemFocusChange(int position, View view, boolean isFocus);
    }

    @Override
    public void onFocusChange(View view, boolean b) {
        BaseHolder bHolder = this.getHolderFromView(view);
        if(bHolder!=null){
            ((TVHolder)bHolder).setFocusChange(b);
            if(this.oafcl !=null){
                oafcl.onItemFocusChange(bHolder.position,bHolder.itemView,b);
            }
        }
    }
}
