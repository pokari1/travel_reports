<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/views/layout/app.jsp">

    <c:param name="content">
        <div id="reportnew">
       <p class=form-title>旅行記投稿</p>

        <form method="POST" name="report_form" action="<c:url value='/reports/create' />" enctype="multipart/form-data">
            <c:import url="_form.jsp" />
        </form>

        <p><a href="<c:url value='/mypage/index' />">一覧に戻る</a></p>
        </div>
    </c:param>

</c:import>