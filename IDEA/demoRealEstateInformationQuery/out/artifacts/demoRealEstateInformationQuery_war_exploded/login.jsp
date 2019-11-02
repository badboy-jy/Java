<%--
  Created by IntelliJ IDEA.
  User: badboyjy
  Date: 2019/11/1
  Time: 9:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>房产信息查询系统</title>
    <script type="text/javascript" src="js/jquery.js" ></script>
    <script type="text/javascript">
        $(function(){
            var rs1=false,rs2=false;
            $("[name='cardId']").blur(function(){
                var reg=/^1[0-9]{17}/;
                var cardId=$("[name='cardId']").val();
                if(cardId==""){
                    $("[name='cardId']").next().html("请输入身份证号");
                    rs1=false;
                }else{
                    if (reg.test(cardId)) {
                        $("[name='cardId']").next().html("");
                        rs1 = true;
                    } else {
                        $("[name='cardId']").next().html("身份证号错误");
                        rs1=false;
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


            $("#form").submit(function(){
                //验证重复工作号
                //验证备注
                if(rs1==true&&rs2==true){
                    return true;
                }else{
                    alert("表单有误,请检查数据");
                    return false;
                }
            });
        })
    </script>
</head>
<body>
<h2>房产信息查询系统</h2>
<form method="post" action="/user" id="form">

    <dl>
        <dt>请输入身份证号：</dt>
        <dd><input type="text" name="cardId" /><font color="red"></font></dd>
        <dt>请输入密码：</dt>
        <dd><input type="password" name="password" /><font color="red"></font></dd>
    </dl>
        <input type="submit" name="submit" value="登录" />
        <a href="/regist.jsp">注册</a>
</form>
</body>
</html>
