package com.hx.fdb.ui.activity.common;

import com.hx.fdb.business.MVP;
import com.hx.fdb.business.presenter.BasePresenter;
import com.hx.fdb.business.presenter.IPresenter;

/**
 * Created by yanxin on 17/3/28.
 */

public abstract class MvpFragment<P extends BasePresenter> extends BaseUiFragment {

    protected P mPresenter;

    public MvpFragment() {
        Class<?> clz = getClass();
        if (clz.isAnnotationPresent(MVP.class)) {
            MVP mvp = clz.getAnnotation(MVP.class);
            Class<? extends IPresenter> pClz = mvp.P();
            try {
                mPresenter = (P) pClz.newInstance();
                mPresenter.setView(this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onDestroy() {
        if(mPresenter != null) {
            mPresenter.destroy();
        }
        super.onDestroy();
    }

}
