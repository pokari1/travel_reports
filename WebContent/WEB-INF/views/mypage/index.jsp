<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}"></c:out>
            </div>
        </c:if>

        <h2>旅行記一覧</h2>
        <section class="reports_list">
          <c:forEach var="report" items="${reports}" varStatus="status">
                     <div>
                     <a href="<c:url value="/show?id=${report.id}" />">
                     <img src="data:image/jpeg;base64,${report.image}" style="width:80% ;height: auto;"/>
                     </a>
                     <br>
                      <p class="report_title">${report.title}</p>
                       </div>
              </c:forEach>
</section>

        <div id="pagination">
            （全 ${reports_count} 件）<br />
            <c:forEach var="i" begin="1" end="${((reports_count - 1) / 15) + 1}" step="1">
                <c:choose>
                    <c:when test="${i == page}">
                        <c:out value="${i}" />&nbsp;
                    </c:when>
                    <c:otherwise>
                        <a href="<c:url value='/reports/index?page=${i}' />"><c:out value="${i}" /></a>&nbsp;
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>
        <p class="return"><a href="<c:url value='/reports/new' />">旅行記の登録</a></p>

    </c:param>
</c:import>