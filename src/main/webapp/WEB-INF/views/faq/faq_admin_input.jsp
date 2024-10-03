<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
body {
	font-family: Arial, sans-serif;
	background-color: #f9f9f9;
	color: #333;
}

.container {
	width: 60%;
	margin: 0 auto;
	background-color: #fff;
	padding: 20px;
	border: 1px solid #ddd;
	border-radius: 8px;
	box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

table {
	width: 100%;
	border-collapse: collapse;
	margin: 20px 0;
}

caption {
	font-size: 22px;
	font-weight: bold;
	margin-bottom: 10px;
	text-align: left;
	color: #333;
}

th, td {
	padding: 12px;
	border-bottom: 1px solid #ddd;
}

th {
	background-color: #f4f4f4;
	text-align: left;
}

td {
	background-color: #fff;
}

input[type="text"], textarea, select {
	width: 100%;
	padding: 10px;
	border: 1px solid #ddd;
	border-radius: 4px;
	box-sizing: border-box;
}

input[type="file"] {
	margin-bottom: 10px;
}

input[type="submit"] {
	background-color: #333;
	color: #fff;
	padding: 10px 20px;
	border: none;
	border-radius: 4px;
	cursor: pointer;
}

input[type="submit"]:hover {
	background-color: #555;
}
</style>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<form action="faq_admin_save" method="post"
			enctype="multipart/form-data">
			<table>
				<caption>FAQ : 글 작성</caption>
				<tr>
					<th>문의 종류</th>
					<td><input type="text" name="tab" value="FAQ" readonly>
					</td>
				</tr>
				<tr>
					<th>제목</th>
					<td><input type="text" name="title"></td>
				</tr>
				<tr>
					<th>작성자</th>
					<td><input type="text" value="운영자" name="nickname" readonly></td>
				</tr>
				<tr>
					<th>내용</th>
					<td><textarea rows="10" cols="25" name="fcontents"></textarea>
					</td>
				</tr>
				<tr>
					<th>첨부 이미지</th>
					<td><input type="file" name="fimage1"> 
						<input type="file" name="fimage2"> 
						<input type="file" name="fimage3"></td>
				</tr>
				<tr>
					<td colspan="2" style="text-align: center;"><input
						type="submit" value="작성 완료"></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>