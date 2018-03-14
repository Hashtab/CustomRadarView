package com.company.project;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.company.project.common.utils.MathUtil;

import java.util.List;

/**
 * 作者: lijun
 * 时间: 2018/3/12 11:00
 * 邮箱: lijun@eims.com.cn
 * 描述：自定义雷达图控件
 */
public class RadarView extends View {
    private Context mContext;
    private int mVertical_Line_Color = 0xffffffff;
    private int mGapStrokeColor = 0x33ffffff;                   //间隔线颜色

//    private int mOriginX;               //原点的x轴坐标
//    private int mOriginY;               //原点的y轴坐标

    private PointF mPointCenter;          //原点

    private List<RadarData> dataList;

    private int count = 6;

    private double mAngle;

    private float radius;

    private float spiltRadius;

    private int gapCount = 5;


    private Paint mLinePaint;

    private Paint mGapLinePaint1;
    private Paint mGapLinePaint2;
    private Paint mValuePaint;

    public RadarView(Context context) {
        super(context);
    }

    public RadarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
    }


    public void setData(List<RadarData> dataList) {

        if (dataList == null || dataList.size() < 3) {
            throw new RuntimeException("数据量应不少于3");
        } else {
            this.dataList = dataList;
        }

        postInvalidate();

    }

    private void initView() {

        count = dataList.size();
        mAngle = 2 * Math.PI / count;
        radius = Math.min(mPointCenter.x, mPointCenter.y);
        spiltRadius = radius / gapCount / 2;

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mPointCenter = new PointF(w / 2, h / 2);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        Paint paint=new Paint();
//        paint.setAntiAlias(true);       //抗锯齿功能
//        paint.setStyle(Paint.Style.FILL);       //内部填充
//        paint.setColor(mVertical_Line_Color);   //设置画笔颜色

        if (dataList == null || dataList.size() == 0) {
            return;
        }
        initView();
        initPaint();
        drawLine(canvas);
//        drawPolygon(canvas);
        drawRecGap(canvas);
        drawDataPoint(canvas);

    }

    private void initPaint() {
        mLinePaint = new Paint();
        mLinePaint.setAntiAlias(true);
        mLinePaint.setStyle(Paint.Style.STROKE);
        mLinePaint.setColor(mVertical_Line_Color);

        mGapLinePaint1 = new Paint();
        mGapLinePaint1.setAntiAlias(true);
        mGapLinePaint1.setStyle(Paint.Style.STROKE);
        mGapLinePaint1.setColor(Color.TRANSPARENT);
        mGapLinePaint1.setStrokeWidth(spiltRadius);

        mGapLinePaint2 = new Paint();
        mGapLinePaint2.setAntiAlias(true);
        mGapLinePaint2.setStyle(Paint.Style.STROKE);
        mGapLinePaint2.setColor(mGapStrokeColor);
        mGapLinePaint2.setStrokeWidth(spiltRadius);

        mValuePaint = new Paint();
        mValuePaint.setAntiAlias(true);
        mValuePaint.setStyle(Paint.Style.FILL);
        mValuePaint.setColor(mVertical_Line_Color);
        mValuePaint.setAlpha(125);


    }

    //计算出各个点的横坐标和纵坐标
    private PointF computePointXY(int position, float radius) {

        float pointX = (float) (mPointCenter.x + radius * Math.sin(mAngle * position - mAngle / 2));
        float pointY = (float) (mPointCenter.y + radius * Math.cos(mAngle * position - mAngle / 2));

        PointF pointF = new PointF(pointX, pointY);

        return pointF;

    }


    private PointF computeWebPointXY(int position, float radius) {

        float pointX = (float) (mPointCenter.x + (radius) * Math.sin(mAngle * position - mAngle / 2));
        float pointY = (float) (mPointCenter.y + (radius) * Math.cos(mAngle * position - mAngle / 2));

        PointF pointF = new PointF(pointX, pointY);

        return pointF;

    }


    //画线
    private void drawLine(Canvas canvas) {
        Path path = new Path();
        for (int i = 0; i < dataList.size(); i++) {
            float x = computePointXY(i, radius).x;
            float y = computePointXY(i, radius).y;
            path.moveTo(mPointCenter.x, mPointCenter.y);
            path.lineTo(x, y);
        }
        path.close();
        canvas.drawPath(path, mLinePaint);
    }

    private void drawPolygon(Canvas canvas) {
        Path path = new Path();
        for (int i = 0; i < dataList.size(); i++) {
            float x = computePointXY(i, radius).x;
            float y = computePointXY(i, radius).y;
            if (i == 0) {
                path.moveTo(x, y);
            } else {
                path.lineTo(x, y);
            }
        }
        path.close();
        canvas.drawPath(path, mLinePaint);
    }


    //画间距
    private void drawRecGap(Canvas canvas) {
        Path path = new Path();

        for (int j = 0; j < gapCount * 2; j++) {

            path.reset();
            for (int i = 0; i < dataList.size(); i++) {

                float x = computeWebPointXY(i, spiltRadius * j+spiltRadius/2).x;
                float y = computeWebPointXY(i, spiltRadius * j+spiltRadius/2).y;

                if (i == 0) {
                    path.moveTo(x, y);
                } else {
                    path.lineTo(x, y);
                }
            }
            path.close();
            if (j % 2 == 0) {

//                mGapLinePaint1 = new Paint();
//                mGapLinePaint1.setAntiAlias(true);
//                mGapLinePaint1.setStyle(Paint.Style.STROKE);
//                mGapLinePaint1.setColor(Color.TRANSPARENT);
//                mGapLinePaint1.setStrokeWidth(spiltRadius);
                canvas.drawPath(path, mGapLinePaint1);

            } else {
//                mGapLinePaint2.setAntiAlias(true);
//                mGapLinePaint2.setStyle(Paint.Style.STROKE);
//                mGapLinePaint2.setColor(Color.GRAY);
//                mGapLinePaint2.setStrokeWidth(spiltRadius);
                canvas.drawPath(path, mGapLinePaint2);
            }
        }
    }


    private float valuePointRadius=2f;
    //画出数据点
    private void drawDataPoint(Canvas canvas){
        Path path=new Path();

        for(int i=0;i<dataList.size();i++){
            RadarData itemData=dataList.get(i);
            double percent=itemData.percent;
            float x=computePointXY(i,(float) (radius*percent)).x;
            float y=computePointXY(i,(float) (radius*percent)).y;

            if(i==0){
                path.moveTo(x,y);
            }else {
                path.lineTo(x,y);
            }

            canvas.drawCircle(x,y,dp2px(valuePointRadius),mLinePaint);
        }

        path.close();

        canvas.drawPath(path,mValuePaint);
    }


    private void drawValueText(Canvas canvas){
        for(int i=0;i<dataList.size();i++){
            RadarData itemData=dataList.get(i);
            double percent=itemData.percent;

            String value=MathUtil.keep2decimal(percent);

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
