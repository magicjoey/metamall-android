package com.metamall.Service;

import android.content.Context;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/3/12.
 */
public class FileService {
    private Context context;

    public FileService(Context context){
        this.context = context;
    }
    /**
     * 把用户名和密码保存到手机ROM
     * @param password 输入要保存的密码
     * @param accountNo 要保存的用户名
     * @param filename 保存到哪个文件
     * @return
     */
    public boolean saveToRom(String password,String accountNo,String filename) throws Exception{
        //以私有的方式打开一个文件
        FileOutputStream fos = context.openFileOutput(filename, Context.MODE_PRIVATE);
        String result = accountNo+":"+password;
        fos.write(result.getBytes());
        fos.flush();
        fos.close();
        return true;
    }
    public Map<String,String> getUserInfo(String filename) throws Exception{
        File file = new File("data/data/lq.wangzhen.file/files/"+filename);
        FileInputStream fis = new FileInputStream(file);
        //以上的两句代码也可以通过以下的代码实现：
        //FileInputStream fis = context.openFileInput(filename);
        byte[] data = StreamTools.getBytes(fis);
        String result = new String(data);
        String results[] = result.split(":");
        Map<String,String> map = new HashMap<String,String>();
        map.put("username", results[0]);
        map.put("password", results[1]);
        return map;
    }

}
