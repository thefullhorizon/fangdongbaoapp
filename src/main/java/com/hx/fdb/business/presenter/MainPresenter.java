package com.hx.fdb.business.presenter;

import com.hx.fdb.business.MVP;
import com.hx.fdb.business.model.SystemModel;
import com.hx.fdb.business.view.MainView;
import com.hx.fdb.http.HSHttpListener;
import com.huoqiu.framework.commhttp.Response;

/**
 * Created by yanxin on 17/10/25.
 */
@MVP(M= SystemModel.class)
public class MainPresenter extends BasePresenter<MainView,SystemModel> {

    public void requestSystemTime() {
        mModel.requestSystemTime(mView.getActivity(), new HSHttpListener<Response>() {
            @Override
            protected void onRequestFail(Exception exception, String url) {

            }

            @Override
            protected void onBusinessScuess(Response response) {

            }

            @Override
            protected void onBusinessFail(Response response) {

            }
        });
    }

}
