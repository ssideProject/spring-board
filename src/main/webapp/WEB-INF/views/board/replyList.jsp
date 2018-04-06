<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@ include file="../include/header.jsp" %>
</head>
<body>
    <table style="width:650px; text-ailan:center;">
        <c:forEach var="row" items="${list}">
        <tr>    
            <td>
                ${row.userName} / (<fmt:formatDate value="${row.regdate}" pattern="yyyy-MM-dd HH:mm:ss"/>)
                <br>
                ${row.replytext}
                <!-- 본인 댓글만 수정버튼 생성되도록 처리 -->
                <c:if test="${sessionScope.id == row.replyer}">
                    <input type="button" id="btnModify" value="수정" onclick="showReplyModify('${row.rno}')">
                </c:if>
                <hr>
            </td>
        </tr>
        </c:forEach>
        <!-- **페이징 처리 -->
        <tr>
            <td>
                <!-- 현재 페이지 블럭이 1보다 크면 처음으로 이동 -->
                <c:if test="${replyPager.curBlock > 1}">
                    <a href="javascript:listReplyRest('1')">[처음]</a>
                </c:if>
                <!-- 현재 페이지 블럭이 1보다 크면 이전 페이지 블럭으로 이동 -->
                <c:if test="${replyPager.curBlock > 1}">
                    <a href="javascript:listReplyRest('${replyPager.prevPage}')">[이전]</a>
                </c:if>
                <!-- 페이지 블럭 처음부터 마지막 블럭까지 1씩 증가하는 페이지 출력 -->
                <c:forEach var="num" begin="${replyPager.blockBegin}" end="${replyPager.blockEnd}">
                    <c:choose>
                        <c:when test="${num == replyPager.curPage}">
                            ${num}&nbsp;
                        </c:when>
                        <c:otherwise>
                            <a href="javascript:listReplyRest('${num}')">${num}</a>&nbsp;
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
                <!-- 현재 페이지 블럭이 전체 페이지 블럭보다 작거나 같으면 다음페이지로 이동 -->
                <c:if test="${replyPager.curBlock <= replyPager.totBlock}">
                    <a href="javascript:listReplyRest('${replyPager.nextPage}')">[다음]</a>
                </c:if>
                <!-- 현재 페이지 블럭이 전체 페이지 블럭보다 작거나 같으면 끝으로 이동 -->
                <c:if test="${replyPager.curBlock <= replyPager.totBlock}">
                    <a href="javascript:listReplyRest('${replyPager.totPage}')">[끝]</a>
                </c:if>
            </td>
        </tr>
    </table>
    
    <div id ="modifyReply"></div>
</body>
</html>
