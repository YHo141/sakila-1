<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta name="keywords" content="" />
        <meta name="description" content="" />
        <meta http-equiv="content-type" content="text/html; charset=utf-8" />
		<title>sakila</title>
        <link href="/sakila/sakilaStyle.css" rel="stylesheet" type="text/css" />
        <link href="http://fonts.googleapis.com/css?family=Arvo" rel="stylesheet" type="text/css" />
        <script src='https://kit.fontawesome.com/a076d05399.js'></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    </head>
    <body>
		<div id="bg">
			<div id="outer">
				<div id="main">
				
					<jsp:include page="/inc/menu.jsp"></jsp:include>
					
					<div id="content">
						<h2>Actor One List</h2>
						<form id="formModify" method="post" action="${path}/auth/actorOneServlet">
							<table id="addTable">
								<tr>
									<th>번호</th>
									<td><input type="hidden" name="actorId" value="${list[0].actor.actorId}">${list[0].actor.actorId}</td>
								</tr>
								<tr>
									<th>성</th>
									<td><input type="text" name="firstName" id="firstName" value="${list[0].actor.firstName}" style="background-color:#BDBDBD;outline:0;border:0;text-align:center;"></td>
								</tr>
								<tr>
									<th>이름</th>
									<td><input type="text" name="lastName" id="lastName" value="${list[0].actor.lastName}" style="background-color:#BDBDBD;outline:0;border:0;text-align:center;"></td>
								</tr>
							</table>
							<div id="contentSearch">
								<button id="btnResult" type="button">수정</button>
							</div>
							<div id="subTitle">
								<h2></h2>
								<h3>Movie starring (${list.size()})</h3>
								<table style="border:1px solid #ccc;">
									<tr>
										<th>
											<c:if test="${empty list[0].film.title}">
												출연하는 영화가 없습니다.
											</c:if>
											<c:if test="${not empty list[0].film.title}">
												<c:forEach var="l" items="${list}">
													${l.film.title},&nbsp;
												</c:forEach>
											</c:if>
										</th>
									</tr>
								</table>
								<div id="contentSearch2">
									<button id="btnBack" type="button" style="margin-top:15px">돌아가기</button>
								</div>
							</div>
						</form>
						<br class="clear" />
					</div>
					<br class="clear" />
				</div>
				<br class="clear" />
					
				<div id="copyright">
						&copy; sakila | Made by byoungyoon
				</div>
			</div>
		</div>
    </body>
    <script>
    	$('#btnBack').click(function(){
    		location.href='${path}/auth/actorServlet';
    	});
    
    	$('#btnResult').click(function(){
    		if($('#firstName').val() == '${list[0].actor.firstName}' && $('#lastName').val() == '${list[0].actor.lastName}'){
    			alert('입력하신 성과 이름은 현재와 동일합니다.');
    		} else{
    			alert('수정되었습니다.');
    			$('#formModify').submit();
    		}
    	});
    </script>
</html>