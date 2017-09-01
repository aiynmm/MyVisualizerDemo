package com.sinosoft.myshowhzdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Admin on 2016/11/15.
 */

public class VisualizerView extends View {
    private byte[] mBytes;
    private float[] mPoints;
    private Rect mRect = new Rect();

    private Paint mForePaint = new Paint();

    public VisualizerView(Context context) {
        super(context);
        init();
    }

    public VisualizerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mBytes = null;

        mForePaint.setStrokeWidth(1f);
        mForePaint.setAntiAlias(true);
        mForePaint.setColor(Color.rgb(0, 128, 255));
    }

    public void updateVisualizer(byte[] bytes) {
        mBytes = bytes;
        invalidate();
    }

   /* private byte type = 0;
    @Override
    public boolean onTouchEvent(MotionEvent me)
    {
        // 当用户触碰该组件时，切换波形类型
        if(me.getAction() != MotionEvent.ACTION_DOWN)
        {
            return false;
        }
        type ++;
        if(type >= 3)
        {
            type = 0;
        }
        return true;
    }*/

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (mBytes == null) {
            return;
        }

        mRect.set(0,0,getWidth(),getHeight());
        /*switch(type) {
            // -------绘制块状的波形图-------
            case 0:
                for (int i = 0; i < mBytes.length - 1; i++) {
                    float left = getWidth() * i / (mBytes.length - 1);
                    // 根据波形值计算该矩形的高度
                    float top = mRect.height()/2 - (byte) (mBytes[i + 1] + 128)
                            * (mRect.height()/2) / 128;
                    float right = left + 1;
                    float bottom = mRect.height()/2;
                    canvas.drawRect(left, top, right, bottom, mForePaint);
                }
                break;
            // -------绘制柱状的波形图（每隔18个抽样点绘制一个矩形）-------
            case 1:
                for (int i = 0; i < mBytes.length - 1; i += 18) {
                    float left = mRect.width() * i / (mBytes.length - 1);
                    // 根据波形值计算该矩形的高度
                    float top = mRect.height()/2 - (byte) (mBytes[i + 1] + 128)
                            * (mRect.height()/2) / 128;
                    float right = left + 6;
                    float bottom = mRect.height()/2;
                    canvas.drawRect(left, top, right, bottom, mForePaint);
                }
                break;
            // -------绘制曲线波形图-------
            case 2:

                // 如果point数组还未初始化
                if (mPoints == null || mPoints.length < mBytes.length * 4) {
                    mPoints = new float[mBytes.length * 4];
                }

               // mRect.set(0, 0, getWidth(), getHeight());

                for (int i = 0; i < mBytes.length - 1; i++) {
                    // 计算第i个点的x坐标
                    mPoints[i * 4] = mRect.width() * i / (mBytes.length - 1);
                    // 根据bytes[i]的值（波形点的值）计算第i个点的y坐标
                    mPoints[i * 4 + 1] = mRect.height() / 2
                            + ((byte) (mBytes[i] + 128)) * (mRect.height() / 2) / 128;
                    // 计算第i+1个点的x坐标
                    mPoints[i * 4 + 2] = mRect.width() * (i + 1) / (mBytes.length - 1);
                    // 根据bytes[i+1]的值（波形点的值）计算第i+1个点的y坐标
                    mPoints[i * 4 + 3] = mRect.height() / 2
                            + ((byte) (mBytes[i + 1] + 128)) * (mRect.height() / 2) / 128;
                }
                // 绘制波形曲线
                canvas.drawLines(mPoints, mForePaint);
                break;

        }*/
        if (mPoints == null || mPoints.length < mBytes.length * 4) {
            mPoints = new float[mBytes.length * 4];

        }
        for (int i = 0; i < mBytes.length - 1; i++) {
            mPoints[i * 4] = mRect.width() * i / 9;
            mPoints[i * 4 + 1] = mRect.height() / 2;
            mPoints[i * 4 + 2] = mRect.width() * i / 9;
            mPoints[i * 4 + 3] = 2 + mRect.height() / 2 + mBytes[i];
        }
        canvas.drawLines(mPoints, mForePaint);
    }
}
