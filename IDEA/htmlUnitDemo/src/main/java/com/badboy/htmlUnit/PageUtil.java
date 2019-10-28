package com.badboy.htmlUnit;

import com.badboy.enity.News;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlMeta;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

public class PageUtil {
    private static final String MaIN_URL = "https://news.163.com/domestic/";

    static List<String> getNewsUrl(WebClient webClient) throws IOException {
        HtmlPage page = webClient.getPage(MaIN_URL);
        webClient.waitForBackgroundJavaScript(10000);
        //通过Apath进行主要新闻链接的获取（超链接HTMLAnchor）
        List<Object> alist = page.getByXPath("//div[@class = 'ndi_main']/div/div/div/h3/a");
        List<String> newsUrl = new ArrayList<String>();
        for (Object object : alist) {
            //将object类型强制转换成超链接
            HtmlAnchor amark = (HtmlAnchor) object;
            String urlAdd = amark.getHrefAttribute();
            //将解析出来的网址放入集合
            newsUrl.add(urlAdd);
        }
        return newsUrl;
    }
    public static News getNewsContentByUrl(WebClient webClient, String url) throws IOException{
        News news = new News();
        news.setUrl(url);
        HtmlPage page = webClient.getPage(url);
        webClient.waitForBackgroundJavaScript(10000);
        String title = page.getTitleText();
        news.setTitle(title);
        //获取head中meta标签下的keywords属性的内容
        List<Object> listkey = page.getByXPath("//head/meta[@name='keywords']");
        if (!listkey.isEmpty()){
            HtmlMeta htmlMeta = (HtmlMeta) listkey.get(0);
            String keyWords = htmlMeta.getAttribute("content");
            news.setKeyWords(keyWords);
        }else {
            news.setKeyWords("no");
        }
        //获取head中meta标签下的article:published_time属性的内容
        List<Object> listtime = page.getByXPath("//head/meta[@property='article:published_time']");
        if (!listtime.isEmpty()){
            HtmlMeta htmlMeta = (HtmlMeta) listtime.get(0);
            String time = htmlMeta.getAttribute("content");
            news.setTime(time);
        }else {
            news.setTime("no");
        }
        //获取网址文本内容
        DomElement textElement = page.getElementById("endText");
        if (textElement != null){
            String content = textElement.getTextContent();
            news.setContent(content);
        }else {
            news.setContent("no");
        }
//获取评论区域的DIV（DomElement）(getById)
        DomElement commentElement = page.getElementById("post_comment_area");
        //进行超链接获取（getByPath）
        List<Object> listcomment = commentElement.getByXPath("//div[@class='post_comment_tiecount']/a");
        /*结果判断
           获取第一个值
           打印超链接
           webClient的getPage进行代码获取
          **/
        if (!listcomment.isEmpty()){
            HtmlAnchor htmlAnchor = (HtmlAnchor) listcomment.get(0);
            String href = htmlAnchor.getAttribute("href");
            // String href = htmlAnchor.getHrefAttribute();
            news.setCommentUrl(href);
            List<Comment> commentContent = getCommentContent(href);
            news.setComment(commentContent);


        }else {
        }
        return news;
    }
    public static List<Comment> getCommentContent(String commentUrl){
        List<Comment> content = new ArrayList<Comment>();
        if (commentUrl != null && commentUrl.length() > 0){
            Date d = new Date();
            Long t = d.getTime();
            String code = Character.getCommentsCode(commentUrl);
            String url1 = "http://comment.api.163.com/api/v1/products/a2869674571f77b5a0867c3d71db5856/threads/" +
                    code +
                    "/comments/hotList?ibc=newspc&limit=30&showLevelThreshold=72&headLimit=1&tailLimit=2&offset=0&callback=jsonp_" + t + "&_=" + (t+ 1);
            String commentsHtml = LoadJSTool.getTotalContent(url1);
            List<Comment> list1 = Character.getCommentsContent(commentsHtml);
            content.addAll(list1);

            String url2 = "http://comment.api.163.com/api/v1/products/a2869674571f77b5a0867c3d71db5856/threads/" +
                    code +
                    "/comments/hotList?ibc=newspc&limit=30&showLevelThreshold=72&headLimit=1&tailLimit=2&offset=0&callback=jsonp_" + (t+2) + "&_=" + (t+ 3);
            commentsHtml = LoadJSTool.getTotalContent(url2);
            List<Comment> list2 = Character.getCommentsContent(commentsHtml);
            content.addAll(list2);

            String url3 = "http://comment.api.163.com/api/v1/products/a2869674571f77b5a0867c3d71db5856/threads/" +
                    code +
                    "/comments/hotList?ibc=newspc&limit=30&showLevelThreshold=72&headLimit=1&tailLimit=2&offset=0&callback=jsonp_" + (t+4) + "&_=" + (t+ 5);
            commentsHtml = LoadJSTool.getTotalContent(url3);
            List<Comment> list3 = Character.getCommentsContent(commentsHtml);
            content.addAll(list3);
            return content;
        }else {
            return null;
        }
    }
}
