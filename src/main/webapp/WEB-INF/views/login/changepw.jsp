<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
function check(){
	var pw =document.getElementById('pw').value;
	var pwconfirm =document.getElementById('pwconfirm').value;
	var pwRegex = /^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^&*])[A-Za-z\d!@#$%^&*]{4,16}$/; // 영문자, 숫자, 특수문자 포함
	var correctColor = "#00ff00";
    var wrongColor = "#ff0000";
    var confirmMsg = document.getElementById('confirmMsg');
    if (!pwRegex.test(pw)) {
        alert('비밀번호는 4자 이상 16자 이하로 영문자,숫자,특수문자를 포함해야합니다.');
        document.getElementById('pw').focus();
        return false;
    }
    if (pw !== pwconfirm) {
        confirmMsg.style.color = wrongColor;
        confirmMsg.innerHTML = "비밀번호 불일치";
        document.getElementById('pwconfirm').focus();
        return false;
    } else {
        confirmMsg.style.color = correctColor;
        confirmMsg.innerHTML = "비밀번호 일치";
    }
    alert('비밀번호가 변경되었습니다.');
    document.forms[0].submit();
}	
</script>
</head>
<body>

<form action="changepw2" method="post">
<table border="6" align="center">
<tr>
<th>새 비밀번호</th>
	<td><input type="password" id="pw" name="pw" placeholder="비밀번호를 입력해주세요" maxlength="16"></td>
</tr>
<tr>
<th>비밀번호 확인</th>
	<td>
	<input type="password" id="pwconfirm" name="pwconfirm" maxlength="16">
	 <span id="confirmMsg"></span>
	</td>
</tr>
<tr>
<th><input type="hidden" name="id" value="${dto1.id}" ></th>
</tr>
<tr>
<td colspan="2">
	<input type="button" value="변경" onclick="check()">
	<input type="reset" value="취소">
</td>
</tr>
</table>
</form>
</body>
</html>