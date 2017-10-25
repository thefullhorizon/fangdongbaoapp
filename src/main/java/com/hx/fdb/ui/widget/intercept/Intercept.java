package com.hx.fdb.ui.widget.intercept;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;

/**
 * Created by yanxin on 17/4/3.
 */

public interface Intercept {

    boolean intercept(Activity activity,Intent intent);

    boolean intercept(Fragment fragment, Intent intent);

}
