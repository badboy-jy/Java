package com.zhangjingyi.servlet;

import com.zhangjingyi.bean.RealEstate;
import com.zhangjingyi.bean.User;
import com.zhangjingyi.service.QueryService;
import com.zhangjingyi.service.impl.QueryServiceImpl;
import com.zhangjingyi.utils.DataUtils;
import com.zhangjingyi.utils.PageUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet(name = "QueryServlet", urlPatterns = "/query")
public class QueryServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        //1.接受参数
        String method = request.getParameter("method");
        if (method == null || ("").equals(method)) {
            method = "findall";
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
    }

    protected void findall(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String cardId = request.getParameter("cardId");
        int type=0;
        if (name == null || "".equals(name)) {
            name = "";
            type=0;
        }
        if (cardId == null || "".equals(cardId)) {
            cardId = "";
            type=1;
        }
        //存放参数值 给前端使用
        request.setAttribute("uname", name);
        request.setAttribute("cardId", cardId);
        request.setAttribute("type",type);



        request.setAttribute("size", DataUtils.PAGESIZE);
        String index = request.getParameter("index");//接受页码值
        int pageindex = 1;
        if (index != null) {
            pageindex = Integer.parseInt(index);
        }
        //调取service方法
        //查找存放 findall  结果list到booklist  给前端
        QueryService service = new QueryServiceImpl();
        List<RealEstate> list = service.findall(name, cardId, pageindex, DataUtils.PAGESIZE);
        //查询总条数，通过总条数算出总页数
        int totalcount = service.totalcount(name, cardId);
        PageUtils<RealEstate> page = new PageUtils<>();
        page.setPageIndex(pageindex);
        page.setTotalCount(totalcount);
        page.setDataList(list);

        request.setAttribute("p1", page);
        //3.跳转页面
        request.getRequestDispatcher("/information.jsp").forward(request, response);

    }

    protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接收参数
        String id = request.getParameter("id");
        //2.调取service方法
        QueryService service = new QueryServiceImpl();
        int i = service.deletebyid(Integer.parseInt(id));
        //3.跳转页面
        //加弹窗 显示操作成功与否
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        if (i > 0) {
            out.print("<script>alert('删除成功');location.href='/query?method=findall'</script>");
        } else {
            out.print("<script>alert('删除失败');location.href='/query?method=findall'</script>");
        }
    }

    protected void findbyid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        //2.调取service方法
        //查找存放 findbyid  结果eBookEntry到entry  给前端
        QueryService service = new QueryServiceImpl();
        RealEstate realEstate = service.findbyid(Integer.parseInt(id));
        request.setAttribute("realEstate", realEstate);
        request.setAttribute("id", id);
        //3.跳转页面
        request.getRequestDispatcher("/update.jsp").forward(request, response);
    }

    public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.接受参数
        String id = request.getParameter("id");
        String projectName = request.getParameter("projectName");
        String cardId = request.getParameter("cardId");
        String houseType = request.getParameter("houseType");
        String area = request.getParameter("area");
        String buildTime = request.getParameter("buildTime");
        String address = request.getParameter("address");
        //2.调取service方法
        QueryService queryService = new QueryServiceImpl();
        RealEstate realEstate = new RealEstate();
        realEstate.setId(Integer.parseInt(id));
        realEstate.setCardId(cardId);
        realEstate.setProjectName(projectName);
        realEstate.setHouseType(houseType);
        realEstate.setArea(Integer.parseInt(area));
        realEstate.setAddress(address);
        try {
            Date date = null;
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            if (!"".equals(buildTime)) {
                date = format.parse(buildTime);
            }
            realEstate.setBuildTime(date);
        } catch (Exception e) {
            e.printStackTrace();
        }

        int i = queryService.update(realEstate);
        //3.跳转页面
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        if (i > 0) {
            out.print("<script>alert('修改成功');location.href='/query?method=findall'</script>");
        } else {
            response.sendRedirect("/query?method=findbyid&id=" + id);
        }

    }
    protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String projectName = request.getParameter("projectName");
        String houseType = request.getParameter("houseType");
        String area = request.getParameter("area");
        String buildTime = request.getParameter("buildTime");
        String address = request.getParameter("address");
        User user = (User) request.getSession().getAttribute("usernow");
        //调取对象实现方法
        QueryService queryService = new QueryServiceImpl();
        RealEstate realEstate = new RealEstate();
        realEstate.setProjectName(projectName);
        realEstate.setHouseType(houseType);
        realEstate.setArea(Integer.parseInt(area));
        realEstate.setAddress(address);
        realEstate.setCardId(user.getCardId());
        try {
            Date date = null;
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            if (!"".equals(buildTime)) {
                date = format.parse(buildTime);
            }
            realEstate.setBuildTime(date);
        } catch (Exception e) {
            e.printStackTrace();
        }

        int i = queryService.add(realEstate);
        //跳转页面
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        if (i > 0) {
            out.print("<script>alert('添加成功');location.href='/query?method=findall'</script>");
        } else {
            response.sendRedirect("/add.jsp");
        }



    }

}
