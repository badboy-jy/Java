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

@WebServlet(name = "DeleteBookServlet",urlPatterns = "/deletebyid")
public class DeleteBookServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String tid = request.getParameter("tid");
        //2.调取service方法
        EBookEntryService service = new EBookEntryServiceImpl();
         int i = service.deletebyid(Integer.parseInt(tid));

        if (i>0){
            response.sendRedirect("/selectall");
        }else {
            response.sendRedirect("/deletebyid?tid");
        }

        //3.跳转页面

    }
}
