package com.badboyjy.servlet;

import com.badboyjy.bean.EBookCategory;
import com.badboyjy.bean.EBookEntry;
import com.badboyjy.service.EBookCategoryService;
import com.badboyjy.service.EBookEntryService;
import com.badboyjy.service.impl.EBookCategoryServiceImpl;
import com.badboyjy.service.impl.EBookEntryServiceImpl;
import com.badboyjy.utils.DataUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "EbookEntryServlet",urlPatterns = "/selectall")
public class EBookEntryServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //地址栏使用一个参数key
        //1.获得参数
       /* String query=null;
        String query1 = request.getParameter("query1");
        String query2 = request.getParameter("query2");
        //2.调取service方法
        if (query1==null && query2==null){
            query="";
            request.setAttribute("query1",query);
            request.setAttribute("query2",query);
        }else if (query1==null||query1.equals("")){
            query=query2;
            request.setAttribute("query1","");
            request.setAttribute("query2",query);
            if (query2.equals("全部")){
                query="";
            }
        }else if (query2==null||query2.equals("")){
            query=query1;
            request.setAttribute("query1",query);
            request.setAttribute("query2","");
            if (query1.equals("全部")||query1.equals("JY 帅")){
                query="";
            }
        }*/
       //地址栏使用两个参数
        //1.获得参数
        String query1 = request.getParameter("query1");
        String query2 = request.getParameter("query2");
        if (query1==null ||query1.equals("")){
            query1="";
        }
        if (query2==null||query2.equals("")){
            query2="";
        }
        //存放参数值 给前端使用
        request.setAttribute("query1",query1);
        request.setAttribute("query2",query2);

        String index = request.getParameter("index");//接受页码值
        int pageindex=1;
        if(index!=null){
            pageindex= Integer.parseInt(index);
        }
        //调取service方法
        //查找存放 findall  结果list到booklist  给前端
        EBookEntryService service=new EBookEntryServiceImpl();
        List<EBookEntry> list = service.findall(query1,query2,pageindex, DataUtils.PAGESIZE);
        request.setAttribute("booklist",list);
        //查找存放 types  结果list1到types  给前端
        EBookCategoryService type = new EBookCategoryServiceImpl();
        List<EBookCategory> list1 = type.findtypes();
        request.setAttribute("types",list1);


        //查询总条数，通过总条数算出总页数
        int totalcount = service.totalcount(query1,query2);
        //总页数
        int totalPage=totalcount%DataUtils.PAGESIZE>0?totalcount/DataUtils.PAGESIZE+1:totalcount/DataUtils.PAGESIZE;
        //将模糊查询的条件值重新保存
        request.setAttribute("pageindex",pageindex);
        request.setAttribute("count",totalcount);//总条数
        request.setAttribute("pages",totalPage);//总页数
        //3.跳转页面
        request.getRequestDispatcher("/show.jsp").forward(request,response);
    }
}
