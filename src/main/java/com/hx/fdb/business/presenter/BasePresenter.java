package com.hx.fdb.business.presenter;

import com.hx.fdb.business.MVP;
import com.hx.fdb.business.model.IModel;
import com.hx.fdb.business.view.IView;
import com.huoqiu.framework.commhttp.BaseReqAction;

/**
 * Created by yanxin on 17/3/22.
 */

public class BasePresenter<V extends IView, M extends IModel> implements IPresenter {

    protected V mView;
    protected M mModel;

    public BasePresenter() {
        Class<?> clz = getClass();
        if (clz.isAnnotationPresent(MVP.class)) {
            MVP mvp = clz.getAnnotation(MVP.class);
            Class<? extends IModel> mClz = mvp.M();
            try {
                mModel = (M) mClz.newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void setView(Object view) {
        if(view == null) return;

        if(view instanceof IView) {
            try {
                this.mView = (V) view;
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void destroy() {
        if(mView != null) {
            if(mView.getActivity() != null) {
                BaseReqAction.cancelAllRequest(mView.getActivity().getClass().getName());
            } else if(mView.getContext() != null) {
                BaseReqAction.cancelAllRequest(mView.getContext().getClass().getName());
            }
        }
        this.mView = null;
    }

}
