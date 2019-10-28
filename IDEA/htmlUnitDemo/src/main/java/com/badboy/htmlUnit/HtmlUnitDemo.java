package com.badboy.htmlUnit;

import com.badboy.enity.News;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import java.io.IOException;
import java.util.List;

public class HtmlUnitDemo {
    public static void main(String args[]) throws IOException {
        //使用htmlUnit进行浏览器模拟
        WebClient webClient = new WebClient(BrowserVersion.BEST_SUPPORTED);
        //设置浏览器JS可用
        webClient.getOptions().setJavaScriptEnabled(true);
        //设置浏览器SSL可用
        webClient.getOptions().setUseInsecureSSL(true);
        //设置浏览器CSS不可用
        webClient.getOptions().setCssEnabled(false);
        //屏蔽网页中的错误
        webClient.getOptions().setThrowExceptionOnScriptError(false);
        //设置连接超时（毫秒）
        webClient.getOptions().setTimeout(10000);
        //设置异步加载可用
        webClient.setAjaxController(new NicelyResynchronizingAjaxController());
        //
        List<String> list = PageUtil.getNewsUrl(webClient);
        for (int i = 0;i<list.size();i++) {
            System.out.println("the " + i + " is " + list.get(i));
        }
//            String url = list.get(3);
        String url =" https://news.163.com/19/0708/08/EJI2HQUT0001899N.html";
        News news = PageUtil.getNewsContentByUrl(webClient, url);
//            System.out.println(news.getUrl());
//            System.out.println(news.getTitle());
//            System.out.println(news.getKeyWords());
//            System.out.println(news.getTime());
//            System.out.println(news.getContent().trim());
        HtmlPage pagecomment =  webClient.getPage(news.getCommentUrl());
        webClient.waitForBackgroundJavaScript(10000);
        System.out.println(pagecomment.asXml());
        System.out.println(url);

        System.out.println(news.getComment());


    }
}
