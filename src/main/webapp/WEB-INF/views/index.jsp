<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> Insert title here</title>
</head>
<body>
    <form action="/file/save" method="post" enctype="multipart/form-data">
    <%--application/x-www-form-urlencoded : 기본값, 서버로 보내기전 인코딩--%>
    <%--multipart/form-data : 모든문자를 인코딩 X, form 요소가 파일, 이미지일떄--%>
    <%--text/plain : 공백은 + 기호로 변환, 다른문자 인코딩 X--%>
        <input type="file" name="uploadFile" />

        <button type="submit">파일업로드</button>
    </form>
</body>
</html>