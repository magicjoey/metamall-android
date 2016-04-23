package com.metamall.client;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * <p>Restful客户端.</p>
 *
 * @author Magic Joey
 * @version RestfulClient.java 1.0 Created@2016-04-23 14:35 $
 */
public class RestfulClient {
    private static Logger logger = LoggerFactory.getLogger(RestfulClient.class);

    private static final String BASE_URL = "http://metamall.jximall.com/";

    public static JSONObject send(String body, ServiceTypeEnum serviceType) {

        JSONObject resultJson = null;
        try {

            URL restServiceURL = new URL(BASE_URL+serviceType.getUrl());

            HttpURLConnection httpConnection = (HttpURLConnection) restServiceURL.openConnection();
            httpConnection.setRequestMethod(serviceType.getMethod());
            httpConnection.setRequestProperty("Accept", "application/json");

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
            logger.error("Send2Server Error:",e);
        }
        return resultJson;
    }

    public static void main(String[] args) {
        String requestBody = "{'phoneNo':'15901845510','type':'register'}";
        JSONObject jsonObject = send(requestBody, ServiceTypeEnum.SMS);

        System.out.println(jsonObject);

    }




}
