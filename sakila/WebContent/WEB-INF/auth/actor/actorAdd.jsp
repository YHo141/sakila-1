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
						<h2>Actor Add List</h2>
						<form id="formAdd" method="post" action="${path}/auth/actorAddServlet">
							<table id="addTable">
								<tr>
									<th>성</th>
									<td><input type="text" id="firstName" name="firstName"></td>
								</tr>
								<tr>
									<th>이름</th>
									<td><input type="text" id="lastName" name="lastName"></td>
								</tr>
							</table>
							<div id="contentSearch2">
								<button id="btnBack" type="button">돌아가기</button>
							</div>
							<div id="contentSearch">
								<button id="btnResult" type="button">추가</button>
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
    		location.href = '${path}/auth/actorServlet';
    	});
    	
    	$('#btnResult').click(function(){
    		if($('#firstName').val().length < 1 || $('#lastName').val().length < 1){
    			alert('배우의 성이나 이름을 입력해주세요.');
    		} else{
    			if(confirm('입력하신 성과 이름으로 등록합니다.')){
    				$('#formAdd').submit();
    			}
    		}
    	});
    </script>
</html>