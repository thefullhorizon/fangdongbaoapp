package com.hx.fdb.ui.widget.pop;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.hx.fdb.R;

public class PopupWindowUtil {

    private PopupWindow mPopupWindow;
    private Context context;
    private LinearLayout layout;

    public PopupWindowUtil(Context context) {
        this.context = context;
        initPop();
    }

    private void initPop() {
        DisplayMetrics dm = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenWidth = dm.widthPixels;
        int screenHeight = dm.heightPixels;
        layout = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.pop, null);
        layout.getBackground().setAlpha(130);
        if (mPopupWindow == null) {
            mPopupWindow = new PopupWindow(layout, screenWidth, screenHeight);
        }
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        // 使其聚集
        mPopupWindow.setFocusable(true);
        // 设置允许在外点击消失
        mPopupWindow.setOutsideTouchable(true);
    }

    /**
     * 设置PopWindow宽高
     *
     * @param width
     * @param height
     */
    public void setmPopHight(int width, int height) {
        layout.setLayoutParams(new LinearLayout.LayoutParams(width, height));
    }

    public void dimiss() {
        if (mPopupWindow != null) {
            mPopupWindow.dismiss();
            mPopupWindow.setFocusable(true);
        }
    }

    public void showFromBottom() {
        mPopupWindow.setAnimationStyle(R.anim.trans_from_bottom);
        //mPopupWindow.showAtLocation();
    }

}
