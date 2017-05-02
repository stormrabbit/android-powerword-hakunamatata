package com.siegfrield.hakuna_matata.ui.holder;

import android.databinding.ViewDataBinding;
import android.view.View;

/**
 * Created by Administrator on 2017/4/21.
 */

public abstract class TVHolder extends BaseHolder {


    public TVHolder(ViewDataBinding binding) {
        super(binding);
    }

    public abstract void setFocusChange(boolean b);

}
