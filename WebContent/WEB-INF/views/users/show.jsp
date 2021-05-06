<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <c:choose>
            <c:when test="${users != null}">
                <h2>id : ${users.id} のユーザー　詳細ページ</h2>

                <table>
                    <tbody>
                        <tr>
                            <th>ユーザー名</th>
                            <td><c:out value="${users.username}" /></td>
                        </tr>
                        <tr>
                            <th>メールアドレス</th>
                            <td><c:out value="${users.email}" /></td>
                        </tr>
                        <tr>
                            <th>権限</th>
                            <td>
                                <c:choose>
                                    <c:when test="${users.admin_flag == 1}">管理者</c:when>
                                    <c:otherwise>一般</c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                    </tbody>
                </table>

                <p><a href="<c:url value='/users/edit?id=${users.id}' />">このユーザーの情報をを編集する</a></p>
            </c:when>
            <c:otherwise>
                <h2>お探しのデータは見つかりませんでした。</h2>
            </c:otherwise>
        </c:choose>

        <p><a href="<c:url value='/users/index' />">一覧に戻る</a></p>
    </c:param>
</c:import>