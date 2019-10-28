package com.badboyjy.servlet;

import com.badboyjy.bean.EBookEntry;
import com.badboyjy.service.EBookEntryService;
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
import java.util.SimpleTimeZone;

@WebServlet(name = "UpdateBookServlet",urlPatterns = "/updatebook")
public class UpdateBookServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String id = request.getParameter("id");
        String bookname = request.getParameter("bookname");
        String content = request.getParameter("content");
        String username = request.getParameter("username");
        String time = request.getParameter("time");
        EBookEntryService service = null;
        EBookEntry eBookEntry = null;
        try {
            service = new EBookEntryServiceImpl();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date date = format.parse(time);
            eBookEntry = new EBookEntry(Integer.parseInt(id),null,bookname,content,username,date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int i = service.updateBook(eBookEntry);
        if (i>0){
            response.sendRedirect("/selectall");
        }else {
            response.sendRedirect("/findbyid?tid="+id);
        }

    }
}
