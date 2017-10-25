package com.hx.fdb.ui.activity.common;

import com.hx.fdb.business.MVP;
import com.hx.fdb.business.presenter.BasePresenter;
import com.hx.fdb.business.presenter.IPresenter;
import com.huoqiu.framework.util.LogUtil;

/**
 * Created by jaki on 17/3/22.
 */

public abstract class MvpActivity<P extends BasePresenter> extends BaseUiActivity {

    protected P mPresenter;

    protected MvpActivity() {
        Class<?> clz = getClass();
        if (clz.isAnnotationPresent(MVP.class)) {
            MVP mvp = clz.getAnnotation(MVP.class);
            Class<? extends IPresenter> pClz = mvp.P();
            try {
                mPresenter = (P) pClz.newInstance();
                mPresenter.setView(this);
            } catch (Exception e) {
                LogUtil.e("MVP","create persenter fail: "+e);
            }
        }
    }

    @Override
    protected void onDestroy() {
        if(mPresenter != null) {
            mPresenter.destroy();
        }
        super.onDestroy();
    }
}
