package com.badboyjy.servlet;

import com.badboyjy.bean.EBookCategory;
import com.badboyjy.bean.EBookEntry;
import com.badboyjy.service.EBookCategoryService;
import com.badboyjy.service.EBookEntryService;
import com.badboyjy.service.impl.EBookCategoryServiceImpl;
import com.badboyjy.service.impl.EBookEntryServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

//注解：给程序看的  注释：给程序员看的
@WebServlet(name = "FindByIdServlet", urlPatterns = "/findbyid")
public class FindByIdServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.接收参数
        String tid = request.getParameter("tid");
        //2.调取service方法
        //查找存放 findbyid  结果eBookEntry到entry  给前端
        EBookEntryService service = new EBookEntryServiceImpl();
        EBookEntry eBookEntry = service.findbyid(Integer.parseInt(tid));
        request.setAttribute("entry",eBookEntry);
        //查找存放 types  结果list1到types  给前端
        EBookCategoryService type = new EBookCategoryServiceImpl();
        List<EBookCategory> list1 = type.findtypes();
        request.setAttribute("types",list1);
        //3.跳转页面
        request.getRequestDispatcher("/update.jsp").forward(request,response);

    }
}
