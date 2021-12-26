<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" url="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> paramMap Example</title>
</head>
<body>
    <h2 style="text/align: center; margin-top:100px;">ids : ${ids}</h2>
    <c:foreach var="id" items="{ids}">
        <p>${id}</p>
    </c:foreach>
</body>
</html>