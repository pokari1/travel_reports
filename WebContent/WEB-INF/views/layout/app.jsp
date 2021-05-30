<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ja">
    <head>
        <meta charset="UTF-8">
        <title>タイトル</title>
                <link rel="stylesheet" href="<c:url value='/css/reset.css' />">
               <link rel="stylesheet" href="<c:url value='/css/style.css' />">
    <body>
        <div id="wrapper">
            <div id="header">
                    <h1>
                        <a href="<c:url value='/index' />">タイトル</a>
                    </h1>
                <nav class="tr-nav">
                    <ul>
                                 <li><a href="<c:url value='/reports/new' />">書く</a></li>
                        <c:if test="${sessionScope.login_users != null}">
                             <c:if test="${sessionScope.login_users.admin_flag == 1}">
                                 <li><a href="<c:url value='/users/index' />">ユーザー管理</a></li>
                             </c:if>
                        </c:if>
                        <c:if test="${sessionScope.login_users == null}">
                                <li><a href="<c:url value='/new' />">新規登録</a></li>
                                <li><a href="<c:url value='/login' />">ログイン</a></li>
                        </c:if>
                        <c:if test="${sessionScope.login_users != null}">
                                <li><a href="<c:url value='/mypage/index' />">マイページ</a></li>
                                <li><a><c:out value="${sessionScope.login_users.username}" />&nbsp;さん&nbsp;&nbsp;</a></li>
                                <li><a href="<c:url value='/logout' />">ログアウト</a></li>
                        </c:if>
                    </ul>
                </nav>


</div>
</div>

            <div id="content">
                ${param.content}
            </div>
            <div id="footer">
                by Pokari1.
            </div>

    </body>
</html>
