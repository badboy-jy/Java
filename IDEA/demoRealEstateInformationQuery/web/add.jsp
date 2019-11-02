<%--
  Created by IntelliJ IDEA.
  User: badboyjy
  Date: 2019/11/1
  Time: 14:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>房产信息查询系统</title>
    <script type="text/javascript" src="js/jquery.js" ></script>
    <script type="text/javascript">
        $(function(){
            var rs1=false,rs2=false,rs3=false,rs4=false,rs5=false;
            $("[name='projectName']").blur(function(){
                var reg=/^[\u4e00-\u9fa5A-Za-z0-9-_]{3,9}/;
                var uname=$("[name='projectName']").val();
                if(uname==""){
                    $("[name='projectName']").next().html("请输入项目名");
                    rs1=false;
                }else{
                    if (reg.test(uname)) {
                        $("[name='projectName']").next().html("");
                        rs1 = true;
                    } else {
                        $("[name='projectName']").next().html("只能中英文，数字，下划线，减号(3-9)位");
                        rs1=false;
                    }
                }
            });


            $("[name='houseType']").blur(function(){
                var reg=/^[\u4e00-\u9fa5A-Za-z0-9-_]{2,9}/;
                var uname=$("[name='houseType']").val();
                if(uname==""){
                    $("[name='houseType']").next().html("请输入房屋类型");
                    rs2=false;
                }else{
                    if (reg.test(uname)) {
                        $("[name='houseType']").next().html("");
                        rs2 = true;
                    } else {
                        $("[name='houseType']").next().html("只能中英文，数字，下划线，减号(3-9)位");
                        rs2=false;
                    }
                }
            });



            $("[name='area']").blur(function(){
                var num=$("[name='area']").val();
                var reg=/[0-9]{3,9}/;
                if(num==""){
                    $("[name='area']").next().html("请输入房屋面积");
                    rs3=false;
                }else{
                    if (reg.test(num)) {
                        $("[name='area']").next().html("");
                        rs3 = true;
                    } else {
                        $("[name='area']").next().html("数字(3-9)位");
                        rs3=false;
                    }
                }
            });

            $("[name='address']").blur(function(){
                var reg=/^[\u4e00-\u9fa5A-Za-z0-9-_]{3,9}/;
                var uname=$("[name='address']").val();
                if(uname==""){
                    $("[name='address']").next().html("请输入地址");
                    rs4=false;
                }else{
                    if (reg.test(uname)) {
                        $("[name='address']").next().html("");
                        rs4 = true;
                    } else {
                        $("[name='address']").next().html("只能中英文，数字，下划线，减号(3-9)位");
                        rs4=false;
                    }
                }
            });
            $("[name='buildTime']").blur(function(){
                var num=$("[name='buildTime']").val();
                if(num==""){
                    $("[name='buildTime']").next().html("请输入建造时间");
                    rs5=false;
                }else{
                        $("[name='buildTime']").next().html("");
                        rs5 = true;
                }
            });


            $("#form").submit(function(){
                //验证重复工作号
                //验证备注
                if(rs1==true&&rs2==true&&rs3==true&&rs4==true&&rs5==true){
                    return true;
                }else{
                    alert("表单有误,请检查数据");
                    return false;
                }
            });
        })
        function retu() {
            location.href="/query?method=findall"
        }
    </script>
</head>
<body>
<h2>房产信息查询系统</h2>
<form action="/query" method="post" id="form">
    <input type="hidden" name="method" value="add">
    <p>项目名称:<input type="text" name="projectName" ><font color="red"></font> </p>
    <p>房屋类型:<input type="text" name="houseType" ><font color="red"></font> </p>
    <p>使用面积:<input type="text" name="area" ><font color="red"></font> </p>
    <p>房屋地址:<input type="text" name="address" ><font color="red"></font></p>
    <p>建造时间:<input type="Date" name="buildTime"  ><font color="red"></font> </p>
    <input type="submit" value="添加"><input type="button" value="返回" onclick="retu()">

</form>
</body>
</html>
