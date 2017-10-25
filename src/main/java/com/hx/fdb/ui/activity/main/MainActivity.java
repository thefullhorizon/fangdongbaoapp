package com.hx.fdb.ui.activity.main;

import android.support.v4.app.FragmentTabHost;
import android.view.View;
import android.widget.TabHost;
import android.widget.TextView;

import com.hx.fdb.R;
import com.hx.fdb.business.MVP;
import com.hx.fdb.business.presenter.MainPresenter;
import com.hx.fdb.business.view.MainView;
import com.hx.fdb.ui.activity.common.MvpActivity;

import butterknife.BindView;

/**
 * Created by yanxin on 17/10/24.
 */
@MVP(P= MainPresenter.class)
public class MainActivity extends MvpActivity<MainPresenter> implements MainView{

    private final static String TAB1 = "tab_yun";
    private final static String TAB2 = "tab_contract";
    private final static String TAB3 = "tab_house";
    private final static String TAB4 = "tab_mine";

    @BindView(android.R.id.tabhost)
    public FragmentTabHost mTabHost;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        initTabView();
        mPresenter.requestSystemTime();
    }

    public void initTabView() {
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);
        mTabHost.getTabWidget().setDividerDrawable(null);
        mTabHost.setBackgroundResource(R.mipmap.card_shadow_up);

        mTabHost.addTab(createSpec(TAB1, getString(R.string.tab1), null), YunFragment.class,null);
        mTabHost.addTab(createSpec(TAB2, getString(R.string.tab2), null), ContactFragment.class, null);
        mTabHost.addTab(createSpec(TAB3, getString(R.string.tab3), null), HouseFragment.class, null);
        mTabHost.addTab(createSpec(TAB4, getString(R.string.tab4), null), MineFragment.class, null);

        mTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                int pos = 0;
                if (TAB1.equals(tabId)) {
                    pos = 0;
                } else if (TAB2.equals(tabId)) {
                    pos = 1;
                } else if (TAB3.equals(tabId)) {
                    pos = 2;
                } else if (TAB4.equals(tabId)) {
                    pos = 3;
                }
//                TextView textView = (TextView) mTabHost.getTabWidget().getChildAt(pos).findViewById(R.id.title_icon);
//                textView.setText(ICON_STRS[pos][1]);
//                textView = (TextView) mTabHost.getTabWidget().getChildAt(mLastTabPos).findViewById(R.id.title_icon);
//                textView.setText(ICON_STRS[mLastTabPos][0]);
//                mLastTabPos = pos;
            }
        });
    }

    /**
     * TabHost lable显示
     *
     * @param tag
     * @param label
     * @param label_icon
     * @return
     */
    private TabHost.TabSpec createSpec(String tag, String label, String label_icon) {
        View spec = View.inflate(this, R.layout.layout_index_tab_item, null);
        //TabHost Title
        TextView title = (TextView) spec.findViewById(R.id.title);
        title.setText(label);

        //待处理
//        TextView numberTextView = (TextView) spec.findViewById(R.id.tab_indicator_number);
//        if (label.equals(getString(R.string.tab_customer))) {
//            mCusomerIndicatorNumber = numberTextView;
//        } else if (label.equals(getString(R.string.tab_search_house))) {
//            mHousesourceIndicatorNumber = numberTextView;
//        } else if (label.equals(getString(R.string.tab_setup))) {
//            mMineIndicatorNumber = numberTextView;
//        }

        //TabHost IconFont
        //TextView title_icon = (TextView) spec.findViewById(R.id.title_icon);
        //title_icon.setText(label_icon);
        return mTabHost.newTabSpec(tag).setIndicator(spec);
    }

}
