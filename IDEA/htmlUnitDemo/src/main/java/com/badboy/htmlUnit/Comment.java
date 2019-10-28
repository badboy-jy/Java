package com.badboy.htmlUnit;

public class Comment {
    private String author;
    private String time;
    private String content;
    private String location;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
    public String toString(){

        return "评论： " + "\n" +
                "[作者:"+author+",时间:"+time+",评论内容:"+content+",地址:"+location+"]"+"\n";

    }
}
