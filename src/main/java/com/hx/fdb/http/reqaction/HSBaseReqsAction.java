package com.hx.fdb.http.reqaction;

import android.content.Context;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.hx.fdb.http.HttpConfig;
import com.hx.fdb.http.RequestModel;
import com.huoqiu.framework.app.AppConfig;
import com.huoqiu.framework.commhttp.BaseReqAction;
import com.huoqiu.framework.commhttp.CommonObjFileReq;
import com.huoqiu.framework.commhttp.CommonObjReq;
import com.huoqiu.framework.commhttp.CommonReq;
import com.huoqiu.framework.commhttp.CommonStrReq;
import com.huoqiu.framework.commhttp.ImageObjReq;
import com.huoqiu.framework.commhttp.MCListenerObj;
import com.huoqiu.framework.encrypt.Encrypt;
import com.huoqiu.framework.util.DateUtil;
import com.huoqiu.framework.util.DeviceUtil;
import com.huoqiu.framework.util.LogUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by shejian on 2015/4/20.
 */
public class HSBaseReqsAction extends BaseReqAction {
    /*------------------------------------------------------------------------------*/

    public static <T> void getGetReq7HeardInfo(Context mContext, String url, RequestModel requestModel, MCListenerObj.IObjResListener<T> iStrResListener, Class<T> responseClass) {
        HashMap<String, Object> paramsMap;
        if(requestModel == null) paramsMap = new HashMap<>();
        else paramsMap = requestModel.getParams();
        CommonObjReq commonObjReq = getCommObjReq(Request.Method.GET, url, paramsMap, iStrResListener, responseClass, getHeardInfo(paramsMap));
        createReqToQue(mContext, commonObjReq);
    }

    public static <T> void getPostReq7HeardInfo(Context mContext, String url, RequestModel requestModel, MCListenerObj.IObjResListener<T> iStrResListener, Class<T> responseClass) {
        HashMap<String, Object> paramsMap;
        if(requestModel == null) paramsMap = new HashMap<>();
        else paramsMap = requestModel.getParams();
        CommonObjReq commonObjReq = getCommObjReq(Request.Method.POST, url, paramsMap, iStrResListener, responseClass, getHeardInfo(paramsMap));
        createReqToQue(mContext, commonObjReq);
    }

    public static void getPostReqString(Context mContext, String url,RequestModel requestModel, MCListenerObj.IObjResListener<String> iStrResListener) {
        HashMap<String, Object> paramsMap;
        if(requestModel == null) paramsMap = new HashMap<>();
        else paramsMap = requestModel.getParams();
        CommonReq commonReq = getCommReq(mContext, Request.Method.POST, url, paramsMap, iStrResListener, getHeardInfo(paramsMap));
        commonReq.setRetryPolicy(new DefaultRetryPolicy(60000, 0, 1f));
        createReqToQue(mContext, commonReq);
    }


    public static <T> void getImagePostReq7HeardInfo(Context mContext, String url, RequestModel requestModel, MCListenerObj.IObjResListener<T> iStrResListener, Class<T> responseClass) {
        HashMap<String, Object> paramsMap;
        if(requestModel == null) paramsMap = new HashMap<>();
        else paramsMap = requestModel.getParams();
        ImageObjReq imageObjReq = getImageObjReq(Request.Method.POST, url, paramsMap, iStrResListener, responseClass, getHeardInfo(paramsMap));
        createReqToQue(mContext, imageObjReq);
    }

    public static <T> void getGetFileReq7HeardInfo(Context mContext, String url, MCListenerObj.IObjResListener<T> iStrResListener, HashMap<String, String> params) {
        CommonObjFileReq commonObjReq = getFileReq(Request.Method.GET, url, iStrResListener, params);
        createReqToQue(mContext, commonObjReq);
    }

    public static HashMap<String, String> getHeardInfo(Map<String, Object> paramsMap) {
        Map<String, Object> map = paramsMap;
        List<Map.Entry<String, Object>> params = new ArrayList<>(map.entrySet());//
        Collections.sort(params, new Comparator<Map.Entry<String, Object>>() {
            public int compare(Map.Entry<String, Object> o1, Map.Entry<String, Object> o2) {
                if (o1.getKey() == null || o2.getKey() == null)
                    return 0;

                return o1.getKey().compareTo(o2.getKey());
            }
        });

        String secret = "";
        for (Map.Entry<String, Object> param : params) {
            String key = param.getKey();
            Object value = param.getValue();
            secret = secret + key + "=" + value + "&";
        }
        if (secret.endsWith("&"))
            secret = secret.substring(0, secret.length() - 1);

        long time = DateUtil.getCurrentTime().getTimeInMillis();
        String timestamp = String.valueOf((time / 100000));
        LogUtil.i(HttpConfig.TAG, "secret:" + secret + " timestamp:" + timestamp);
        String md5 = Encrypt.decryptKey(secret, timestamp, HttpConfig.packageName);
        HashMap<String, String> headers = new HashMap<>();
        headers.put(appKeyLabel, AppConfig.get_appkey());
        headers.put(appSecretLabel, md5);
        headers.put(appTime, time + "");
        headers.put(appVersion, AppConfig.versionName);
        headers.put(appOS, "android");
        headers.put(appIMEI, DeviceUtil.getIMEI(AppConfig.application));
        headers.put(appModel, DeviceUtil.getBrandModel());
        headers.put(userId, AppConfig.agentid.toString());
        headers.put(u_ticket, AppConfig.u_ticket);
        headers.put(distance, AppConfig.distance);
        headers.put(cityId, AppConfig.cityId);
        headers.put("Connection", "Closed");

        return headers;
    }

    /**
     * 报文头需要的字段
     */
    private final static String appTime = "App_Time";// 当前时间
    private final static String appVersion = "ver"; // 手机端版本号
    private final static String appOS = "os"; // 手机来源 (android/ios)
    private final static String appIMEI = "imei"; // 手机唯一标志
    private final static String appModel = "model"; // 手机型号
    private final static String userId = "user_id"; // 已登录用户ID
    private final static String u_ticket = "u_ticket";// 已登录用户的Cookie
    private final static String appKeyLabel = "App-Key"; // key
    private final static String appSecretLabel = "App-Secret"; // secret
    private final static String distance = "distance";
    private final static String cityId = "cityId";

    public static CommonReq getCommReq(Context mContext, int method, String urlStr, Map<String, Object> paramsMap, MCListenerObj.IObjResListener<String> iStrResListener, final HashMap<String, String> headerInfo) {

        MCListenerObj mcListenerObj = new MCListenerObj(iStrResListener, urlStr);

        CommonReq commonObjReq = new CommonStrReq(method, urlStr, paramsMap,mcListenerObj,headerInfo);

        return commonObjReq;

    }
}