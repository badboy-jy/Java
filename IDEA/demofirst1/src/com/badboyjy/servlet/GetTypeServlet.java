package com.badboyjy.servlet;

import com.badboyjy.bean.EBookCategory;
import com.badboyjy.service.EBookCategoryService;
import com.badboyjy.service.impl.EBookCategoryServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "GetTypeServlet",urlPatterns = "/gettypelist")
public class GetTypeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //调取service方法
        //查找存放types  结构list到types  给前端
        EBookCategoryService service = new EBookCategoryServiceImpl();
        List<EBookCategory> list = service.findtypes();
        request.setAttribute("types",list);
        //跳转页面
        request.getRequestDispatcher("/add.jsp").forward(request,response);
    }
}
