package com.siegfrield.hakuna_matata.ui.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.siegfrield.hakuna_matata.ui.holder.BaseHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/23.
 */

public abstract class BaseDataAdapter<V extends ViewDataBinding, T> extends RecyclerView.Adapter<BaseHolder> implements View.OnClickListener{

    private final int resId;
    private OnAdapterItemClickListener oaicl = null;

    private List<T> dataList = null;
    public BaseDataAdapter(){
        this.resId = this.getLayoutId();
    }



    @Override
    public BaseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 根据传入的layout创建view
        View view = LayoutInflater.from(parent.getContext()).inflate(this.resId, parent,false);
        V binding = DataBindingUtil.bind(view);
        BaseHolder baseHolder = this.getHolder(binding);

        return baseHolder;
    }
    @Override
    public void onBindViewHolder(BaseHolder holder, int position) {
        // 添加item clicklistener
        holder.itemView.setOnClickListener(this);
        // 记录holder位置
        holder.position = position;
        T data = dataList.get(position);
        holder.setData(data);

    }
    // 初始化layout
    protected abstract int getLayoutId();

    // 确定holder
    protected abstract BaseHolder getHolder(V binding);

    // 更新数据
    public void updateData(List<T> dataList){
        if(dataList == null ){
            return;
        }
        if(this.dataList == null){
            this.dataList = new ArrayList<>();
        }

        // 如果没数据,则添加新数据
        if(this.dataList == null || this.dataList.size()==0){

                this.dataList = (List<T>) dataList;

            notifyDataSetChanged();
        }else{
            // 否则在老数据后面添加新数据
            for (int i = 0;i< dataList.size();i++){
                this.dataList.add((T) dataList.get(i));
            }
            notifyItemInserted(this.dataList.size() - dataList.size());
        }
    }




    @Override
    public int getItemCount() {
        return this.dataList == null ? 0 : dataList.size();
    }

    @Override
    public void onClick(View view) {
        BaseHolder bHolder = this.getHolderFromView(view);
        if(this.oaicl !=null && bHolder !=null){
            oaicl.onItemClick(bHolder.position,bHolder.itemView);
        }
    }



    public void setOnAdapterItemClickListener(OnAdapterItemClickListener oaicl) {
        this.oaicl = oaicl;
    }



    public BaseHolder getHolderFromView (View view){
        if(view.getTag() instanceof  BaseHolder){
            return (BaseHolder) view.getTag();
        }else{
            return null;
        }
    }

    public  void clearData(){
        if(this.dataList==null){
          return ;
        }
        dataList.clear();
    };

    public List<T> getDataList() {
        return dataList;
    }

    public interface OnAdapterItemClickListener{
        void onItemClick(int position, View view);
    }


}
