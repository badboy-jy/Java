package com.badboyjy.servlet;

import com.badboyjy.bean.Supplier;
import com.badboyjy.bean.User;
import com.badboyjy.service.SupplierService;
import com.badboyjy.service.UserService;
import com.badboyjy.service.impl.SuppplierServiceImpl;
import com.badboyjy.service.impl.UserServiceImpl;
import com.badboyjy.utils.DataUtils;
import com.badboyjy.utils.PageUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.List;

@WebServlet(name = "SupplierServlet", urlPatterns = "/supplier")
public class SupplierServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        //1.接受参数
        String method = request.getParameter("method");

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

    protected void selectallsupplier(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String supplierName = request.getParameter("supplierName");
        String supplierDesc = request.getParameter("supplierDesc");
        if (supplierName == null || "".equals(supplierName)) {
            supplierName = "";
        }
        if (supplierDesc == null || "".equals(supplierDesc)) {
            supplierDesc = "";
        }

        //存放参数值 给前端使用
        request.setAttribute("supplierName", supplierName);
        request.setAttribute("supplierDesc", supplierDesc);
        request.setAttribute("size", DataUtils.PAGESIZE);

        String index = request.getParameter("index");//接受页码值
        int pageindex = 1;
        if (index != null) {
            pageindex = Integer.parseInt(index);
        }
        //调取service方法
        //查找存放 findall  结果list到booklist  给前端
        SupplierService service = new SuppplierServiceImpl();
        List<Supplier> list = service.findallsupplier(supplierName, supplierDesc, pageindex, DataUtils.PAGESIZE);

        //查询总条数，通过总条数算出总页数
        int totalcount = service.totalcount(supplierName, supplierDesc);
        PageUtils<Supplier> page = new PageUtils<>();
        page.setPageIndex(pageindex);
        page.setTotalCount(totalcount);
        page.setDataList(list);
        request.setAttribute("p1", page);
        //3.跳转页面
        request.getRequestDispatcher("/providerAdmin.jsp").forward(request, response);
    }

    protected void addsupplier(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String supplierName = request.getParameter("supplierName");
        String supplierDesc = request.getParameter("supplierDesc");
        String supplierPerName = request.getParameter("supplierPerName");
        String supplierPhone = request.getParameter("supplierPhone");
        String supplierChuanZhen = request.getParameter("supplierChuanZhen");
        String supplierAddress = request.getParameter("supplierAddress");
        //调取对象实现方法
        SupplierService service = new SuppplierServiceImpl();
        Supplier supplier = new Supplier();

        supplier.setSupplierName(supplierName);
        supplier.setSupplierDesc(supplierDesc);
        supplier.setSupplierPerName(supplierPerName);
        supplier.setSupplierPhone(supplierPhone);
        supplier.setSupplierChuanZhen(supplierChuanZhen);
        supplier.setSupplierAddress(supplierAddress);
        int i = service.addsupplier(supplier);
        //跳转页面
        if (i > 0) {
            response.sendRedirect("/supplier?method=selectallsupplier");
        } else {
            response.sendRedirect("/providerAdd.jsp");
        } //调取service方法
    }

    protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接收参数
        String supplierId = request.getParameter("supplierId");
        //2.调取service方法
        SupplierService service = new SuppplierServiceImpl();
        int i = service.deletebyid(Integer.parseInt(supplierId));
        //3.跳转页面
        //加弹窗 显示操作成功与否
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        if (i > 0) {
            out.print("<script>alert('删除成功');location.href='/supplier?method=selectallsupplier'</script>");
        } else {
            out.print("<script>alert('删除失败');location.href='/supplier?method=selectallsupplier'</script>");
        }
    }

    protected void findbyid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String supplierId = request.getParameter("supplierId");
        //2.调取service方法
        //查找存放 findbyid  结果eBookEntry到entry  给前端
        SupplierService service = new SuppplierServiceImpl();
        Supplier supplier = service.findbyid(Integer.parseInt(supplierId));
        request.setAttribute("supplier", supplier);
        request.setAttribute("sid", supplierId);
        //3.跳转页面
        request.getRequestDispatcher("/supplierUpdate.jsp").forward(request, response);
    }

    public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.接受参数
        String sid = request.getParameter("sid");
        String supplierName = request.getParameter("supplierName");
        String supplierDesc = request.getParameter("supplierDesc");
        String supplierPerName = request.getParameter("supplierPerName");
        String supplierPhone = request.getParameter("supplierPhone");
        String supplierChuanZhen = request.getParameter("supplierChuanZhen");
        String supplierAddress = request.getParameter("supplierAddress");
        //2.调取service方法
        SupplierService service = new SuppplierServiceImpl();
        Supplier supplier = new Supplier();
        supplier.setSupplierId(Integer.parseInt(sid));
        supplier.setSupplierName(supplierName);
        supplier.setSupplierDesc(supplierDesc);
        supplier.setSupplierPerName(supplierPerName);
        supplier.setSupplierPhone(supplierPhone);
        supplier.setSupplierChuanZhen(supplierChuanZhen);
        supplier.setSupplierAddress(supplierAddress);

        int i = service.update(supplier);
        //3.跳转页面
        if (i > 0) {
            response.sendRedirect("/supplier?method=selectallsupplier");
        } else {
            response.sendRedirect("/supplier?method=findbyid&sid=" + sid);
        }
    }

}
