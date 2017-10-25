package com.hx.fdb.ui.widget.select;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;

import com.hx.fdb.R;
import com.huoqiu.framework.util.DensityUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yanxin on 17/4/11.
 */

public class SelectBox extends LinearLayout {

    public static final int SINGLE = 1;
    public static final int MULTI = 2;

    private List<String> mData;
    private List<CheckBox> mTexts;
    private boolean[] isSelected;
    private int chooseMode;
    private int itemDivideheight;

    public SelectBox(Context context) {
        super(context);
        init(context);
    }

    public SelectBox(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public SelectBox(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        setOrientation(HORIZONTAL);
        itemDivideheight = DensityUtil.dipToPx(getContext(), 20);
    }

    /**
     * 设置单选多选
     *
     * @return
     * @SINGLE or @MULTI
     */
    public SelectBox setChooiceMode(int mode) {
        this.chooseMode = mode;
        if(mode != SINGLE && mode != MULTI) {
            throw new RuntimeException("选择模式错误");
        }
        return this;
    }

    /**
     * 设置数据
     *
     * @param mData
     * @return
     */
    public SelectBox setData(List<String> mData) {
        this.mData = mData;
        refresh();
        return this;
    }

    private void refresh() {
        if (mData == null || mData.size() == 0) {
            removeAllViews();
            return;
        }
        mTexts = new ArrayList<>();
        isSelected = new boolean[mData.size()];
        LinearLayout leftLayout = new LinearLayout(getContext());
        leftLayout.setOrientation(VERTICAL);
        LinearLayout.LayoutParams paramsLeft = new LinearLayout.LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT);
        paramsLeft.weight=1;
        addView(leftLayout, paramsLeft);

        LinearLayout rightLayout = new LinearLayout(getContext());
        rightLayout.setOrientation(VERTICAL);
        LinearLayout.LayoutParams paramsRight = new LinearLayout.LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT);
        paramsRight.leftMargin = DensityUtil.dipToPx(getContext(),23);
        paramsRight.weight=1;
        addView(rightLayout, paramsRight);
        for (int i = 0; i < mData.size(); i++) {
            CheckBox item = (CheckBox) LayoutInflater.from(getContext()).inflate(R.layout.select_box, null);
            item.setText(mData.get(i));
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LayoutParams.WRAP_CONTENT,
                    LayoutParams.MATCH_PARENT);
            if(i>=2) {
                params.topMargin = itemDivideheight;
            }
            if (i % 2 == 0) {
                leftLayout.addView(item,params);
            } else {
                rightLayout.addView(item,params);
            }
            item.setTag(i);
            mTexts.add(item);
            isSelected[i] = false;
            item.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    Integer index = (Integer) buttonView.getTag();
                    if(index == null) return;
                    if(isChecked) {
                        select(index);
                    } else {
                        unSelect(index);
                    }
                }
            });
        }
    }

    /**
     * 选中
     * @param index
     */
    public void select(int index) {
        isSelected[index] = true;
        if(chooseMode == SINGLE) {
            for(int i=0;i<isSelected.length;i++) {
                if(i!=index && isSelected[i]) {
                    isSelected[i] = false;
                    mTexts.get(i).setChecked(false);
                }
            }
        }
    }

    /**
     * 选中
     * @param index
     */
    public void unSelect(int index) {
        isSelected[index] = false;
    }

    public List<Integer> getSelect() {
        if(isSelected != null) {
            List<Integer> selected = new ArrayList<>();
            for(int i=0;i<isSelected.length;i++) {
                if(isSelected[i]) {
                    selected.add(i);
                }
            }
            return selected;
        }
        return null;
    }

}
