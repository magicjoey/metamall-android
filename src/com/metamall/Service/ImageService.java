package com.metamall.Service;


import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * Created by Administrator on 2016/4/12.
 */
public class ImageService {
    public static byte[] getImage(String path) throws Exception {
        URL url = new URL(path);
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setRequestMethod("GET");   //设置请求方法为GET
        conn.setReadTimeout(5*1000);    //设置请求过时时间为5秒
        InputStream inputStream = conn.getInputStream();   //通过输入流获得图片数据
        byte[] data = StreamTools.readInputStream(inputStream);     //获得图片的二进制数据
        return data;

    }
}
