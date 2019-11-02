/**
 * 
 */
window.onload = function(){
	//获取提示框，名字、密码、确认密码、邮件、验证码文本框，注册按钮元素，
	var errorMsg = document.getElementsByClassName("errorMsg")[0];
	var username = document.getElementsByName("username")[0];
	var password = document.getElementsByName("password")[0];
	var repwd = document.getElementsByName("repwd")[0];
	var email = document.getElementsByName("email")[0];
	var code = document.getElementById("code");
	var btn = document.getElementById("sub_btn");
	//用户名，密码，邮件，验证码正则
	var reg_username = /^[\u4e00-\u9fa5A-Za-z0-9-_]{3,9}/
	var reg_pwd = /\w{3,9}/ 
	var reg_email = /^\w+@\w+[.]\w/;
	var reg_code = /bnbnp/;
	//
	var flag1 = true;
	var flag2 = true;
	var flag3 = true;
	var flag4 = true;
	var flag5 = true;
	//用户名，密码，确认密码，邮件，验证码  正则判断
	username.onblur = function() {
		var value = username.value;
		var b = reg_username.test(value);
		// 当用户名不符合正则时
		if (!b) {
			errorMsg.innerHTML = "用户名格式不对！    只能中英文，数字，下划线，减号(3-9)位"
			flag1 = false;
		}else{
			errorMsg.innerHTML = " "
			flag1 = true;
		}
	}
	
	
	password.onblur = function() {
		var value = password.value;
		var b = reg_pwd.test(value);
		// 当用户名不符合正则时
		if (!b) {
			errorMsg.innerHTML = "密码格式不对！   只能中英文，数字，下划线(3-9)位"
			flag2 = false;
		}else{
			errorMsg.innerHTML = " "
			flag2 =true;
		}
	}
	repwd.onblur = function() {
		var pwd_value = password.value;
		var repwd_value = repwd.value;
		// 当用户名不符合正则时
		if (pwd_value!=repwd_value) {
			errorMsg.innerHTML = "密码重写错误，请确认密码！"
			flag3 = false;
		}else{
			errorMsg.innerHTML = " "
			flag3 =true;
		}
	}
	email.onblur = function() {
		var value = email.value;
		var b = reg_email.test(value);
		// 当用户名不符合正则时
		if (!b) {
			errorMsg.innerHTML = "邮件格式不对！"
			flag4 = false;
		}else{
			errorMsg.innerHTML = " "
			flag4 =true;
		}
	}
	code.onblur = function() {
		var value = code.value;
		var b = reg_code.test(value);
		// 当用户名不符合正则时
		if (!b) {
			errorMsg.innerHTML = "验证码不正确！"
			flag5 = false;
		}else{
			errorMsg.innerHTML = " "
			flag5 =true;
		}
	}
	btn.onclick = function() {
		// 当点击时，校验用户名和密码是否为空
		// 获取元素的 值
		var user_value = username.value;
		var pwd_value = password.value;
		var repwd_value = repwd.value;
		var email_value = email.value;
		var code_value = code.value;
		if (user_value == "" || user_value == null || pwd_value == ""
			|| pwd_value == null || repwd_value == "" || repwd_value == null || email_value == ""
				|| email_value == null || code_value == "" || code_value == null) {
			errorMsg.innerHTML = "信息不能为空"
			return false;// 取消事件行为（函数）
		}
		if(!flag1 || !flag2 || !flag3 || !flag4 || !flag5){
			if(!flag1){
				errorMsg.innerHTML = "用户名格式不对"
			}
			if(!flag2){
				errorMsg.innerHTML = "密码格式不对"
			}
			if(!flag3){
				errorMsg.innerHTML = "密码重写错误，请确认密码！"
			}
			if(!flag4){
				errorMsg.innerHTML = "邮件格式不对"
			}
			if(!flag5){
				errorMsg.innerHTML = "验证码不对"
			}
			return false;
		}
	}
}