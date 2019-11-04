package com.badboyjy.servlet;

import com.badboyjy.bean.Bill;
import com.badboyjy.bean.Supplier;
import com.badboyjy.bean.User;
import com.badboyjy.service.BillService;
import com.badboyjy.service.InService;
import com.badboyjy.service.SupplierService;
import com.badboyjy.service.impl.BillServiceImpl;
import com.badboyjy.service.impl.InServiceImpl;
import com.badboyjy.service.impl.SuppplierServiceImpl;
import com.badboyjy.utils.DataUtils;
import com.badboyjy.utils.PageUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet(name = "BillServlet", urlPatterns = "/bill")
public class BillServlet extends HttpServlet {
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

    protected void selectallbill(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String goodsName = request.getParameter("goodsName");
        String payStatus = request.getParameter("payStatus");
        if (goodsName == null || "".equals(goodsName)) {
            goodsName = "";
        }
        if (payStatus == null || "".equals(payStatus)) {
            payStatus = "";
        }

        //存放参数值 给前端使用
        request.setAttribute("goodsName", goodsName);
        request.setAttribute("payStatus", payStatus);
        request.setAttribute("size", DataUtils.PAGESIZE);

        String index = request.getParameter("index");//接受页码值
        int pageindex = 1;
        if (index != null) {
            pageindex = Integer.parseInt(index);
        }
        BillService service = new BillServiceImpl();
        List<Bill> list = service.findallbill(goodsName, payStatus, pageindex, DataUtils.PAGESIZE);

        //查询总条数，通过总条数算出总页数
        int totalcount = service.totalcount(goodsName, payStatus);
        PageUtils<Bill> page = new PageUtils<>();
        page.setPageIndex(pageindex);
        page.setTotalCount(totalcount);
        page.setDataList(list);
        request.setAttribute("p1", page);
        //3.跳转页面
        request.getRequestDispatcher("/billAdmin.jsp").forward(request, response);
    }

    protected void checksupplierId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String supplierId = request.getParameter("supplier_id");
        //2.数据库查询
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        BillService service = new BillServiceImpl();
        Supplier supplier = new Supplier();
        supplier.setSupplierId(Integer.parseInt(supplierId));
        int i = service.check(supplier);
        if (i <= 0) {
            //返回:不可用
            out.print("供应商不存在，请检查或注册供应商");
        } else {
            //可以用
            out.print("");
        }
    }

    protected void addbill(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String goodsName = request.getParameter("goodsName");
        String goodsCount = request.getParameter("goodsCount");
        String money = request.getParameter("money");
        String isPay = request.getParameter("isPay");
        String supplier_id = request.getParameter("supplier_id");
        String goodsDescription = request.getParameter("goodsDescription");
        String time = request.getParameter("time");
        //调取对象实现方法
        BillService service = new BillServiceImpl();
        Bill bill = new Bill();

        bill.setGoodsName(goodsName);
        bill.setGoodsCount(Integer.parseInt(goodsCount));
        bill.setMoney(Integer.parseInt(money));
        bill.setPay(Integer.parseInt(isPay));
        bill.setSupplierId(Integer.parseInt(supplier_id));
        bill.setGoodsDescription(goodsDescription);
        Date date = new Date();
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            date = format.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        bill.setTime(date);
        int i = service.addbill(bill);
        //跳转页面
        if (i > 0) {
            response.sendRedirect("/bill?method=selectallbill");
        } else {
            response.sendRedirect("/providerAdd.jsp");
        } //调取service方法
    }

    protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接收参数
        String billId = request.getParameter("billId");
        //2.调取service方法
        BillService service = new BillServiceImpl();
        int i = service.deletebyid(Integer.parseInt(billId));
        //3.跳转页面
        //加弹窗 显示操作成功与否
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        if (i > 0) {
            out.print("<script>alert('删除成功');location.href='/bill?method=selectallbill'</script>");
        } else {
            out.print("<script>alert('删除失败');location.href='/bill?method=selectallbill'</script>");
        }
    }

    protected void findbyid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String billId = request.getParameter("bid");
        //2.调取service方法
        BillService service = new BillServiceImpl();
        Bill bill = service.findbyid(Integer.parseInt(billId));
        request.setAttribute("bill", bill);
        request.setAttribute("bid", billId);
        //3.跳转页面
        request.getRequestDispatcher("/billUpdate.jsp").forward(request, response);
    }

    public void updatebill(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.接受参数
        String bid = request.getParameter("bid");
        String goodsName = request.getParameter("goodsName");
        String goodsCount = request.getParameter("goodsCount");
        String money = request.getParameter("money");
        String isPay = request.getParameter("isPay");
        String supplier_id = request.getParameter("supplier_id");
        String goodsDescription = request.getParameter("goodsDescription");
        String time = request.getParameter("time");
        //2.调取service方法
        BillService service = new BillServiceImpl();
        Bill bill = new Bill();
        bill.setBillId(Integer.parseInt(bid));
        bill.setGoodsName(goodsName);
        bill.setGoodsCount(Integer.parseInt(goodsCount));
        bill.setMoney(Integer.parseInt(money));
        bill.setPay(Integer.parseInt(isPay));
        bill.setSupplierId(Integer.parseInt(supplier_id));
        bill.setGoodsDescription(goodsDescription);
        Date date = new Date();
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            date = format.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        bill.setTime(date);
        int i = service.updatebill(bill);
        //3.跳转页面
        if (i > 0) {
            response.sendRedirect("/bill?method=selectallbill");
        } else {
            response.sendRedirect("/bill?method=findbyid&bid=" + bid);
        }
    }
}
