package com.matchmove.mmpay.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;
import com.matchmove.mmpay.api.ConnectionSession;
import com.matchmove.mmpay.api.MapUtil;
import com.matchmove.mmpay.api.OAuth;


public class Session implements ConnectionSession {

    private static String storagePath = "classpath:tmp/";
    private static Long expires = (long) 900;

    private static String getFilename(String key) {
        String filename = Session.storagePath + "jvsdk." + key + ".cache";
        return filename;
    }

    @Override
    public boolean set(String key, String value) {
        return this.set(key, value, Session.expires);
    }

    @Override
    public boolean set(String key, String value, long expires) {

        if (null != value) {
            expires = OAuth.timestamp() + expires;

            Map<String, String> data = new HashMap<String, String>();

            data.put("expires", new Long(expires).toString());
            data.put("value", value);
            value = MapUtil.mapToString(data);
        } else {
            value = "";
        }

        boolean result = true;
        FileWriter f = null;
        try {
            File file = new File(Session.getFilename(key));

            f = new FileWriter(file);
            f.write(value);
        } catch (Exception e) {
            result = false;
        } finally {
            try {
                if (null != f) {
                    f.close();
                }
            } catch (Exception e) {

            }
        }

        return result;
    }

    @Override
    public String get(String key) {

        String cursor;
        String value = "";
        BufferedReader buffer = null;
        try {

            File file = new File(Session.getFilename(key));

            if (!file.exists()) {
                return null;
            }

            buffer = new BufferedReader(new FileReader(Session.getFilename(key)));

            while ((cursor = buffer.readLine()) != null) {
                value = value + cursor;
            }
        } catch (Exception e) {
            value = null;
        } finally {
            try {
                if (null != buffer) {
                    buffer.close();
                }
            } catch (Exception e) {

            }
        }

        if ("" == value || null == value) {
            return null;
        }

        Map<String, String> data = MapUtil.stringToMap(value);

        long expires = Long.parseLong(data.get("expires"));

        return expires <= OAuth.timestamp() ? null: data.get("value");
    }
}