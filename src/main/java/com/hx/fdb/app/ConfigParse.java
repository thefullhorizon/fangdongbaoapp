package com.hx.fdb.app;

import android.util.Log;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by yanxin on 17/4/21.
 */

public class ConfigParse {

    public static void parse() {
        loadConfig();
    }

    /**
     * 加载配置文件
     */
    private static void loadConfig() {
        Properties p = new Properties();
        try {
            InputStream in_orgin = HSApplication.getApplication().getAssets().open("config.properties");
            int count = in_orgin.available();
            byte[] buffer_new = new byte[count];
            in_orgin.read(buffer_new);
            InputStream in_new = new ByteArrayInputStream(buffer_new);
            p.load(in_new);
            in_orgin.close();
            in_new.close();

            HSConfig.setConfig(p);

        } catch (IOException e) {
            Log.e("PARSE", e.getMessage());
            System.exit(0);
        }

    }

}
