package com.badboyjy.servlet;

import com.badboyjy.bean.AirQualityIndex;
import com.badboyjy.bean.District;
import com.badboyjy.service.AirQualityIndexService;
import com.badboyjy.service.DistrictService;
import com.badboyjy.service.impl.AirQualityIndexServiceImpl;
import com.badboyjy.service.impl.DistrictServiceImpl;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet(name = "EntryServlet",urlPatterns = "/entry")
public class EntryServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        //1.接受参数
        String method = request.getParameter("method");
        if (method == null || ("").equals(method)) {
            method = "selectall";
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

    protected void selectall(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获得参数
        String districtId = request.getParameter("districtId");
        int id = 0;
        if (districtId == null||"".equals(districtId)) {
            id =0;
        }else{
            id = Integer.parseInt(districtId);
        }
        //存放参数值 给前端使用
        request.setAttribute("districtId", districtId);

        String index = request.getParameter("index");//接受页码值
        int pageindex = 1;
        if (index != null) {
            pageindex = Integer.parseInt(index);
        }
        //调取service方法
        //查找存放 findall  结果list到booklist  给前端
        AirQualityIndexService service = new AirQualityIndexServiceImpl();
        List<AirQualityIndex> list = service.findall(id, pageindex, DataUtils.PAGESIZE);
        //查找存放 types  结果list1到types  给前端
        DistrictService type = new DistrictServiceImpl();
        List<District> list1 = type.finddistrict();
        request.setAttribute("district", list1);

        //查询总条数，通过总条数算出总页数
        int totalcount = service.totalcount(id);
        PageUtils<AirQualityIndex> page = new PageUtils<>();
        page.setPageIndex(pageindex);
        page.setTotalCount(totalcount);
        page.setDataList(list);
        request.setAttribute("p1", page);
        //3.跳转页面
        request.getRequestDispatcher("/show.jsp").forward(request, response);
    }

    protected void getdistrict(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //调取service方法
        //查找存放types  结构list到types  给前端
        DistrictService service = new DistrictServiceImpl();
        List<District> list = service.finddistrict();
        request.setAttribute("district", list);
        //跳转页面
        request.getRequestDispatcher("/add.jsp").forward(request, response);
    }

    protected void insertairquality(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String district = request.getParameter("district");
        String monitorTime = request.getParameter("monitorTime");
        String pm10 = request.getParameter("pm10");
        String pm2_5 = request.getParameter("pm2.5");
        String monitoringStatioin = request.getParameter("monitoringStatioin");
        //调取对象实现方法
        AirQualityIndexService airQualityIndexService = new AirQualityIndexServiceImpl();
        AirQualityIndex airQualityIndex = new AirQualityIndex();
        airQualityIndex.setDistrictId(Integer.parseInt(district));
        Integer Pm10=null;
        Integer Pm2_5=null;
        if (!"".equals(pm10)){
            Pm10=Integer.parseInt(pm10);
        }
        if (!"".equals(pm2_5)){
            Pm2_5=Integer.parseInt(pm2_5);
        }
        airQualityIndex.setPm10(Pm10);
        airQualityIndex.setPm2_5(Pm2_5);
        airQualityIndex.setMonitoringStation(monitoringStatioin);

        Date date= null;
        try {
            date = null;
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            if (!"".equals(monitorTime)){
                date = format.parse(monitorTime);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        airQualityIndex.setMonitorTime(date);
        airQualityIndex.setLastMondifyTime(date);
        int i = airQualityIndexService.addAirQuality(airQualityIndex);
        //跳转页面
        if (i > 0) {
            response.sendRedirect("/entry");
        } else {
            response.sendRedirect("/add.jsp");
        } //调取service方法
    }
    protected void findbyid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String did = request.getParameter("did");
        //2.调取service方法
        //查找存放 findbyid  结果eBookEntry到entry  给前端
        AirQualityIndexService service = new AirQualityIndexServiceImpl();
        AirQualityIndex airQualityIndex = service.findbyid(Integer.parseInt(did));
        request.setAttribute("airQualityIndex", airQualityIndex);
        //查找存放 types  结果list1到types  给前端
        DistrictService type = new DistrictServiceImpl();
        List<District> list1 = type.finddistrict();
        request.setAttribute("district", list1);
        //3.跳转页面
        request.getRequestDispatcher("/update.jsp").forward(request, response);
    }
    public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.接受参数
        String id = request.getParameter("id");
        String district = request.getParameter("district");
        String lastMondifyTime = request.getParameter("lastMondifyTime");
        String pm10 = request.getParameter("pm10");
        String pm2_5 = request.getParameter("pm2.5");
        String monitoringStation = request.getParameter("monitoringStation");
        //2.调取service方法
        Integer Pm10=null;
        Integer Pm2_5=null;
        if (!"".equals(pm10)){
            Pm10=Integer.parseInt(pm10);
        }
        if (!"".equals(pm2_5)){
            Pm2_5=Integer.parseInt(pm2_5);
        }
        AirQualityIndexService service= null;
        AirQualityIndex airQualityIndex = null;
        AirQualityIndex index=null;
        Date date= null;
        try {
            date=null;
            service = new AirQualityIndexServiceImpl();
            index = service.findbyid(Integer.parseInt(id));
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            if (!"".equals(lastMondifyTime)){
                date = format.parse(lastMondifyTime);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        airQualityIndex = new AirQualityIndex(Integer.parseInt(id),Integer.parseInt(district),index.getMonitorTime(),Pm10,Pm2_5,monitoringStation,date);
        int i = service.update(airQualityIndex);
        //3.跳转页面
        if(i>0){
            response.sendRedirect("/entry?method=selectall");
        }else{
            response.sendRedirect("/entry?method=findbyid&did="+id);
        }
    }
    public  void delete(HttpServletRequest request,HttpServletResponse response){
        //1.接受参数
        String id = request.getParameter("id");
        //2.调取service
        AirQualityIndexService service = new AirQualityIndexServiceImpl();
        int i = service.delete(Integer.parseInt(id));
        //3.跳转页面,先设置编码，再获取out
        try {
            response.setContentType("text/html;charset=utf-8");
            PrintWriter out = response.getWriter();
            if(i>0){
                out.write("<script>alert('删除成功');location.href='/entry?method=selectall'</script>");
            }else{
                out.write("<script>alert('删除失败');location.href='/entry?method=selectall'</script>");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
}
}