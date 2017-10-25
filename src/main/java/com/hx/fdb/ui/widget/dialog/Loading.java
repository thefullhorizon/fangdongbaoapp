package com.hx.fdb.ui.widget.dialog;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;

import com.hx.fdb.R;

/**
 * Created by yanxin on 17/4/18.
 */

public class Loading {

    private AppAlertDialog loadingDialog;
    private TextView textView;

    private ObjectAnimator objectAnimator;

    public Loading(AppAlertDialog loadingDialog,ObjectAnimator objectAnimator) {
        this.loadingDialog = loadingDialog;
        this.objectAnimator = objectAnimator;
    }

    public void show() {
        show(null);
    }

    public void show(String txt) {
        if(loadingDialog != null && !loadingDialog.isShowing()) {
            if(!TextUtils.isEmpty(txt) && textView != null) {
                textView.setText(txt);
                textView.setVisibility(View.VISIBLE);
            } else if(textView != null) {
                textView.setVisibility(View.GONE);
            }
            if(objectAnimator != null) {
                objectAnimator.start();
            }
            loadingDialog.show();
        }
    }

    public void hide() {
        if(loadingDialog != null && loadingDialog.isShowing()) {
            if(objectAnimator != null) {
                objectAnimator.cancel();
            }
            loadingDialog.dismiss();
        }
    }

    //APP 加载等待dialog
    public static Loading create(Activity activity) {
        View view = LayoutInflater.from(activity).inflate(R.layout.dialog_loading,null);
        AppAlertDialog dialog = AppAlertDialog.createLoading(activity,R.style.MyDialog_Trans,view);
        dialog.setCanceledOnTouchOutside(false);
        //dialog.setCancelable(false);
        View loadingImg = view.findViewById(R.id.loading);
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(
                loadingImg, "rotation", 0, 360);
        objectAnimator.setDuration(400);
        objectAnimator.setRepeatCount(ValueAnimator.INFINITE);
        objectAnimator.setRepeatMode(ValueAnimator.RESTART);
        objectAnimator.setInterpolator(new LinearInterpolator());
        //loadingImg.setPivotX(view.getWidth()/2);
        //loadingImg.setPivotY(view.getHeight()/2);
        objectAnimator.start();
        //DialogCollection.getInstance().put(activity.getClass(),dialog);
        Loading loading = new Loading(dialog,objectAnimator);
        loading.textView = ((TextView)view.findViewById(R.id.txt));
        return loading;
    }

}
