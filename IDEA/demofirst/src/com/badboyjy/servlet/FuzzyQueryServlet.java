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
import java.util.List;

@WebServlet(name = "FuzzyQueryServlet",urlPatterns = "/fuzzyquery")
public class FuzzyQueryServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String query = request.getParameter("query");
        EBookEntryService service=new EBookEntryServiceImpl();
        List<EBookEntry> list = service.fuzzyquery(query);
        request.setAttribute("booklist",list);
        //3.跳转页面
        request.getRequestDispatcher("/fuzzyquery.jsp").forward(request,response);
    }
}
