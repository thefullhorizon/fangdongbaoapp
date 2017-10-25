package com.hx.fdb.business;

import com.hx.fdb.business.model.IModel;
import com.hx.fdb.business.presenter.IPresenter;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by yanxin on 17/3/22.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface MVP {

    /**
     * Model, 只在Presenter使用,指明Presenter控制的Model
     * @return Model的实现类
     */
    Class<? extends IModel> M() default IModel.class;

//    Class<? extends ABaseView> V() default IABaseView.class;

    /**
     * Presenter, 只在Activity或者Fragment使用
     * @return Presenter的实现类
     */
    Class<? extends IPresenter> P() default IPresenter.class;

}
