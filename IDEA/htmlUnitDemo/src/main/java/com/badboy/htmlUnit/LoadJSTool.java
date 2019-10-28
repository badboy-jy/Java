package com.badboy.htmlUnit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class LoadJSTool {
    public static String getTotalContent(String url){
        Runtime rt = Runtime.getRuntime();
        Process process = null;
        try {
            process = rt.exec("E:\\phantomjs-2.1.1-windows\\phantomjs-2.1.1-windows\\phantomjs-2.1.1-windows\\bin\\phantomjs.exe E:\\parser.js "+url);
            //获取内容的输入流
            InputStream in = process.getInputStream();
            //字节流转换为字符流
            InputStreamReader reader = new InputStreamReader(in,"utf-8");
            //字符流转换为缓冲流
            BufferedReader br = new BufferedReader(reader);
            StringBuffer sbf = new StringBuffer();
            String tmp = "";
            while ((tmp = br.readLine())!= null){
                sbf.append(tmp);
            }
            return sbf.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
