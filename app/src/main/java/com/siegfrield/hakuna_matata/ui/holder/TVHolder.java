package com.siegfrield.hakuna_matata.ui.holder;

import android.view.View;

/**
 * Created by Administrator on 2017/4/21.
 */

public abstract class TVHolder extends BaseHolder {

    public TVHolder(View itemView) {
        super(itemView);
    }

    public abstract void setFocusChange(boolean b);

}
