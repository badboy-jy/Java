package com.alibaba.controller;

import com.alibaba.bean.Houselist;
import com.alibaba.service.HouselistService;
import com.alibaba.util.PageEnum;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HoustlistController {
    @Autowired
    private HouselistService houselistService;

    @RequestMapping("/ahouselist")
    public String ahouselist(Model model, @RequestParam(required = false, defaultValue = "1") Integer page) {
        PageHelper.startPage(page, PageEnum.PAGESIZE.getSize());
        List<Houselist> houselist = houselistService.selectAll();
        PageInfo<Houselist> p = new PageInfo<Houselist>(houselist);


        model.addAttribute("p", p);
        model.addAttribute("houselist", houselist);
        model.addAttribute("mainPage", "ahouselist.jsp");
        return "jsp/admin/main1";
    }

    @RequestMapping("/toaddhouse")
    public String toaddhoust(Model model) {
        model.addAttribute("mainPage", "addhouse.jsp");
        return "jsp/admin/main1";
    }

    @RequestMapping("/addhouse")
    public String addhouse(Model model, Houselist houselist) {
        String houseid = houselist.getHouseid();
        Houselist houselist1 = houselistService.findhouseid(houseid);
        if (houselist1 != null) {
            model.addAttribute("error", "该房屋id已存在");
            model.addAttribute("houselist", houselist);
            model.addAttribute("mainPage", "addhouse.jsp");
            return "jsp/admin/main1";
        } else {
            model.addAttribute("error", "添加成功");
            houselistService.inserthouse(houselist);
            model.addAttribute("houselist", houselist);
            model.addAttribute("mainPage", "addhouse.jsp");
            return "jsp/admin/main1";
        }
    }

    @RequestMapping("/deletehouse")
    public String deletehouse(Integer id) {
        houselistService.deletehouse(id);
        return "redirect:/ahouselist";
    }

    @RequestMapping("/findid")
    public String findid(Integer id, Model model) {
        Houselist list = houselistService.findid(id);
        model.addAttribute("houselist", list);
        model.addAttribute("mainPage", "changehouse.jsp");
        return "jsp/admin/main1";
    }

    @RequestMapping("/findhouseidupdate")
    public String findhouseidupdate(Houselist houselist, Model model) {
        System.out.println(houselist);
        Houselist list = houselistService.findhouseidupdate(houselist);
        if (list != null) {
            model.addAttribute("houselist", houselist);
            model.addAttribute("mainPage", "changehouse.jsp");
            model.addAttribute("error", "该房屋id已存在");
            return "jsp/admin/main1";
        } else {
            houselistService.updatehouse(houselist);
            model.addAttribute("houselist", houselist);
            model.addAttribute("mainPage", "changehouse.jsp");
            model.addAttribute("error", "更新成功");
            return "jsp/admin/main1";
        }
    }

    @RequestMapping("/houselist")
    public String houselist(Model model, @RequestParam(required = false, defaultValue = "1") Integer page) {

        PageHelper.startPage(page, PageEnum.PAGESIZE.getSize());
        List<Houselist> houselist = houselistService.selectAll();
        PageInfo<Houselist> p = new PageInfo<Houselist>(houselist);


        model.addAttribute("p", p);
        model.addAttribute("houselist", houselist);
        model.addAttribute("mainPage", "houselist.jsp");
        return "jsp/zuke/main";
    }

    @RequestMapping("/toahouselist")
    public String toahouselist() {
        return "/ahouselist";
    }

}
