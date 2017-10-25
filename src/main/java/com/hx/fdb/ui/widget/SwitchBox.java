package com.hx.fdb.ui.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.hx.fdb.R;


/**
 * Created by yanxin on 2015/12/3.
 */
public class SwitchBox extends LinearLayout {

    private RadioGroup  radioGroup;
    private RadioButton leftRadio;
    private RadioButton rightRadio;
    private SelectBoxListener mSelectBoxListener;
    private ViewPager mViewPager;

    public SwitchBox(Context context) {
        super(context);
    }

    public SwitchBox(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public SwitchBox(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init(Context context,AttributeSet attrs) {
        View view = LayoutInflater.from(context).inflate(R.layout.selectbox_layout, null);
        radioGroup = (RadioGroup)view.findViewById(R.id.radioGroup);
        leftRadio = (RadioButton)view.findViewById(R.id.leftBtn);
        rightRadio = (RadioButton)view.findViewById(R.id.rightBtn);
        mViewPager = (ViewPager) view.findViewById(R.id.viewpager);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.SwitchBox);
        String leftTitle = a.getString(R.styleable.SwitchBox_selectbox_left_title);
        String rightTitle = a.getString(R.styleable.SwitchBox_selectbox_right_title);
        Drawable selectBg = null;
        if(a.hasValue(R.styleable.SwitchBox_selectbox_background)) {
            selectBg = a.getDrawable(R.styleable.SwitchBox_selectbox_background);
        }
        if(a.hasValue(R.styleable.SwitchBox_selectbox_textcolor)) {
            ColorStateList textColor = a.getColorStateList(R.styleable.SwitchBox_selectbox_textcolor);
            leftRadio.setTextColor(textColor);
            rightRadio.setTextColor(textColor);
        } else {
            leftRadio.setTextColor(getResources().getColorStateList(R.color.selectbox_textcolor_2081f8_999999));
            rightRadio.setTextColor(getResources().getColorStateList(R.color.selectbox_textcolor_2081f8_999999));
        }
        boolean pager = a.getBoolean(R.styleable.SwitchBox_selectbox_pager,false);
        a.recycle();

        leftRadio.setText(leftTitle);
        rightRadio.setText(rightTitle);
        if(selectBg != null) {
            leftRadio.setBackgroundDrawable(selectBg);
            rightRadio.setBackgroundDrawable(selectBg);
        }

        leftRadio.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    if(mViewPager.getVisibility() == VISIBLE) {
                        mViewPager.setCurrentItem(0);
                    }
                    if (mSelectBoxListener != null) mSelectBoxListener.selectLeft();
                }

            }
        });

        rightRadio.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    if(mViewPager.getVisibility() == VISIBLE) {
                        mViewPager.setCurrentItem(1);
                    }
                    if (mSelectBoxListener != null) mSelectBoxListener.selectRight();
                }
            }
        });

        if(pager) {
            mViewPager.setVisibility(VISIBLE);
            mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                    if(position == 0) checkLeft();
                    else checkRight();
                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });
        } else {
            mViewPager.setVisibility(GONE);
        }
        addView(view);
    }

    public void checkLeft() {
        if(!leftRadio.isChecked()) {
            radioGroup.check(R.id.leftBtn);
        }
    }

    public void checkRight() {
        if(!rightRadio.isChecked()) {
            radioGroup.check(R.id.rightBtn);
        }
    }

    public boolean isLeftChecked() {
        return leftRadio.isChecked();
    }

    public boolean isRightChecked() {
        return rightRadio.isChecked();
    }

    public void setAdapter(PagerAdapter adapter) {
        mViewPager.setAdapter(adapter);
    }

    public void setSelectBoxListner(SelectBoxListener selectBoxListner) {
        this.mSelectBoxListener = selectBoxListner;
    }

    public interface SelectBoxListener {
        void selectLeft();
        void selectRight();
    }

}
