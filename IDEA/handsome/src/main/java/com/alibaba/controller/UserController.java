package com.alibaba.controller;

import com.alibaba.bean.User;
import com.alibaba.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    @Autowired
    private UserService userService;
    @RequestMapping("/logincheck")
    public String login(User user,Model model,HttpSession httpSession) throws Exception{

        User user1=userService.login(user);

        if(user1!=null){
            httpSession.setAttribute("user", user1);
            if(user1.getType().equals("zuke")){
                return "jsp/zuke/main";
            }
            else{
                return "jsp/admin/main1";
            }
        }else{
            String error="error";
            model.addAttribute("error", error);
            return "login";
        }
    }
    @RequestMapping("/login")
    public String userList() throws Exception{
        return "login";

    }


	@RequestMapping("/toindex")
	public String toindex(Model model) throws Exception{
		return "jsp/admin/index";
		}
	}

