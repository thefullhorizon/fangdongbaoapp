package com.hx.fdb.ui.widget.dialog;

import android.app.Dialog;

import com.huoqiu.framework.util.LogUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yanxin on 17/4/13.
 */

public class DialogCollection {

    private final String TAG = "DIALOG";

    public static DialogCollection dialogCollection;

    private Map<String,List<Dialog>> dialogMap = new HashMap<>();
    private Map<String,Dialog> loadingMap = new HashMap<>();

    private DialogCollection() {

    }

    public static DialogCollection getInstance() {
        if(dialogCollection == null) {
            dialogCollection = new DialogCollection();
        }
        return dialogCollection;
    }

    public void put(Class mClass,Dialog dialog) {
        String key = mClass.getName();
        LogUtil.e(TAG,"add: "+key);
        List<Dialog> dialogs = dialogMap.get(key);
        if(dialogs == null) {
            dialogs = new ArrayList<>();
            dialogMap.put(key,dialogs);
        }
        dialogs.add(dialog);
    }

    public void putLoading(Class mClass,Dialog dialog) {
        String key = mClass.getName();
        LogUtil.e(TAG,"add: "+key);
        loadingMap.put(key,dialog);
    }

    public void remove(Class mClass) {
        String key = mClass.getName();
        LogUtil.e(TAG,"remove: "+key);
        List<Dialog> dialogs = dialogMap.remove(key);
        if(dialogs != null && dialogs.size() > 0) {
            for(Dialog dialog:dialogs) {
                dialog.cancel();
                LogUtil.e(TAG,"remove: ok");
            }
            dialogs.clear();
        }

        Dialog dialog = loadingMap.remove(key);
        if(dialog != null) {
            dialog.cancel();
        }
    }

    public void hideLoading() {
        for(Dialog dialog:loadingMap.values()) {
            if(dialog != null && dialog.isShowing()) {
                dialog.dismiss();
                LogUtil.e(TAG,"dismiss: ok");
            }
        }
    }

}
