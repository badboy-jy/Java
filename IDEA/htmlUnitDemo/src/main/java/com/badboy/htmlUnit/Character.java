package com.badboy.htmlUnit;

import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Character {

    public  static void saveHtml(String name, HtmlPage htmlPage) {
        try {
            name =  name+".html";
            File dest = new File("D:/Html/" +name);
            OutputStream os = new FileOutputStream(dest, false);
            OutputStreamWriter osw = new OutputStreamWriter(os, "utf-8");
            osw.write(htmlPage.toString());
            osw.flush();

            osw.close();
            os.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String standardFileName(String mName){
        if(StringUtils.isNotBlank(mName)){
            mName = mName.replaceAll("[\\\\/:*?\"<>|]", "");
            if(StringUtils.isBlank(mName)){
                mName = "未命名";
            }
        };
        return mName;
    }

    public  static String getCommentsCode(String url){
        int startIndex = url.lastIndexOf('/');
        int endIndex = url.lastIndexOf('.');
        return url.substring(startIndex + 1, endIndex);
    }

    public static List<Comment> getCommentsContent(String commentsHtml){
        List<Comment> comments = new ArrayList<Comment>();
        String getcontent = Jsoup.parse(commentsHtml).select("pre").text();
        int length = getcontent.length();
        int beginIndex = getcontent.indexOf("(")+1;
        String strJsonData = getcontent.substring(beginIndex, length - 2);

        String str;
        HashSet<String> set = new HashSet<String>();
        JSONObject json = new JSONObject(strJsonData);

        JSONArray arrayIds = json.getJSONArray("commentIds");
        for (Object obj:arrayIds) {
            str = obj.toString();
            String[] ids = str.split(",");
            for (String s:ids) {
                set.add(s);
            }
        }


        JSONObject arrayments = json.getJSONObject("comments");
        JSONObject jobj;
        for (String s:set) {
            jobj = arrayments.getJSONObject(s);
            Comment comment = new Comment();

            Object o = jobj.get("content");
            if(o != null){
                comment.setContent(o.toString());
            }
            o = jobj.get("createTime");
            if(o != null){
                comment.setTime(o.toString());
            }

            o = jobj.get("anonymous");
            if(o != null){
                if(o.toString().equals("false")){
                    o = jobj.get("user");
                    JSONObject j = new JSONObject(o.toString());
                    comment.setAuthor(j.getString("nickname"));
                }else{
                    comment.setAuthor("anonymous");
                }
            }

            comments.add(comment);

        }
        return  comments;
    }

}
