/**
 * 
 */
window.onload = function() {
	var username = document.getElementsByName("username")[0];
	var password = document.getElementsByName("password")[0];
	var errorMsg = document.getElementsByClassName("errorMsg")[0];

	var btn = document.getElementById("sub_btn");
	var reg_username = /^[\u4e00-\u9fa5A-Za-z0-9-_]{3,9}/
	var reg_pwd = /\w{3,9}/
	var flag1 = true;
	var flag2 = true;
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
	btn.onclick = function() {
		// 当点击时，校验用户名和密码是否为空
		// 获取元素的 值
		var user_value = username.value;
		var pwd_value = password.value;
		if (user_value == "" || user_value == null || pwd_value == ""
				|| pwd_value == null) {
			errorMsg.innerHTML = "用户名和密码不能为空"
			return false;// 取消事件行为（函数）
		}
		if(!flag1 || !flag2){
		if(!flag1){
			errorMsg.innerHTML = "用户名格式不对！"
		}
		if(!flag2){
			errorMsg.innerHTML = "密码格式不对！"
			}
		
		return false;
		}
	}
}