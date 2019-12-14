package com.alibaba.controller;

import com.alibaba.bean.*;
import com.alibaba.service.ApplyService;
import com.alibaba.service.HouselistService;
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
public class ApplyController {
    @Autowired
    private UserlistService userlistService;
    @Autowired
    private HouselistService houselistService;
    @Autowired
    private ApplyService applyService;


    //管理员查看申请看房列表
    @RequestMapping("/findapplylist")
    public String findapplylist(Model model,@RequestParam(required=false,defaultValue="1") Integer page) throws Exception{
        PageHelper.startPage(page, PageEnum.PAGESIZE.getSize());
        List<Apply> applylist=applyService.findapplylist();
        PageInfo<Apply> p=new PageInfo<Apply>(applylist);
        model.addAttribute("applylist",applylist);
        model.addAttribute("p", p);
        model.addAttribute("mainPage","applylist.jsp");
        return "jsp/admin/main1";
    }


    //管理员拒绝看房申请
    @RequestMapping("/refuseapply")
    public String refuseapply(String house_id,Model model){
        Houselist houselist=new Houselist();
        houselist.setHouseid(house_id);
        houselist.setStatus("未租赁");
        applyService.refuseapply(houselist);

        return "redirect:findapplylist";
    }

    //申请看房
    @RequestMapping("/applycheckuserlist")
    public String applycheckuserlist(HttpSession httpSession,Model model,Integer id){
        User user1= (User) httpSession.getAttribute("user");
        Integer user_id=user1.getId();
        Userlist list=userlistService.findhasuserlist(user_id);
        if(list==null){
            model.addAttribute("error", "applycheck");
            return "redirect:/houselist";
        }else{
            Houselist houselist=houselistService.findid(id);
            houselist.setStatus("已被申请");
            houselistService.updatehousestatus(houselist);
            Integer userlist_id=list.getId();
            Apply apply=new Apply();
            apply.setHouse_id(houselist.getHouseid());
            apply.setAddress(houselist.getAddress());
            apply.setPrice(houselist.getPrice());
            apply.setArea(houselist.getArea());
            apply.setStatus("申请中");
            apply.setUserlist_id(userlist_id);
            applyService.insertapply(apply);
            model.addAttribute("error", "applysuccess");
            return "redirect:/houselist";


        }

    }

    //租客查看自己的 看房申请
    @RequestMapping("/getmyapply")
    public String getmyapply(Model model,HttpSession httpSession,@RequestParam(required=false,defaultValue="1") Integer page){
        User user1= (User) httpSession.getAttribute("user");
        Userlist userlist=userlistService.findhasuserlist(user1.getId());
        PageHelper.startPage(page, PageEnum.PAGESIZE.getSize());
        List<Userlist> list=userlistService.getmyapply(userlist.getId());
        PageInfo<Userlist> p=new PageInfo<Userlist>(list);
        model.addAttribute("userlist", list);
        model.addAttribute("p", p);
        model.addAttribute("mainPage", "myapply.jsp");
        return "jsp/zuke/main";
    }

	@RequestMapping("/applychangehousestatus")
	public String applychangehousestatus(HttpSession httpSession,Model model,String house_id)throws Exception{
		User user1= (User) httpSession.getAttribute("user");
		Integer user_id=user1.getId();
		Userlist userlist=userlistService.findhasuserlist(user_id);
		Houselist houselist=houselistService.findhouseid(house_id);
		houselist.setStatus("已租赁");
		houselistService.updatehousestatus(houselist);
		Zulist zulist=new Zulist();
		zulist.setHouse_id(house_id);
		zulist.setPrice(houselist.getPrice());
		zulist.setAddress(houselist.getAddress());
		
		return "";
	}


	
}
