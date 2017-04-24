package com.siegfrield.hakuna_matata.ui.custom;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;

import com.siegfrield.hakuna_matata.ui.adapter.BaseDataAdapter;
import com.siegfrield.hakuna_matata.ui.holder.BaseHolder;
import com.siegfrield.hakuna_matata.model.data.Data;

import java.util.List;

/**
 * @author 龙骑将杨影枫
 * Android TV 专用的recycleView
 * 1 保证滚动时焦点不移动(方法很粗暴)
 * 2 滚动到边缘位置判断是否需要加载数据(方法更粗暴)
 * 3 不知道有什么大坑,不过目前没用出来
 */
public class TVRecyclerView extends RecyclerView {

    private OnLoadingMoreListener mOnLoadingMoreListener;
    private View leftFocusView = null;
    private View upFocusView = null;
    private View SelectView;

    public TVRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public TVRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TVRecyclerView(Context context) {
        super(context);
    }

    public void setSelectView(View v) {
        this.SelectView = v;
    }

    public View getSelectView() {
        return SelectView;
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getAction() != KeyEvent.ACTION_DOWN)
            return super.dispatchKeyEvent(event);

        try {


            Integer direction = null;
            if (event.getKeyCode() == KeyEvent.KEYCODE_DPAD_UP) {
                direction = View.FOCUS_UP;
            } else if (event.getKeyCode() == KeyEvent.KEYCODE_DPAD_LEFT) {
                direction = View.FOCUS_LEFT;
            } else if (event.getKeyCode() == KeyEvent.KEYCODE_DPAD_RIGHT) {
                direction = View.FOCUS_RIGHT;
            } else if (event.getKeyCode() == KeyEvent.KEYCODE_DPAD_DOWN) {
                direction = View.FOCUS_DOWN;
            }
            if (direction == null)
                return super.dispatchKeyEvent(event);
            int childTotal = getChildCount();
            View focusView = null;
            int indexCount = -1;
            for (int i = 0; i < childTotal; i++) {
                View child = getChildAt(i);
                if (child.hasFocus()) {
                    focusView = child;
                    indexCount = i;
                    break;
                }
            }
            if (focusView == null || indexCount == -1)
                return super.dispatchKeyEvent(event);

            if (!(getAdapter() instanceof BaseDataAdapter))
                return super.dispatchKeyEvent(event);


            BaseDataAdapter adapter = (BaseDataAdapter) getAdapter();
            List<Data> dataList = adapter.getDataList();
            if (!(focusView.getTag() instanceof BaseHolder)) {
                return super.dispatchKeyEvent(event);
            }
            BaseHolder rHolder = (BaseHolder) focusView.getTag();

            int orientation = this.getLayoutOrientaion();
            if (orientation == -1) {
                return super.dispatchKeyEvent(event);
            }
            int column = 1;
            if (getLayoutManager() instanceof GridLayoutManager) {
                GridLayoutManager gManager = (GridLayoutManager) getLayoutManager();
                column = gManager.getSpanCount();
            }
            int loadIndex = -1;
            switch (direction) {
                case View.FOCUS_LEFT:
                    if (orientation == OrientationHelper.HORIZONTAL) {
                        if (indexCount <= column - 1 && this.leftFocusView != null) {
                            leftFocusView.requestFocus();
                            return true;
                        } else if (indexCount >= column) {
                            scrollBy(-rHolder.itemView.getWidth(), 0);
                            getChildAt(indexCount - column).requestFocus();
                            return true;
                        } else {
                            return super.dispatchKeyEvent(event);
                        }
                    } else if (orientation == OrientationHelper.VERTICAL) {
                        if ((indexCount + 1) % column == 1 && this.leftFocusView != null) {
                            leftFocusView.requestFocus();
                            return true;
                        } else {
                            return super.dispatchKeyEvent(event);
                        }
                    } else {
                        return super.dispatchKeyEvent(event);
                    }
                case View.FOCUS_UP:
                    if (orientation == OrientationHelper.HORIZONTAL) {
                        return super.dispatchKeyEvent(event);
                    } else if (orientation == OrientationHelper.VERTICAL) {
                        if (indexCount < column) {
                            if (upFocusView != null) {
                                upFocusView.requestFocus();
                                return true;
                            } else {
                                return super.dispatchKeyEvent(event);
                            }
                        } else {
                            scrollBy(0, -rHolder.itemView.getHeight());
                            getChildAt(indexCount - column).requestFocus();
                            return true;
                        }
                    } else {
                        return super.dispatchKeyEvent(event);
                    }
                case View.FOCUS_RIGHT:
                    if (orientation == OrientationHelper.HORIZONTAL) {
                        if (column == 1) {
                            if ((dataList.size() - 1) == rHolder.position) {
                                if (this.mOnLoadingMoreListener != null) {
                                    this.mOnLoadingMoreListener.loadingMore();
                                    return true;
                                } else {
                                    return super.dispatchKeyEvent(event);
                                }
                            }
                        }
                        int loadingMoreIndex = (dataList.size() + 1) % column;
                        if ((rHolder.position + loadingMoreIndex) < dataList.size() - column) {
                            return super.dispatchKeyEvent(event);
                        } else {
                            if (this.mOnLoadingMoreListener != null) {
                                this.mOnLoadingMoreListener.loadingMore();
                                return true;
                            } else {
                                return super.dispatchKeyEvent(event);
                            }
                        }
                    } else if (orientation == OrientationHelper.VERTICAL) {
                        if ((indexCount + 1) % column == 0) {
                            return true;
                        } else {
                            return super.dispatchKeyEvent(event);
                        }
                    }
                case View.FOCUS_DOWN:
                    if (orientation == OrientationHelper.HORIZONTAL) {

                    } else if (orientation == OrientationHelper.VERTICAL) {
                        if (column == 1) {
                            if ((dataList.size() - 1) == rHolder.position) {
                                if (this.mOnLoadingMoreListener != null) {
                                    this.mOnLoadingMoreListener.loadingMore();
                                    return true;
                                } else {
                                    return super.dispatchKeyEvent(event);
                                }
                            }
                        }
                        int loadingMoreIndex = (dataList.size() + 1) % column;
                        if ((rHolder.position + loadingMoreIndex) > dataList.size()) {
                            if (mOnLoadingMoreListener != null) {
                                mOnLoadingMoreListener.loadingMore();
                                return true;
                            }
                        } else {
                            scrollBy(0, rHolder.itemView.getHeight());
                            if (getChildAt(indexCount + column) != null)
                                getChildAt(indexCount + column).requestFocus();
                        }
                    }

                default:
                    break;
            }
        } catch (Exception e) {
            System.out.println("这里有错误!===>" + e.toString());
        }
        return super.dispatchKeyEvent(event);
    }

    private int getLayoutOrientaion() {
        LayoutManager lManager = getLayoutManager();
        if (lManager instanceof GridLayoutManager) {
            GridLayoutManager gManager = (GridLayoutManager) lManager;
            return gManager.getOrientation();
        } else if (lManager instanceof LinearLayoutManager) {
            LinearLayoutManager llManager = (LinearLayoutManager) lManager;
            return llManager.getOrientation();
        }
        return -1;
    }

    public void setOnLoadingMoreListener(
            OnLoadingMoreListener mOnLoadingMoreListener) {
        this.mOnLoadingMoreListener = mOnLoadingMoreListener;
    }

    public interface OnLoadingMoreListener {
        void loadingMore();
    }
}