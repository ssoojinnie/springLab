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
    <title> Insert title here</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <style type="text/css">
        .active {color: red !important; }
    </style>
    <script src="https://code.jquery.com/jquery-1.11.3.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.2/js/all.min.js" integrity="sha512-UwcC/iaz5ziHX7V6LjSKaXgCuRRqbTp1QHpbOJ4l1nw2/boCfZ2KlFIqBUA/uRVF0onbREnY9do8rM/uT/ilqw==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    <script src="/resources/js/lib/ajax.js"></script>
    <script src="/resources/js/lib/modal.js"></script>
    <script>
        $(function(){

            //모달 오픈
            $('.btn-modal-open').bind('click', function(){
                var modal = new Modal();
                modal.open({
                    url: $(this).data().url
                });
            });
        });
    </script>

    <sitemesh:write property="head"/>
</head>
<body>

    <nav class="navbar navbar-expand-lg navbar-light bg-light">
      <div class="container-fluid">
        <a class="navbar-brand" href="#">Navbar</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
          <ul class="navbar-nav me-auto mb-2 mb-lg-0">
            <li class="nav-item">
              <a class="nav-link" aria-current="page" href="#">Home</a>
            </li>
            <c:forEach var="menu" items="${menuTypes}">
            <li class="nav-item"><a class="nav-link ${menu == menuType ? 'active' : ''}" href="${menu.url()}"><spring:message code="${menu.menuCode()}"/></a></li>
            </c:forEach>
            <%--
            <li class="nav-item"><a class="nav-link ${menuType.name() == 'notice' ? 'active' : ''}" href="/notice"><spring:message code="menu.notice"/></a></li>
            <li class="nav-item"><a class="nav-link ${menuType.name() == 'faq' ? 'active' : ''}" href="/faq"><spring:message code="menu.faq"/></a></li>
            <li class="nav-item"><a class="nav-link ${menuType.name() == 'inquiry' ? 'active' : ''}" href="/inquiry"><spring:message code="menu.inquiry"/></a></li>
            <li class="nav-item"><a class="nav-link ${menuType.name() == 'community' ? 'active' : ''}" href="/community"><spring:message code="menu.community"/></a></li>
            --%>
          </ul>
          <form class="d-flex">
            <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
            <button class="btn btn-outline-success" type="submit">Search</button>
          </form>
        </div>
      </div>
    </nav>

    <div class="container">
        <sitemesh:write property="body"/>
    <div>
    <footer class="bd-footer p-3 p-md-5 mt-5 bg-light text-center text-sm-start">
    <div class="container">
        <ul class="bd-footer-links ps-0 mb-3">
            <li class="d-inline-block"><a href="javascript:;" data-url="/terms" class="btn-modal-open"><spring:message code="modal.terms"/></a></li>
        </ul>
    </div>
    </footer>
  </body>
</html>