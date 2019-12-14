package com.alibaba.controller;

import com.alibaba.bean.Hetong;
import com.alibaba.bean.User;
import com.alibaba.bean.Userlist;
import com.alibaba.bean.Zulist;
import com.alibaba.service.UserlistService;
import com.alibaba.service.ZulistService;
import com.alibaba.util.PageEnum;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/zulist")
public class ZulistController {
    @Autowired
    private ZulistService zulistService;

    //管理员查看所有在租列表
    @RequestMapping("/findzulist")
    public String findzulist(Model model, @RequestParam(required = false, defaultValue = "1") Integer page) throws Exception {
        PageHelper.startPage(page, PageEnum.PAGESIZE.getSize());
        List<Zulist> zulist = zulistService.findzuuserlist();
        PageInfo<Zulist> p = new PageInfo<Zulist>(zulist);
        model.addAttribute("p", p);
        model.addAttribute("zulist", zulist);
        model.addAttribute("mainPage", "zulist.jsp");
        return "jsp/admin/main1";
    }

    @Autowired
    private UserlistService userlistService;

    //跳到增添合同的页面
    @RequestMapping("/toaddhetong")
    public String toaddhetong(Model model, String house_id) {
        Hetong hetong = new Hetong();
        hetong.setHouse_id(house_id);
        model.addAttribute("hetong", hetong);
        model.addAttribute("mainPage", "addhetong.jsp");

        return "jsp/admin/main1";
    }

    //查看我的在租列表
    @RequestMapping("/myzulist")
    public String myzulist(Model model, HttpSession httpSession, @RequestParam(required = false, defaultValue = "1") Integer page) throws Exception {

        User user1 = (User) httpSession.getAttribute("user");
        Userlist userlist = userlistService.findhasuserlist(user1.getId());
        PageHelper.startPage(page, PageEnum.PAGESIZE.getSize());
        List<Userlist> list = userlistService.getUserzuList(userlist.getId());
        PageInfo<Userlist> p = new PageInfo<Userlist>(list);
        model.addAttribute("userlistzu", list);
        model.addAttribute("p", p);
        model.addAttribute("mainPage", "myzulist.jsp");

        return "jsp/zuke/main";
    }

}