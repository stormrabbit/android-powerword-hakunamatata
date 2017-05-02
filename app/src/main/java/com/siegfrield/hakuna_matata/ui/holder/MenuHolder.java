package com.siegfrield.hakuna_matata.ui.holder;

import com.siegfrield.hakuna_matata.databinding.HolderMenuBinding;
import com.siegfrield.hakuna_matata.model.data.MenuEntry;

/**
 * Created by Administrator on 2017/5/2.
 */

public class MenuHolder extends BaseHolder<HolderMenuBinding, MenuEntry> {


    public MenuHolder(HolderMenuBinding binding) {
        super(binding);
    }

    @Override
    public void setData(MenuEntry data) {
        mBinding.tvTitle.setText(data.title);
    }


}
