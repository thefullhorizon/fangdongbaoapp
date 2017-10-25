package com.hx.fdb.ui.widget.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.hx.fdb.R;

/**
 * Created by yanxin on 17/4/9.
 */

public class AppDialog {

    private AlertDialog dialog;

    public void show() {
        if (dialog != null) dialog.show();
    }

    public void hide() {
        if(dialog!=null) dialog.hide();
    }

    public void dismiss() {
        if(dialog!=null && dialog.isShowing()) dialog.dismiss();
    }

    public void cancel() {
        if(dialog!=null) dialog.cancel();
    }

    public static class Builder {

        private Context context;

        private final int layoutRes = R.layout.dialog;

        private int anim;
        private DialogType dialogType;
        private String title = "提示";
        private String message;
        private int messageGrivaty = Gravity.CENTER;
        private String leftBtnText = "取消";
        private String rightBtnText = "确定";
        private View contView;
        private boolean showTitle;
        private boolean clickClose = true;//是否点击按钮自动关闭dialog
        private boolean cancel;
        private boolean outCancel;

        public View.OnClickListener leftClickListener;
        public View.OnClickListener rightClickListener;

        public Builder(@NonNull Context context) {
            this.context = context;
        }

        private AppDialog create() {
            AppDialog appDialog = new AppDialog();
            final AlertDialog dialog = new AlertDialog.Builder(context).show();
            appDialog.dialog = dialog;

            if(anim != 0) {
                Window window = dialog.getWindow(); //得到对话框
                window.setWindowAnimations(anim); //设置窗口弹出动画
                //window.setBackgroundDrawableResource(R.color.transparent); //设置对话框背景为透明
                //window.getDecorView().setPadding(0, 0, 0, 0); //消除边距
            }

            View view = LayoutInflater.from(context).inflate(layoutRes, null);
            View titleView = view.findViewById(R.id.titleView);
            FrameLayout messageView = (FrameLayout) view.findViewById(R.id.messageView);
            TextView messageTxt = (TextView) view.findViewById(R.id.messageTxt);
            if (showTitle) {
                ((TextView) view.findViewById(R.id.titleText)).setText(title);
                titleView.setVisibility(View.VISIBLE);
            } else {
                titleView.setVisibility(View.GONE);
            }

            if (!TextUtils.isEmpty(message)) {
                messageTxt.setText(message);
                messageTxt.setVisibility(View.VISIBLE);
                messageTxt.setGravity(messageGrivaty);
            } else if(contView != null) {
                messageTxt.setVisibility(View.GONE);
                if(contView.getParent() != null) {
                    ((ViewGroup)contView.getParent()).removeView(contView);
                }
                messageView.addView(contView);
            }

            Button leftBtn = (Button) view.findViewById(R.id.leftBtn);
            Button rightBtn = (Button) view.findViewById(R.id.rightBtn);
            View divide = view.findViewById(R.id.vlineDivide);

            if(!TextUtils.isEmpty(leftBtnText)) {
                leftBtn.setText(leftBtnText);
            }

            if(!TextUtils.isEmpty(rightBtnText)) {
                rightBtn.setText(rightBtnText);
            }

            leftBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (clickClose) {
                        dialog.dismiss();
                    }
                    if (leftClickListener != null) {
                        leftClickListener.onClick(v);
                    }
                }
            });

            rightBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (clickClose) {
                        dialog.dismiss();
                    }
                    if (rightClickListener != null) {
                        rightClickListener.onClick(v);
                    }
                }
            });

            if (dialogType.equals(DialogType.SINGLE)) {
                divide.setVisibility(View.GONE);
                rightBtn.setVisibility(View.GONE);
            } else if (dialogType.equals(DialogType.DOUBLE)) {
                divide.setVisibility(View.VISIBLE);
                leftBtn.setVisibility(View.VISIBLE);
                rightBtn.setVisibility(View.VISIBLE);
            }

            dialog.setCancelable(cancel);
            dialog.setCanceledOnTouchOutside(outCancel);

            dialog.setContentView(view);
            return appDialog;
        }

        public AppDialog show() {
            final AppDialog dialog = create();
            DialogCollection.getInstance().put(context.getClass(),dialog.dialog);
            dialog.show();
            return dialog;
        }

        public Builder setAnim(int anim) {
            this.anim = anim;
            return this;
        }

        public Builder setDialogType(DialogType dialogType) {
            this.dialogType = dialogType;
            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }

        public Builder setMessageGrivaty(int messageGrivaty) {
            this.messageGrivaty = messageGrivaty;
            return this;
        }

        public Builder setLeftBtnText(String leftText) {
            this.leftBtnText = leftText;
            return this;
        }

        public Builder setRightBtnText(String rightBtnText) {
            this.rightBtnText = rightBtnText;
            return this;
        }

        public Builder setShowTitle(boolean showTitle) {
            this.showTitle = showTitle;
            return this;
        }

        public Builder setContView(View contView) {
            this.contView = contView;
            return this;
        }

        /**
         * 点击弹出框中的按钮 自动关闭dialog
         * @param clickClose
         * @return
         */
        public Builder setClickClose(boolean clickClose) {
            this.clickClose = clickClose;
            return this;
        }

        public Builder setLeftClickListener(View.OnClickListener leftClickListener) {
            this.leftClickListener = leftClickListener;
            return this;
        }

        public Builder setRightClickListener(View.OnClickListener rightClickListener) {
            this.rightClickListener = rightClickListener;
            return this;
        }

        public Builder setCancelable(boolean cancel) {
            this.cancel = cancel;
            return this;
        }

        public Builder setCanceledOnTouchOutside(boolean outCancel) {
            this.outCancel = outCancel;
            return this;
        }
    }

    public enum DialogType {
        SINGLE,
        DOUBLE;
    }

}
