<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> 홈페이지 만들기</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body>
    <div class="container">
        <form id="form" method="get" action="/board/list">
            <div class="row mb-3">
                <label for="keyword" class="col-sm-2 col-form-label"><spring:message code="search.keyword"/></label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" name="keyword" value="${parameter.keyword}" id="keyword" placeholder="<spring:message code="placeholder.keyword"/>"/>
                </div>
            </div>
            <button type="submit" class="btn btn-primary"><spring:message code="button.search"/></button>
        </form>

            <table class="table caption-top">
              <thead>
                <tr>
                  <th scope="col">#</th>
                  <th scope="col"><spring:message code="board.title"/></th>
                  <th scope="col"><spring:message code="board.viewCount"/></th>
                  <th scope="col"><spring:message code="board.regDate"/></th>
                </tr>
              </thead>
              <tbody>
              <c:forEach var="board" items="${boardList}" varStatus="status">
                <tr>
                  <th scope="row">${status.count}</th>
                  <td><a href="/board/${board.boardSeq}">${board.title}</a></td>
                  <td>${board.viewCount}</td>
                  <td><fmt:formatDate value="${board.regDate}" pattern="yyyy.MM.dd HH:mm"/></td>
                </tr>
                </c:forEach>
                <c:if test="${fn:length(boardList) == 0 }">
                <tr>
                    <td colspan="4"><spring:message code="msg.board.empty"/></td>
                </tr>
                </c:if>
              </tbody>
            </table>
            <div class="d-grid gap-2 d-md-flex justify-content-md-end mt-2">
                <a href="/board/form" class="btn btn-primary" type="button"><spring:message code="button.form"/></a>
            </div>
    </div>
     <script src="https://code.jquery.com/jquery-1.11.3.js"></script>
     <script>
        $(function(){

            var $form = $('#form'); //$에 #을 붙이면 화면 컴포넌트의 id를 의미함
            $form.bind('submit', function(){
                $.ajax({
                    url: '/board/list',
                    type: 'get',
                    data: $form.serialize(),//자동으로 key value 값으로 변환 //JSON.stringify(json),
                    //contentType: 'application/json',
                    dataType: 'json',
                    success: function(data){
                        if(data.code == 'SUCCESS'){

                        } else{
                            alert(data.message);
                        }
                        console.log(data);
                    }
                });
                return false;
            });
        });
     </script>

</body>
</html>