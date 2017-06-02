package com.siegfrield.hakuna_matata.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.transition.Transition;
import android.view.View;

import com.siegfrield.hakuna_matata.R;
import com.siegfrield.hakuna_matata.databinding.ActivityHostBinding;
import com.siegfrield.hakuna_matata.model.data.MenuEntry;
import com.siegfrield.hakuna_matata.ui.adapter.BaseDataAdapter;
import com.siegfrield.hakuna_matata.ui.adapter.HostMenuAdapter;

import java.util.ArrayList;

public class HostActivity extends BaseActivity<ActivityHostBinding> implements BaseDataAdapter.OnAdapterItemClickListener {
    HostMenuAdapter adapter ;
    ArrayList<MenuEntry> menuList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        updateData();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_host;
    }

    @Override
    protected void initView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mBinding.rvData.setLayoutManager(linearLayoutManager);
        adapter = new HostMenuAdapter();
        adapter.setOnAdapterItemClickListener(this);
        mBinding.rvData.setAdapter(adapter);
        this.menuList = new ArrayList<>();

    }


    public void initData(){
        MenuEntry menuEntry = new MenuEntry("RetrofitDemo", RetrofitDemoActivity.class);
        MenuEntry menuEntry2 = new MenuEntry("LinearLayoutDemo", LinearLayoutDemoActivity.class);


        menuList.add(menuEntry);
        menuList.add(menuEntry2);

    }
    public void updateData(){
        this.adapter.updateData(menuList);
    }

    @Override
    public void onItemClick(int position, View view) {
        MenuEntry entry = adapter.getDataList().get(position);
        Intent intent = new Intent(this, entry.clazz);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
