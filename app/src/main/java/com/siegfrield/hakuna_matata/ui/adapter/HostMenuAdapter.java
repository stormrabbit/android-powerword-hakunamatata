package com.siegfrield.hakuna_matata.ui.adapter;

import android.databinding.ViewDataBinding;
import android.view.View;

import com.siegfrield.hakuna_matata.R;
import com.siegfrield.hakuna_matata.databinding.HolderMenuBinding;
import com.siegfrield.hakuna_matata.model.data.MenuEntry;
import com.siegfrield.hakuna_matata.ui.holder.BaseHolder;
import com.siegfrield.hakuna_matata.ui.holder.MenuHolder;

/**
 * Created by Administrator on 2017/5/2.
 */

public class HostMenuAdapter extends BaseDataAdapter<HolderMenuBinding,MenuEntry> {
    @Override
    protected int getLayoutId() {
        return R.layout.holder_menu;
    }

    @Override
    protected BaseHolder getHolder(HolderMenuBinding binding) {
        return new MenuHolder(binding);
    }


}
