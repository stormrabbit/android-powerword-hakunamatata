package com.siegfrield.hakuna_matata.controller.holder;

import android.view.View;

import com.siegfrield.hakuna_matata.model.data.Data;

/**
 * Created by Administrator on 2017/4/21.
 */

public abstract class TVHolder extends BaseHolder {

    public TVHolder(View itemView) {
        super(itemView);
    }

    public abstract void setFocusChange(boolean b);

}
