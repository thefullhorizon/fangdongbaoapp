package com.hx.fdb.business.presenter;

import android.content.Intent;
import android.text.TextUtils;

import com.hx.fdb.bean.user.LoginResponse;
import com.hx.fdb.business.MVP;
import com.hx.fdb.business.model.UserModel;
import com.hx.fdb.business.view.LoginView;
import com.hx.fdb.http.HSHttpListener;
import com.hx.fdb.ui.ActivityController;
import com.hx.fdb.ui.activity.main.MainActivity;
import com.hx.fdb.util.UserInfoUtil;
import com.huoqiu.framework.util.ToastUtil;

/**
 * Created by yanxin on 17/10/25.
 */
@MVP(M = UserModel.class)
public class LoginPresenter extends BasePresenter<LoginView, UserModel> {

    public void mockLogin(String phone) {
        if (TextUtils.isEmpty(phone)) {
            ToastUtil.show(mView.getContext(), "请输入手机号码");
            return;
        }
        mView.showLoading();
        mModel.mockLogin(mView.getActivity(), phone, new HSHttpListener<LoginResponse>() {

            @Override
            protected void onBusinessScuess(LoginResponse response) {
                UserInfoUtil.instence().save(response.data);
                Intent intent = new Intent(mView.getActivity(), MainActivity.class);
                ActivityController.getInstence().startActivity(mView.getActivity(), intent);
            }

            @Override
            protected void onBusinessFail(LoginResponse response) {
                ToastUtil.show(mView.getContext(), response.getMessage());
            }

            @Override
            protected void onRequestFail(Exception exception, String url) {

            }

        });
    }

}
