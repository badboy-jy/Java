<%--
  Created by IntelliJ IDEA.
  User: badboyjy
  Date: 2019/11/1
  Time: 9:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>房产信息查询系统</title>
    <script type="text/javascript" src="js/jquery.js" ></script>
    <script type="text/javascript">
        $(function(){
            var rs1=false,rs2=false,rs3=false,rs4=false;
            $("[name='name']").blur(function(){
                var reg=/^[\u4e00-\u9fa5A-Za-z0-9-_]{3,9}/;
                var uname=$("[name='name']").val();
                if(uname==""){
                    $("[name='name']").next().html("请输入用户名");
                    rs1=false;
                }else{
                    if (reg.test(uname)) {
                        $("[name='name']").next().html("");
                        rs1 = true;
                    } else {
                        $("[name='name']").next().html("只能中英文，数字，下划线，减号(3-9)位");
                        rs1=false;
                    }
                }
            });

            $("[name='cardId']").blur(function(){
                var reg=/^1[0-9]{17,17}$/;
                var cardId=$("[name='cardId']").val();
                if(cardId==""){
                    $("[name='cardId']").next().html("请输入身份证号");
                    rs3=false;
                }else{
                    if (reg.test(cardId)) {

                       $.ajax({
                            url:"/user?method=checkcardId",
                            data:"cardId="+cardId,
                            type:"post",
                            dataType:"text",
                            success:function (rs) {

                                if (rs==""){
                                    $("[name='cardId']").next().html(rs);
                                    rs3 = true;
                                } else {
                                    $("[name='cardId']").next().html(rs);
                                    rs3 = false;
                                }
                            }
                        });

                    } else {
                        $("[name='cardId']").next().html("身份证号错误");
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




            $("#form").submit(function(){
                //验证重复工作号
                //验证备注
                if(rs1==true&&rs2==true&&rs3==true&&rs4==true){
                    return true;
                }else{
                    alert("表单有误,请检查数据");
                    return false;
                }
            });
        })

        function goto() {
            location.href="login.jsp";
        }
    </script>
</head>
<body>
<h2>账号注册</h2>
<form method="post" action="/user" id="form">
    <input type="hidden" name="method" value="regist">
    <dl>
        <dt>身份证号：</dt>
        <dd><input type="text" name="cardId" /><font color="red"></font> </dd>
        <dt>用户名：</dt>
        <dd><input type="text" name="name" /><font color="red"></font></dd>
        <dt>密码：</dt>
        <dd><input type="password" name="password" /><font color="red"></font></dd>
        <dt>确认密码：</dt>
        <dd><input type="password" name="password1" /><font color="red"></font></dd>
    </dl>
    <input type="submit" name="submit" value="注册" />
  <input type="button" value="返回" onclick="goto()">
</form>
</body>
</html>
