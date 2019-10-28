package com.badboyjy.servlet;

import com.badboyjy.bean.EBookEntry;
import com.badboyjy.service.impl.EBookEntryServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "AddBookServlet",urlPatterns = "/addbook")
public class AddBookServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //编码格式统一
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        //接收参数
        String bookname = request.getParameter("bookname");
        String booktype = request.getParameter("booktype");
        String content = request.getParameter("content");
        String username = request.getParameter("username");
        String time = request.getParameter("time");
        //调取对象实现方法
        EBookEntryServiceImpl eBookEntryService = new EBookEntryServiceImpl();
        EBookEntry eBookEntry = new EBookEntry();
        eBookEntry.setCategoryId(Integer.parseInt(booktype));
        eBookEntry.setTitle(bookname);
        eBookEntry.setSummary(content);
        eBookEntry.setUploadUser(username);
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date date = format.parse(time);
            eBookEntry.setCreatDate(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int i = eBookEntryService.addBook(eBookEntry);
        //跳转页面
        if (i>0){
            response.sendRedirect("/selectall");
        }else {
            response.sendRedirect("/add.jsp");
        }

    }
}
