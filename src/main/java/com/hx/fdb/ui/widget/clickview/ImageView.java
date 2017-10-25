package com.hx.fdb.ui.widget.clickview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

import com.hx.fdb.R;

/**
 * 点击的时候 会有黑色透明的遮罩 background必须是drawable中配置的
 * <selector xmlns:android="http://schemas.android.com/apk/res/android" >
 <item android:drawable="@color/color_4893F1" android:state_pressed="false"/>
 <item android:drawable="@color/color_4893F1" android:state_pressed="true"/>
 </selector>
 黑色的遮罩会自动绘画
 * Created by yanxin on 17/4/5.
 */
public class ImageView extends AppCompatImageView{

    private Drawable bgDrawable;

    public ImageView(Context context) {
        super(context);
    }

    public ImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (isEnabled() && isPressed()) {
            if (getBackground() instanceof StateListDrawable) {
                if (bgDrawable == null) {
                    bgDrawable = getResources().getDrawable(R.color.color_black_15);
                    bgDrawable.setBounds(0, 0, getWidth(), getHeight());
                }
                bgDrawable.draw(canvas);
            }
        }
    }
}
