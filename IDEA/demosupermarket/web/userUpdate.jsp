<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <script type="text/javascript" src="js/jquery.js" ></script>
    <title>Insert title here</title>
    <link type="text/css" rel="stylesheet" href="css/style.css">
    <script type="text/javascript">
        $(function(){
            var rs1=true,rs2=false,rs3=true,rs4=false,rs5=true,rs6=true;
            $("[name='username']").blur(function(){
                var reg=/^[\u4e00-\u9fa5A-Za-z0-9-_]{3,9}/;
                var uname=$("[name='username']").val();
                if(uname==""){
                    $("[name='username']").next().html("请输入用户名");
                    rs1=false;
                }else{
                    if (reg.test(uname)) {
                        $("[name='username']").next().html("");
                        rs1 = true;
                    } else {
                        $("[name='username']").next().html("只能中英文，数字，下划线，减号(3-9)位");
                        rs1=false;
                    }
                }
            });


            $("[name='age']").blur(function(){
                var reg=/^[1-7][0-9]{0,1}$/;
                var age=$("[name='age']").val();
                if(age==""){
                    $("[name='age']").next().html("请输入年龄");
                    rs3=false;
                }else{
                    if (reg.test(age)) {
                        $("[name='age']").next().html("");
                        rs3 = true;
                    } else {
                        $("[name='age']").next().html("80岁以下用户可以注册");
                        rs3=false;
                    }
                }
            });



            $("[name='password']").blur(function(){
                var num=$("[name='password']").val();
                var reg=/\w{3,9}/;
                if(num==""){
                    $("[name='password']").next().html("请输入密码");
                    rs2=false;
                }else{
                    if (reg.test(num)) {
                        $("[name='password']").next().html("");
                        rs2 = true;
                    } else {
                        $("[name='password']").next().html("只能中英文，数字，下划线(3-9)位");
                        rs2=false;
                    }
                }
            });
            $("[name='password1']").blur(function(){
                var num=$("[name='password']").val();
                var num1=$("[name='password1']").val();
                if(num1==""){
                    $("[name='password1']").next().html("请输入密码");
                    rs4=false;
                }else{
                    if (num==num1) {
                        $("[name='password1']").next().html("");
                        rs4 = true;
                    } else {
                        $("[name='password1']").next().html("密码重写错误，请确认密码！");
                        rs4=false;
                    }
                }
            });
            $("[name='mobile']").blur(function(){
                var num=$("[name='mobile']").val();
                var reg=/^[1-9][0-9]{10}$/;
                if(num==""){
                    $("[name='mobile']").next().html("请输入电话");
                    rs5=false;
                }else{
                    if (reg.test(num)) {
                        $("[name='mobile']").next().html("");
                        rs5 = true;
                    } else {
                        $("[name='mobile']").next().html("电话格式不对");
                        rs5=false;
                    }
                }
            });
            $("[name='address']").blur(function(){
                var num=$("[name='address']").val();
                var reg=/\w{3,9}/;
                if(num==""){
                    $("[name='address']").next().html("请输入地址");
                    rs6=false;
                }else{
                    if (reg.test(num)) {
                        $("[name='address']").next().html("");
                        rs6 = true;
                    } else {
                        $("[name='address']").next().html("只能中英文，数字，下划线(3-9)位");
                        rs6=false;
                    }
                }
            });


            $("#form").submit(function(){
                //验证重复工作号
                //验证备注
                if(rs1==true&&rs2==true&&rs3==true&&rs4==true&&rs5==true&&rs6==true){
                    return true;
                }else{
                    alert("表单有误,请检查数据");
                    return false;
                }
            });
        })
        function goto() {
            location.href="/user?method=selectalluser";
        }
    </script>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
</head>
<body>
<div class="main">
    <div class="optitle clearfix">
        <div class="title">用户管理-修改数据&gt;&gt;</div>

    </div>
    <form id="form" name="form" method="post" action="/user">
        <input type="hidden" name="method" value="updateuser">
        <input type="hidden" name="uid" value="${uid}">
        <div class="content">
            <table class="box"><font color="red"></font>


                <tr>
                    <td class="field">用户名称：</td>
                    <td><input type="text" name="username" class="text" id="textfield2" value="${user.userName}"/> <font color="red"></font></td>
                </tr>
                <tr>
                    <td class="field">用户密码：</td>

                    <td><input type="password" name="password" class="text" id="textfield2" /> <font color="red"></font></td>
                </tr>
                <tr>
                    <td class="field">确认密码：</td>
                    <td><input type="password" name="password1" class="text"/> <font color="red"></font></td>
                </tr>

                <tr>
                    <td class="field">用户性别：</td>
                    <td><select name="sex" id="select" >
                        <option value="0" ${user.userSex=="女"?"selected":""}>女</option>
                        <option value="1" ${user.userSex=="男"?"selected":""}>男</option>
                    </select></td>
                </tr>

                <tr>
                    <td class="field">用户年龄：</td>
                    <td><input type="text" name="age" class="text" id="textfield2" value="${user.userAge}"/> <font color="red"></font></td>
                </tr>
                <tr>
                    <td class="field">用户电话：</td>
                    <td><input type="text" name="mobile" class="text" id="textfield2" value="${user.userPhone}"/> <font color="red"></font></td>

                </tr>
                <tr>
                    <td class="field">用户地址：</td>
                    <td><textarea name="address" id="textarea" class="text" cols="45" rows="5">${user.userAddress}</textarea><font color="red"></font></td>
                </tr>

            </table>
        </div>
        <div class="buttons">
            <input type="submit" name="button"  value="修改" class="input-button"/>
            <input type="button" name="button"  onclick="goto()" value="返回" class="input-button"/>
        </div>

    </form>
</div>
</body>
</html>
