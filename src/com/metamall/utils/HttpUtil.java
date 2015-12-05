package com.metamall.utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

/**
 * <p>Http工具类.</p>
 *
 * @author Magic Joey
 * @version HttpUtil.java 1.0 Created@2015-12-05 16:11 $
 */
public class HttpUtil {

    private HttpUtil(){
        //不允许构造
    }

    public static String post(RemoteServiceEnum remoteServiceEnum,Map<String,String> paramMap){
        String result = null;
        URL url = null;
        HttpURLConnection connection = null;
        InputStreamReader in = null;
        try {
            url = new URL(remoteServiceEnum.getRemoteUrl());
            connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("Charset", "utf-8");
            connection.setConnectTimeout(15000);
            DataOutputStream dop = new DataOutputStream(
                    connection.getOutputStream());
            String paramString = mapToString(paramMap);
            dop.writeBytes(paramString);
            dop.flush();
            dop.close();

            in = new InputStreamReader(connection.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(in);
            StringBuffer strBuffer = new StringBuffer();
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                strBuffer.append(line);
            }
            result = strBuffer.toString();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return result;

    }


    public static String mapToString(Map<String,String> dataMap){
        if(dataMap==null||dataMap.size()==0){
            return "";
        }
        StringBuilder accum = new StringBuilder();
        for(Map.Entry<String,String> entry : dataMap.entrySet()){
            accum.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
        }
        String result = accum.toString();
        if(result.length()>0){
            result = result.substring(0,result.length()-1);
        }
        return result;
    }


}
