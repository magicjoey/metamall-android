package com.metamall.client;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>Restful客户端.</p>
 *
 * @author Magic Joey
 * @version RestfulClient.java 1.0 Created@2016-04-23 14:35 $
 */
public class RestfulClient {
    private static Logger logger = LoggerFactory.getLogger(RestfulClient.class);

    private static final String BASE_URL = "http://metamall.daoapp.io//";

    public static JSONObject send(Map<String,String> reqMap, ServiceTypeEnum serviceType) {

        JSONObject resultJson = null;
        try {

            URL restServiceURL = new URL(BASE_URL+serviceType.getUrl());

            HttpURLConnection httpConnection = (HttpURLConnection) restServiceURL.openConnection();
            httpConnection.setRequestMethod(serviceType.getMethod());

            OutputStream outStrm = httpConnection.getOutputStream();
            ObjectOutputStream objOutputStrm = new ObjectOutputStream(outStrm);
            objOutputStrm.writeObject(reqMap); // 这里发送数据
            objOutputStrm.flush();
            objOutputStrm.close();
//            httpConnection.setRequestProperty("Accept", "application/json");

            if (httpConnection.getResponseCode() != 200) {
                throw new RuntimeException("HTTP GET Request Failed with Error code : "
                        + httpConnection.getResponseCode());
            }

            BufferedReader responseBuffer = new BufferedReader(new InputStreamReader(
                    (httpConnection.getInputStream())));

            StringBuilder accum = new StringBuilder();
            String output;
            while ((output = responseBuffer.readLine()) != null) {
                accum.append(output);
            }
            httpConnection.disconnect();
            resultJson = JSONObject.parseObject(accum.toString());

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Send2Server Error:", e);
        }finally {

        }
        return resultJson;
    }

    public static void main(String[] args) {
        Map<String,String> reqMap = new HashMap<String,String>();
        reqMap.put("phoneNo","15901845510");
        reqMap.put("type","register");
//        String requestBody = "{'phoneNo':'15901845510','type':'register'}";
        JSONObject jsonObject = send(reqMap, ServiceTypeEnum.SMS);

        System.out.println(jsonObject);

    }




}
