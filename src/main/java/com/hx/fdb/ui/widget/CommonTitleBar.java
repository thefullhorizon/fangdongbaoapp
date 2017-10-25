package com.hx.fdb.ui.widget;

import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hx.fdb.R;


/**
 * 公共的头部 view
 * Created by yanxin on 2015/6/23.
 */
public class CommonTitleBar extends LinearLayout implements OnClickListener {

    private View custom_title_panel;
    private TextView title_text;
    private View leftButton;
    private TextView titleRight;
    private ImageView imgRight;

    private TitleBarListener titleBarListener;

    private Activity mActivity;

    public CommonTitleBar(Context context) {
        super(context);
        init(context);
    }

    public CommonTitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    @Override
    protected void onDetachedFromWindow() {
        mActivity = null;
        super.onDetachedFromWindow();
    }

    public CommonTitleBar setActivity(Activity activity) {
        this.mActivity = activity;
        return this;
    }

    private void init(Context context) {
        if (isInEditMode()) { return; }
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.common_title_bar, null);

        int height = getContext().getResources().getDimensionPixelSize(R.dimen.title_bar_height);

//        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//                //&& Build.VERSION.SDK_INT<=Build.VERSION_CODES.KITKAT_WATCH) {
//            int statusBarHeight =  AppUtil.getStatusBarHeight(getContext());
//            height += statusBarHeight;
//            View statusBar = view.findViewById(R.id.statusBar);
//            statusBar.setVisibility(View.VISIBLE);
//            LinearLayout.LayoutParams params = (LayoutParams) statusBar.getLayoutParams();
//            params.height = statusBarHeight;
//        }

        LayoutParams params = new LayoutParams(
                LayoutParams.MATCH_PARENT,
                height);
        addView(view, params);

        custom_title_panel = view.findViewById(R.id.titleBar);
        title_text = (TextView) view.findViewById(R.id.title_text);
        leftButton = view.findViewById(R.id.btn_back);
        titleRight = (TextView) view.findViewById(R.id.title_right);
        imgRight = (ImageView) view.findViewById(R.id.title_right_img);

        if (isInEditMode()) return;
        leftButton.setOnClickListener(this);
        titleRight.setOnClickListener(this);
        imgRight.setOnClickListener(this);
    }

    public CommonTitleBar setLeftBtnVisible(int visibility) {
        leftButton.setVisibility(visibility);
        return this;
    }

    public CommonTitleBar setRightBtnVisible(int visibility) {
        titleRight.setVisibility(visibility);
        return this;
    }

    public CommonTitleBar setRightImgVisible(int visibility) {
        imgRight.setVisibility(visibility);
        return this;
    }

    public CommonTitleBar setTitle(int text) {
        title_text.setText(getResources().getString(text));
        return this;
    }

    public CommonTitleBar setTitle(CharSequence text) {
        title_text.setText(text);
        return this;
    }

    public CommonTitleBar setRightText(int text) {
        titleRight.setText(getResources().getString(text));
        return this;
    }

    public CommonTitleBar setRightText(CharSequence text) {
        titleRight.setText(text);
        return this;
    }

    public CommonTitleBar setRightSrc(int resId) {
        imgRight.setImageBitmap(BitmapFactory.decodeResource(getResources(),resId));
        return this;
    }

    /**
     * 设置背景色值
     *
     * @param resource
     */
    private void setBackground(int resource) {
        custom_title_panel.setBackgroundResource(resource);
    }

    /**
     * 切换主题
     *
     * @param theme
     */
    public CommonTitleBar setTheme(THEME theme) {
        if (theme.equals(THEME.THEME_TRANS)) {
            setBackground(R.color.transparent);
        } else if(theme.equals(THEME.THEME_BULUE)) {
            setBackground(R.color.title_bar_bg);
        } else if(theme.equals(THEME.THEME_DACK)) {
            setBackground(R.color.color_FF252A30);
        }
        return this;
    }

    public interface TitleBarListener {
        public void leftOnClick();

        public void rightOnClick();
    }

    public abstract static class SimpleTitleBarListener implements TitleBarListener {
        public void leftOnClick() {
        }
    }

    public CommonTitleBar setTitleBarListener(TitleBarListener titleBarListener) {
        this.titleBarListener = titleBarListener;
        return this;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_back) {
            if (titleBarListener != null) {
                titleBarListener.leftOnClick();
            }
            if (mActivity != null) {
                mActivity.finish();
            }
        } else if (v.getId() == R.id.title_right || v.getId() == R.id.title_right_img ) {
            if (titleBarListener != null) {
                titleBarListener.rightOnClick();
            }
        }
    }

    public enum THEME {
        THEME_BULUE(),
        THEME_DACK(),
        THEME_TRANS();
    }

}
