<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> paramMap Example</title>
</head>
<body>
    <div class="container">
        <h2> 게시물 목록 </h2>
        <form action="" method="get">
            <div class="mb-3 row">
                <label for="exampleFormControlInput1" class="col-sm-2 col-form-label"/>
                <div class="col-sm-10">
                    <c:forEach var="boardType" items="${boardTypes}">
                    <div class="form-check form-check-inline">
                        <input class="form-check-input" name="boardTypes" type="checkbox" value="${boardType.code()}" ${paramMap.boardTypes == 'A' ? 'checked="checked"' : ''} id="flexCheckDefault">
                        <label class="form-check-label" for="flexCheckDefault">
                            ${boardType.label()}
                        </label>
                    </div>
                    </c:forEach>
                </div>
            </div>
            <div class="mb-3 text-center">
                <button type="submit" class="btn btn-primary">검색하기</button>
            </div>
        </form>
    </div>
</body>
</html>

