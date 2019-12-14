package com.alibaba.controller;


import com.alibaba.bean.Checkout;
import com.alibaba.bean.User;
import com.alibaba.bean.Userlist;
import com.alibaba.service.CheckoutService;
import com.alibaba.service.UserlistService;
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
@RequestMapping("/checkout")
public class CheckoutController {
    @Autowired
    private CheckoutService checkoutService;
    @Autowired
    private UserlistService userlistService;

    @RequestMapping("/getallcheckout")
    public String getallcheckout(Model model, @RequestParam(required = false, defaultValue = "1") Integer page) {
        PageHelper.startPage(page, PageEnum.PAGESIZE.getSize());
        List<Checkout> checkout = checkoutService.getallcheckout();
        PageInfo<Checkout> p = new PageInfo<Checkout>(checkout);
        model.addAttribute("p", p);
        model.addAttribute("checkout", checkout);
        model.addAttribute("mainPage", "checkout.jsp");
        return "jsp/admin/main1";
    }

    //租客删除自己已退租列表
    @RequestMapping("/admindeletecheckout")
    public String admindeletecheckout(Integer id) {
        checkoutService.deletecheckout(id);
        return "redirect:/checkout/getallcheckout";
    }

    @RequestMapping("/getmycheckout")
    public String getmycheckout(Model model, HttpSession httpSession, @RequestParam(required = false, defaultValue = "1") Integer page) {
        User user1 = (User) httpSession.getAttribute("user");
        Userlist userlist = userlistService.findhasuserlist(user1.getId());
        PageHelper.startPage(page, PageEnum.PAGESIZE.getSize());
        List<Userlist> list = userlistService.getmycheckout(userlist.getId());
        PageInfo<Userlist> p = new PageInfo<Userlist>(list);
        model.addAttribute("p", p);
        model.addAttribute("userlistcheck", list);
        model.addAttribute("mainPage", "mycheckout.jsp");
        return "jsp/zuke/main";
    }

    //租客删除自己已退租列表
    @RequestMapping("/deletecheckout")
    public String deletecheckout(Integer id) {
        checkoutService.deletecheckout(id);
        return "redirect:/checkout/getmycheckout";
    }
}