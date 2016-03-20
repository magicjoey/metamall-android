package com.metamall.Service;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

/**
 * Created by Administrator on 2016/3/20.
 */
public class StreamTools {

    /**
     * 把InputStream中的内容读出来，放到一个byte[]中返回
     * @param is
     * @return
     * @throws Exception
     */
    public static byte[] getBytes(InputStream is) throws Exception{
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while((len=is.read(buffer)) != -1){
            baos.write(buffer, 0, len);
        }
        baos.flush();
        baos.close();
        return baos.toByteArray();
    }
}
