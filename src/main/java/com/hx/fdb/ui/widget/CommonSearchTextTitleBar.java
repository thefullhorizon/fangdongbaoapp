package com.hx.fdb.ui.widget;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hx.fdb.R;


/**
 * 公共的搜索头部 view
 * Created by yanxin on 2015/6/23.
 */
public class CommonSearchTextTitleBar extends LinearLayout implements OnClickListener {

    private View custom_title_panel;
    private View titleBar;
    private View statusBar;
    private TextView title_text;
    private ImageView leftButton;

    private SearchTitleBarListener titleBarListener;

    private Activity mActivity;

    public CommonSearchTextTitleBar(Context context) {
        super(context);
        init(context);
    }

    public CommonSearchTextTitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    @Override
    protected void onDetachedFromWindow() {
        mActivity = null;
        super.onDetachedFromWindow();
    }

    /**
     * 设置背景色值
     *
     * @param resource
     */
    public void setBackground(int resource) {
        custom_title_panel.setBackgroundResource(resource);
    }

    /**
     * 切换主题
     *
     * @param theme
     */
    public void setTheme(CommonSearchTextTitleBar.THEME theme) {
        if (theme.equals(CommonSearchTextTitleBar.THEME.THEME_WHITE)) {
            titleBar.setBackgroundResource(R.color.white);
            statusBar.setBackgroundResource(R.color.transparent);
            leftButton.setImageDrawable(getResources().getDrawable(R.mipmap.grey_arrow));
        } else if(theme.equals(CommonSearchTextTitleBar.THEME.THEME_BULUE)) {
            setBackground(R.color.title_bar_bg);
            leftButton.setImageDrawable(getResources().getDrawable(R.mipmap.arrow));
        }
    }

    public void setActivity(Activity activity) {
        this.mActivity = activity;
    }

    private void init(Context context) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.common_search_text_title_bar, null);
        statusBar = view.findViewById(R.id.statusBar);

        int height = getContext().getResources().getDimensionPixelSize(R.dimen.title_bar_height);

//        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            int statusBarHeight =  AppUtil.getStatusBarHeight(getContext());
//            height += statusBarHeight;
//            statusBar.setVisibility(View.VISIBLE);
//            LayoutParams params = (LayoutParams) statusBar.getLayoutParams();
//            params.height = statusBarHeight;
//        }

        LayoutParams params = new LayoutParams(
                LayoutParams.MATCH_PARENT,
                height);
        addView(view, params);

        custom_title_panel = view.findViewById(R.id.searchTitleBarPanel);
        title_text = (TextView) view.findViewById(R.id.title_text);
        leftButton = (ImageView) view.findViewById(R.id.btn_back);
        titleBar = findViewById(R.id.titleView);

        if (isInEditMode()) return;
        leftButton.setOnClickListener(this);
        findViewById(R.id.searchView).setOnClickListener(this);
    }

    public void setHintTitle(int left) {
        title_text.setHint(getResources().getString(left));
    }

    public void setHintTitle(CharSequence left) {
        title_text.setHint(left);
    }

    public void setTitle(int left) {
        title_text.setText(getResources().getString(left));
    }

    public void setTitle(CharSequence left) {
        title_text.setText(left);
    }

    public interface SearchTitleBarListener {
        void leftOnClick();

        void searchClick();
    }

    public abstract static class SimpleTitleBarListener implements SearchTitleBarListener {

        public void leftOnClick() {

        }
    }

    public void setBarOnClickListener(SearchTitleBarListener titleBarListener) {
        this.titleBarListener = titleBarListener;
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
        } else if (v.getId() == R.id.searchView) {
            if (titleBarListener != null) {
                titleBarListener.searchClick();
            }
        }
    }

    public enum THEME {
        THEME_BULUE(),
        THEME_WHITE();
    }

}
