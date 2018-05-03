<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- jstl 코어 태그 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script language="JavaScript">

    // 쿠키 생성
    function setCookie(cName, cValue, cDay){
        var expire = new Date();
        expire.setDate(expire.getDate() + cDay);
        cookies = cName + '=' + escape(cValue) + '; path=/ '; // 한글 깨짐을 막기위해 escape(cValue)를 합니다.
        if(typeof cDay != 'undefined') cookies += ';expires=' + expire.toGMTString() + ';';
        document.cookie = cookies;
    }
 
    // 쿠키 가져오기
    function getCookie(cName) {
        cName = cName + '=';
        var cookieData = document.cookie;
        var start = cookieData.indexOf(cName);
        var cValue = '';
        if(start != -1){
            start += cName.length;
            var end = cookieData.indexOf(';', start);
            if(end == -1)end = cookieData.length;
            cValue = cookieData.substring(start, end);
        }
        return unescape(cValue);
    }

</script>


<a href="${path}/board/list.do">게시판</a>
<a href="${path }/shop/product/list.do	">쇼핑몰</a>
<c:choose>
    <c:when test="${sessionScope.id == null}">
        <a href="${path}/member/login.do">로그인</a>
    </c:when>
    <c:otherwise>
        ${sessionScope.name}님이 로그인중입니다.
        <a href="${path}/member/logout.do">로그아웃</a>
    </c:otherwise>
</c:choose>

<input type="button" value="쿠키 생성" onclick="setCookie('${pageContext.request.requestURI}', '<%= request.getRequestURL() %>', 1)">
<input type="button" value="쿠키 보기" onclick="alert(getCookie('${pageContext.request.requestURI}'))">
<input type="button" value="쿠키 삭제" onclick="setCookie('${pageContext.request.requestURI}', '', -1)">
    
<hr>
