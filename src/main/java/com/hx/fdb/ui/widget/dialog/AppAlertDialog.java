package com.hx.fdb.ui.widget.dialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.hx.fdb.R;

/**
 * 自定义dialog view
 * 可以定制显示动画
 * Created by yanxin on 17/4/13.
 */
public class AppAlertDialog extends AlertDialog {

    private boolean isSetup;
    private View mView;

    public AppAlertDialog(@NonNull Context context) {
        super(context,R.style.MyDialog);
    }

    protected AppAlertDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
    }

    protected AppAlertDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public static AppAlertDialog create(Context context, View view) {
        AppAlertDialog dialog = new AppAlertDialog(context);
        DialogCollection.getInstance().put(context.getClass(),dialog);
        dialog.setView(view);
        return dialog;
    }

    public static AppAlertDialog create(Context context,int theme, View view) {
        AppAlertDialog dialog = new AppAlertDialog(context,theme);
        DialogCollection.getInstance().put(context.getClass(),dialog);
        dialog.setView(view);
        return dialog;
    }

    public static AppAlertDialog createLoading(Context context,int theme, View view) {
        AppAlertDialog dialog = new AppAlertDialog(context,theme);
        DialogCollection.getInstance().putLoading(context.getClass(),dialog);
        dialog.setView(view);
        return dialog;
    }

    @Override
    public void setView(View view) {
        super.setView(view);
        this.mView = view;
    }

    public void setText(int id, CharSequence charSequence) {
        ((TextView)mView.findViewById(id)).setText(charSequence);
    }

    public void setTag(int id, Object obj) {
        mView.findViewById(id).setTag(obj);
    }

    public void setEnabled(int id,boolean enable) {
        mView.findViewById(id).setEnabled(enable);
    }

    public void setBackground(int id, int bgId) {
        mView.findViewById(id).setBackgroundResource(bgId);
    }

    public void setOnClickListener(int id, View.OnClickListener onClickListener) {
        mView.findViewById(id).setOnClickListener(onClickListener);
    }

    private void setAnimation(int anim) {
        Window window = getWindow(); //得到对话框
        window.setWindowAnimations(anim); //设置窗口弹出动画
        window.setBackgroundDrawableResource(R.color.transparent); //设置对话框背景为透明
        window.getDecorView().setPadding(0, 0, 0, 0); //消除边距
    }

    private void setLayoutParam(int x,int gravity) {
        Window window = getWindow();
        WindowManager.LayoutParams wl = window.getAttributes();
        wl.width = x;
        wl.gravity = gravity;
        window.setAttributes(wl);
    }

    private void setLayoutParam(int x,int gravity,int top) {
        Window window = getWindow();
        WindowManager.LayoutParams wl = window.getAttributes();
        wl.width = x;
        wl.gravity = gravity;
        wl.y = top;
        window.setAttributes(wl);
    }

    public void showFromBottom() {
        if(!isSetup) {
            isSetup = true;
            setAnimation(R.style.Dialog_Animation_FromBottom);
            setLayoutParam(WindowManager.LayoutParams.MATCH_PARENT,Gravity.BOTTOM);
        }
        show();
    }

    public void showFromTop() {
        if(!isSetup) {
            isSetup = true;
            setAnimation(R.style.Dialog_Animation_FromTop);
            setLayoutParam(WindowManager.LayoutParams.MATCH_PARENT,Gravity.TOP,getContext().getResources().getDimensionPixelOffset(R.dimen.title_bar_height));
        }
        show();
    }

}
