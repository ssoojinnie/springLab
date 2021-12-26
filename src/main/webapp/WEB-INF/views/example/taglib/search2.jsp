<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tag" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> taglib example </title>
</head>
<body>
    <script src="https://code.jquery.com/jquery-1.11.3.js"></script>
    <div class="container">
        <h2> 게시물 목록 </h2>
        <form action="" method="get">
            <div class="mb-3 row">
                <label for="exampleFormControlInput1" class="col-sm-2 col-form-label">
                <div class="col-sm-10">
                    <tag:bootstrap-checkbox items="${boardTypes}" values="${parameter.boardTypes}"></tag:bootstrap-checkbox>
                </div>
                </label>
            </div>
            <div class="mb-3 text-center">
                <button type="submit" class="btn btn-primary">검색하기</button>
            </div>
        </form>
    </div>
</body>
</html>