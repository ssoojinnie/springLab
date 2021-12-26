
<%@ tag language="java" pageEncoding="UTF-8" body-content="empty"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="items" type="kr.co.songjava.mvc.domain.BaseCodeLabelEnum[]" required="true" %>
<%@ attribute name="values" type="java.lang.Object" required="false" %>

<%-- 검색조건 부트스트랩 UI 채크박스 공통 tag--%>
<c:forEach var="boardType" items="${boardTypes}" varStatus="status">
    <div class="form-check form-check-inline">
    <input class="form-check-input" name="boardTypes" type="checkbox" value="${boardType.code()}" id="board-type${status.count}">
        <label class="form-check-label" for="board-type${status.count}">
            ${boardType.label()}
        </label>
    </div>
</c:forEach>
