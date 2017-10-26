package com.hx.fdb.ui.activity.user;

import android.content.Intent;

import com.hx.fdb.R;
import com.hx.fdb.business.MVP;
import com.hx.fdb.business.presenter.LoginPresenter;
import com.hx.fdb.business.view.LoginView;
import com.hx.fdb.ui.ActivityController;
import com.hx.fdb.ui.activity.common.MvpActivity;
import com.hx.fdb.ui.widget.CountdownView;
import com.hx.fdb.ui.widget.edittext.PhoneEditTextView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by yanxin on 17/10/25.
 */
@MVP(P= LoginPresenter.class)
public class LoginActivity extends MvpActivity<LoginPresenter> implements LoginView {

    @BindView(R.id.phoneTxt)
    public PhoneEditTextView phoneTxt;
    @BindView(R.id.codeBtn)
    public CountdownView codeView;

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {
        codeView.setCountDownListener(new CountdownView.CountDownListener() {
            @Override
            public boolean before() {
                return false;
            }

            @Override
            public void start() {

            }

            @Override
            public void finish() {

            }
        });
    }

    @OnClick(R.id.clearBtn)
    public void clickClearBtn() {
        phoneTxt.setText("");
    }

    @OnClick(R.id.loginBtn)
    public void clickLoginBtn() {
        mPresenter.mockLogin(phoneTxt.getText().toString());
        ActivityController.getInstence().startActivity(this,new Intent(this,IdentifyActivity.class));
    }
}
