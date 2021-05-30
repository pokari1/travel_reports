<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layout/app.jsp">
     <c:param name="content">
        <c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}"></c:out>
            </div>
        </c:if>
<div class="main_image_random">
<img src="data:image/jpeg;base64,${random.image}" style="width:80% ;height: auto;"/>
</div>
<br>


        <div class="middle_title">最新の投稿</div>
     <section class="reports_list">
                <c:forEach var="reports" items="${reports}" varStatus="status">
                     <div><a href="<c:url value="/show?id=${reports.id}" />"><img src="data:image/jpeg;base64,${reports.image}" style="width:80% ;height: auto;"/></a><br>
                       <c:out value="${reports.title}" />
                                      <c:out value="${reporst.users.username}" />
                       </div>
                </c:forEach>
    </section>
    </c:param>
</c:import>
