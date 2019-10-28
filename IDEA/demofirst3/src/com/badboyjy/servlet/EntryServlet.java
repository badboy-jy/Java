package com.badboyjy.servlet;

import com.badboyjy.bean.EBookCategory;
import com.badboyjy.bean.EBookEntry;
import com.badboyjy.service.EBookCategoryService;
import com.badboyjy.service.EBookEntryService;
import com.badboyjy.service.impl.EBookCategoryServiceImpl;
import com.badboyjy.service.impl.EBookEntryServiceImpl;
import com.badboyjy.utils.DataUtils;
import com.badboyjy.utils.PageUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet(name = "EntryServlet", urlPatterns = "/entry")
public class EntryServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //编码格式统一
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        //1.接受参数
        String method = request.getParameter("method");
        if (method == null || ("").equals(method)){
            method="selectall";
        }
            try {
                //通过反射获取对象
                Class cla = this.getClass();
                //调取对象的方法
                Method method1 = cla.getDeclaredMethod(method, HttpServletRequest.class, HttpServletResponse.class);
                //执行方法
                method1.invoke(this, request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
//        //2调取方法
//        if("delete".equals(method)){
//            delete(request,response);
//        }else if("insertbook".equals(method)){
//            insert(request, response);
//        }else if("findbyid".equals(method)){
//            findbyid(request, response);
//        }else if("gettypes".equals(method)){
//            gettypes(request, response);
//        }else if("update".equals(method)){
//            update(request, response);
//        }else {
//            selectall(request,response);
//        }
    }

    protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接收参数
        String tid = request.getParameter("tid");
        //2.调取service方法
        EBookEntryService service = new EBookEntryServiceImpl();
        int i = service.deletebyid(Integer.parseInt(tid));
        //3.跳转页面
        //加弹窗 显示操作成功与否
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        if (i > 0) {
            out.print("<script>alert('删除成功');location.href='/entry'</script>");
        } else {
            out.print("<script>alert('删除失败');location.href='/entry'</script>");
        }
    }

    protected void insertbook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
			Date date = null;
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			if(!"".equals(time)){
             date = format.parse(time);
			 }
            eBookEntry.setCreatDate(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int i = eBookEntryService.addBook(eBookEntry);
        //跳转页面
        if (i > 0) {
            response.sendRedirect("/entry");
        } else {
            response.sendRedirect("/add.jsp");
        }

    }

    protected void findbyid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.接收参数
        String tid = request.getParameter("tid");
        //2.调取service方法
        //查找存放 findbyid  结果eBookEntry到entry  给前端
        EBookEntryService service = new EBookEntryServiceImpl();
        EBookEntry eBookEntry = service.findbyid(Integer.parseInt(tid));
        request.setAttribute("entry", eBookEntry);
        //查找存放 types  结果list1到types  给前端
        EBookCategoryService type = new EBookCategoryServiceImpl();
        List<EBookCategory> list1 = type.findtypes();
        request.setAttribute("types", list1);
        //3.跳转页面
        request.getRequestDispatcher("/update.jsp").forward(request, response);

    }

    protected void gettypes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //调取service方法
        //查找存放types  结构list到types  给前端
        EBookCategoryService service = new EBookCategoryServiceImpl();
        List<EBookCategory> list = service.findtypes();
        request.setAttribute("types", list);
        //跳转页面
        request.getRequestDispatcher("/add.jsp").forward(request, response);
    }

    protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接收参数
        String id = request.getParameter("id");
        String bookname = request.getParameter("bookname");
        String booktype = request.getParameter("booktype");
        String content = request.getParameter("content");
        String username = request.getParameter("username");
        String time = request.getParameter("time");
        //调取service方法
        EBookEntryService service = null;
        EBookEntry eBookEntry = null;
        try {
			service = new EBookEntryServiceImpl();
			Date date = null;
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			if(!"".equals(time)){
             date = format.parse(time);
			 }   
            eBookEntry = new EBookEntry(Integer.parseInt(id), Integer.parseInt(booktype), bookname, content, username, date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int i = service.updateBook(eBookEntry);
        //跳转页面
        if (i > 0) {
            response.sendRedirect("/entry");
        } else {
            response.sendRedirect("/entry?entry=findbyid&tid=" + id);
        }

    }

    protected void selectall(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获得参数
        String query1 = request.getParameter("query1");
        String query2 = request.getParameter("query2");
        if (query1 == null || query1.equals("")) {
            query1 = "";
        }
        if (query2 == null || query2.equals("")) {
            query2 = "";
        }
        //存放参数值 给前端使用
        request.setAttribute("query1", query1);
        request.setAttribute("query2", query2);

        String index = request.getParameter("index");//接受页码值
        int pageindex = 1;
        if (index != null) {
            pageindex = Integer.parseInt(index);
        }
        //调取service方法
        //查找存放 findall  结果list到booklist  给前端
        EBookEntryService service = new EBookEntryServiceImpl();
        List<EBookEntry> list = service.findall(query1, query2, pageindex, DataUtils.PAGESIZE);
        //查找存放 types  结果list1到types  给前端
        EBookCategoryService type = new EBookCategoryServiceImpl();
        List<EBookCategory> list1 = type.findtypes();
        request.setAttribute("types", list1);


        //查询总条数，通过总条数算出总页数
        int totalcount = service.totalcount(query1, query2);
        //总页数
        int totalPage = totalcount % DataUtils.PAGESIZE > 0 ? totalcount / DataUtils.PAGESIZE + 1 : totalcount / DataUtils.PAGESIZE;
        //将模糊查询的条件值重新保存
       /* request.setAttribute("pageindex", pageindex);
        request.setAttribute("count", totalcount);//总条数
        request.setAttribute("pages", totalPage);//总页数
        request.setAttribute("booklist", list);*/
        PageUtils<EBookEntry> page = new PageUtils<>();
        page.setPageIndex(pageindex);
        page.setTotalCount(totalcount);
        page.setDataList(list);
        request.setAttribute("p1",page);
        //3.跳转页面
        request.getRequestDispatcher("/show.jsp").forward(request, response);
    }

}
