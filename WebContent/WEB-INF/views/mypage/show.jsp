<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <c:choose>
            <c:when test="${report != null}">

   <div class=reportusersdetail>
                 <div class=report_image>
                    <img src="data:image/jpeg;base64,${report.image}" style="width:80% ;height: auto;"/>
                    </div>
                    <br>
                <div class=users_report>
               <c:out value="${report.users.username}" />
                </div>
                <div class=report_detail>
                <h1><c:out value="${report.title}" /></h1>
                <p><c:out value="${report.report_time}" /></p>
                <p><c:out value="${report.prefecture}" /></p>
                <p><c:out value="${report.address}" /></p>
                <p><c:out value="${report.content}" /></p>
</div>
         </div>
                <c:if test="${sessionScope.login_users.id == report.users.id}">
                    <p class="return"><a href="<c:url value="/reports/edit?id=${report.id}" />">この旅行記を編集する</a></p>
                </c:if>
            </c:when>
            <c:otherwise>
                <h2>お探しのデータは見つかりませんでした。</h2>
            </c:otherwise>
        </c:choose>

        <p class="return"><a href="<c:url value="/mypage/index" />">一覧に戻る</a></p>
    </c:param>
</c:import>