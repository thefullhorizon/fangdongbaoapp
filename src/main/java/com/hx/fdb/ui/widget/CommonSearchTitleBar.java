package com.hx.fdb.ui.widget;

import android.app.Activity;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hx.fdb.R;


/**
 * 公共的搜索头部 view
 * Created by yanxin on 2015/6/23.
 */
public class CommonSearchTitleBar extends LinearLayout implements OnClickListener {

    private EditText title_text;
    private View leftButton;
    private TextView titleRight;

    private SearchTitleBarListener titleBarListener;

    private Activity mActivity;

    public CommonSearchTitleBar(Context context) {
        super(context);
        init(context);
    }

    public CommonSearchTitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    @Override
    protected void onDetachedFromWindow() {
        mActivity = null;
        super.onDetachedFromWindow();
    }

    public void setActivity(Activity activity) {
        this.mActivity = activity;
    }

    private void init(Context context) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.common_search_title_bar, null);

        int height = getContext().getResources().getDimensionPixelSize(R.dimen.title_bar_height);

//        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            int statusBarHeight =  AppUtil.getStatusBarHeight(getContext());
//            height += statusBarHeight;
//            View statusBar = view.findViewById(R.id.statusBar);
//            statusBar.setVisibility(View.VISIBLE);
//            LayoutParams params = (LayoutParams) statusBar.getLayoutParams();
//            params.height = statusBarHeight;
//        }

        LayoutParams params = new LayoutParams(
                LayoutParams.MATCH_PARENT,
                height);
        addView(view, params);

        title_text = (EditText) view.findViewById(R.id.title_text);
        leftButton = view.findViewById(R.id.btn_back);
        titleRight = (TextView) view.findViewById(R.id.title_right);

        if (isInEditMode()) return;
        leftButton.setOnClickListener(this);
        titleRight.setOnClickListener(this);
        findViewById(R.id.clearBtn).setOnClickListener(this);
        title_text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(titleBarListener != null) {
                    titleBarListener.onTextChange(s.toString());
                }
            }
        });
    }

    public String getText() {
        return title_text.getText().toString();
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

        void rightOnClick();

        void onTextChange(String text);
    }

    public abstract static class SimpleTitleBarListener implements SearchTitleBarListener {

        public void leftOnClick() {

        }

        public void rightOnClick() {

        }

    }

    public void addTextWatcher(TextWatcher textWatcher) {
        title_text.addTextChangedListener(textWatcher);
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
        } else if (v.getId() == R.id.title_right) {
            if (titleBarListener != null) {
                titleBarListener.rightOnClick();
            }
        } else if(v.getId() == R.id.clearBtn) {
            title_text.setText("");
            if (titleBarListener != null) {
                titleBarListener.onTextChange("");
            }
        }
        /*else if (v.getId() == R.id.title_text) {
            if (titleBarListener != null) {
                titleBarListener.searchOnClick();
            }
        }*/
    }

}
