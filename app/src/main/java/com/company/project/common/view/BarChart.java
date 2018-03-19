package com.company.project.common.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.company.project.common.model.BarData;
import com.company.project.common.utils.MathUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者: lijun
 * 时间: 2018/3/15 17:32
 * 邮箱: lijun@eims.com.cn
 * 描述：柱状图
 */
public class BarChart extends View{

    private Context mContext;

    private float marginTop;
    private float marginBottom;
    private float marginLeft;
    private float marginRight;

    private float width;
    private float height;

    private float xAxisLineWidth;
    private float yAxisLineWidth;


    private int xLabelCount;
    private int yLabelCount;

    private int groupBarCount=1;                           //每组柱状图个数

    private float groupSpace;
    private float barSpace;
    private float barWidth;


    private List<BarData> dataList=new ArrayList<>();

    private float screenWidth;


    private Paint xyCoordinatePaint;                        //画笔        绘制x轴和y轴

    public BarChart(Context context) {
        super(context);
        this.mContext=context;
    }

    public BarChart(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mContext=context;
    }

    private void initView(){
        marginTop=dp2px(20f);
        marginBottom=dp2px(20f);
        marginLeft=dp2px(20f);
        marginRight=dp2px(20f);

        xyCoordinatePaint=new Paint();
        xyCoordinatePaint.setColor(0xffffffff);
        xyCoordinatePaint.setAntiAlias(true);
        xyCoordinatePaint.setStyle(Paint.Style.STROKE);
        xyCoordinatePaint.setStrokeWidth(dp2px(1f));

    }

    private void setData(List<BarData> dataList){
        this.dataList=dataList;
        postInvalidate();
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        initView();

        width=w-marginLeft-marginRight;
        height=w-marginTop-marginBottom;

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawXYAxis(canvas);


    }

    //画横坐标和纵坐标
    private void drawXYAxis(Canvas canvas){

        canvas.drawLine(marginLeft,height-marginBottom,marginLeft,marginTop,xyCoordinatePaint);
        canvas.drawLine(marginLeft,height-marginBottom,marginRight,height-marginBottom,xyCoordinatePaint);


        for(int i=0;i<dataList.size();i++){
            BarData itemData=dataList.get(i);

            double percent1=itemData.percent1;
            double percent2=itemData.percent2;
            String percent1Str= MathUtil.keep1decimal(itemData.percent1*100)+"%";
            String percent2Str= MathUtil.keep1decimal(itemData.percent1*100)+"%";





        }


    }

    private float dp2px(float dpValue) {
        final float scale = mContext.getResources().getDisplayMetrics().density;
        return dpValue * scale + 0.5f;
    }

    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

}
