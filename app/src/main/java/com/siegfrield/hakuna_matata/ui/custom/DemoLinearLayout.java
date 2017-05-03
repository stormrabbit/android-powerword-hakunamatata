package com.siegfrield.hakuna_matata.ui.custom;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.siegfrield.hakuna_matata.utils.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2017/5/3.
 */

public class DemoLinearLayout extends LinearLayout {
    private List<String> tags;
    private static final int margin = 15;
    public DemoLinearLayout(Context context) {
        this(context, null ,0);

    }

    public DemoLinearLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DemoLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    public void init(){
        this.setOrientation(VERTICAL);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {


        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

//        if(widthSize != 0){
//            this.drawTags();
//        }

    }


    public void measureTest (){
        int w = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
        int h = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);

        this.measure(w, h);
        int width = this.getMeasuredWidth(); // 获取宽度
        int height = this.getMeasuredHeight(); // 获取高度

//        this.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
//            @Override
//            public boolean onPreDraw() {
//                DemoLinearLayout.this.getViewTreeObserver().removeOnPreDrawListener(this);
//                int width = DemoLinearLayout.this.getWidth(); // 获取宽度
//                int height = DemoLinearLayout.this.getHeight(); // 获取高度
//                return true;
//            }
//        });
    }
    public void setTags(List<String> tags){
        this.tags = tags;
    }

    private void drawTags() {
        if(this.tags == null || this.tags.size() == 0){
            return ;
        }
        ArrayList<TextView> tViews = new ArrayList<>();
        int widthMeasureSpec = MeasureSpec.makeMeasureSpec(0,
                MeasureSpec.UNSPECIFIED);
        int heightMeasureSpec = MeasureSpec.makeMeasureSpec(0,
                MeasureSpec.UNSPECIFIED);

        // 1 首先,测量出content的宽度
        int lineWidth = 0;

        int contentWidth = this.getMeasuredWidth();

        if(contentWidth == 0){
            return ;
        }

        // 2 创建一个横排linearlayout,宽度设置为matchparent,高度为0dp,weight1,
        LinearLayout llLine = new LinearLayout(this.getContext());
        llLine.setOrientation(LinearLayout.HORIZONTAL);

        LayoutParams layoutParams = new LayoutParams(
                LayoutParams.MATCH_PARENT, 0, 1.0f);


        // 2.1开始循环
        for (String name : tags) {
            // 3 创建一个textview,设置内容
            TextView tView = new TextView(getContext());
            tView.setText(name);
            // 4 测量textView的宽度
            tView.measure(widthMeasureSpec, heightMeasureSpec);
            int tViewWidth = tView.getMeasuredWidth();
            // 5 将宽度和margin值累加
            // 6 如果宽度值小于content的宽度,将textView保存在集合中,继续下一步
            if ((lineWidth + margin * 2 + tViewWidth) < contentWidth) {
                tViews.add(tView);
                lineWidth = margin * 2 + tViewWidth + lineWidth;
            }
            // 7 如果宽度之大于等于content的高度,则减去累加值
            else {
                // 8 用content的宽度-目前累加的宽度,得出剩余宽度
                int leftWidth = contentWidth - lineWidth;
                // 9 用剩余宽度除以集合的个数,获得panding值
                int padding = leftWidth / tViews.size();
                // 10 从集合中拿出textview,按照padding值,marging值设置lieanrylayout
                for (int i = 0; i < tViews.size(); i++) {
                    TextView textView = tViews.get(i);
                    LayoutParams layoutParams2 = new LayoutParams(
                            layoutParams.WRAP_CONTENT,
                            layoutParams.WRAP_CONTENT);
                    layoutParams2.rightMargin = margin;
                    layoutParams2.leftMargin = margin;
                    int paddingLeft = textView.getPaddingLeft();
                    int paddingRight = textView.getPaddingRight();
                    int paddingBottom = textView.getPaddingBottom();
                    int paddingTop = textView.getPaddingTop();
                    textView.setPadding(paddingLeft + (padding / 2),
                            paddingTop, paddingRight + (padding / 2),
                            paddingBottom);
                    llLine.addView(textView, layoutParams2);
                }
                // 11 将linearlayout添加至content上
                this.addView(llLine, layoutParams);
                // 12 清除集合数据 累加宽度清零 新建linearlayout
                tViews.clear();
                tViews.add(tView);
                lineWidth = margin * 2 + tViewWidth;
                llLine = new LinearLayout(getContext());
                // 13 集合数据中添加新的textview,宽度新的累加
                // 14 继续下一轮循环
            }

        }
        // 循环结束后,检查tView里面的值,如果还有剩余,则继续添加

        if (tViews.size() != 0) {
            // 8 用content的宽度-目前累加的宽度,得出剩余宽度
            int leftWidth = contentWidth - lineWidth;
            // 9 用剩余宽度除以集合的个数,获得panding值
            int padding = leftWidth / tViews.size();
            for (int i = 0; i < tViews.size(); i++) {
                TextView textView = tViews.get(i);
                LayoutParams layoutParams2 = new LayoutParams(
                        layoutParams.WRAP_CONTENT, layoutParams.WRAP_CONTENT);
                layoutParams2.rightMargin = margin;
                layoutParams2.leftMargin = margin;
                int paddingLeft = textView.getPaddingLeft();
                int paddingRight = textView.getPaddingRight();
                int paddingBottom = textView.getPaddingBottom();
                int paddingTop = textView.getPaddingTop();
                textView.setPadding(paddingLeft + (padding / 2), paddingTop,
                        paddingRight + (padding / 2), paddingBottom);
                llLine.addView(textView, layoutParams2);
            }
            // 11 将linearlayout添加至content上
            this.addView(llLine, layoutParams);
            // 13 集合数据中添加新的textview,宽度新的累加
            // 14 继续下一轮循环

        }

    }
}
