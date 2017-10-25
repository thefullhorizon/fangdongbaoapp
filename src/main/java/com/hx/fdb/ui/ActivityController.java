package com.hx.fdb.ui;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;

import com.hx.fdb.ui.activity.main.MainActivity;
import com.hx.fdb.ui.widget.intercept.Intercept;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yanxin on 17/4/17/4/33.
 */

public class ActivityController {

    private List<Intercept> intercepts = new ArrayList<>();

    private static ActivityController controller  = new ActivityController();

    private ActivityController() {

    }

    public void addIntercepts(Intercept intercept) {
        intercepts.add(intercept);
    }

    public static ActivityController getInstence() {
        return controller;
    }

    public void goToMainActivity(Activity activity) {
        Intent intent = new Intent(activity, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(activity,intent);
    }

    public void startActivity(Activity activity, Intent intent) {
        if(intercepts != null && intercepts.size() > 0) {
            boolean isIntercept;
            for(Intercept intercept:intercepts) {
                isIntercept = intercept.intercept(activity,intent);
                if(isIntercept) return;
            }
        }
        activity.startActivity(intent);
    }

    public void startActivity(Fragment fragment, Intent intent) {
        if(intercepts != null && intercepts.size() > 0) {
            boolean isIntercept;
            for(Intercept intercept:intercepts) {
                isIntercept = intercept.intercept(fragment,intent);
                if(isIntercept) return;
            }
        }
        fragment.startActivity(intent);
    }

    public void startActivityForResult(Activity activity, Intent intent,int code) {
        if(intercepts != null && intercepts.size() > 0) {
            boolean isIntercept;
            for(Intercept intercept:intercepts) {
                isIntercept = intercept.intercept(activity,intent);
                if(isIntercept) return;
            }
        }
        activity.startActivityForResult(intent,code);
    }

    public void startActivityForResult(Fragment fragment, Intent intent,int code) {
        if(intercepts != null && intercepts.size() > 0) {
            boolean isIntercept;
            for(Intercept intercept:intercepts) {
                isIntercept = intercept.intercept(fragment,intent);
                if(isIntercept) return;
            }
        }
        fragment.startActivityForResult(intent,code);
    }

}
