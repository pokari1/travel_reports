<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <c:if test="${hasError}">
            <div id="flush_error">
                メールアドレスかパスワードが間違っています。
            </div>
        </c:if>
        <c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}"></c:out>
            </div>
        </c:if>

     <div id="userform">
        <form method="POST" action="<c:url value='/login' />">
             <p class=form-title>ログイン</p>
            <label for="email">メールアドレス</label><br />
            <input type="text" name="email" value="${email}" />
            <br /><br />

            <label for="password">パスワード</label><br />
            <input type="password" name="password" />
            <br /><br />
           <div id="login">
            <input type="hidden" name="_token" value="${_token}" />
            <button type="submit">ログイン</button>
            </div>
        </form>
        </div>
    </c:param>
</c:import>