package com.hx.fdb.ui.activity.user;

import android.view.View;
import android.widget.EditText;

import com.hx.fdb.R;
import com.hx.fdb.business.MVP;
import com.hx.fdb.business.presenter.LoginPresenter;
import com.hx.fdb.business.view.LoginView;
import com.hx.fdb.ui.activity.common.MvpActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by yanxin on 17/10/25.
 */
@MVP(P= LoginPresenter.class)
public class LoginActivity extends MvpActivity<LoginPresenter> implements LoginView {

    @BindView(R.id.phoneTxt)
    public EditText phoneTxt;

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {
        titleBar.setTitle("登录");
        titleBar.setLeftBtnVisible(View.GONE);
    }

    @OnClick(R.id.loginBtn)
    public void clickLoginBtn() {
        mPresenter.mockLogin(phoneTxt.getText().toString());
    }
}
