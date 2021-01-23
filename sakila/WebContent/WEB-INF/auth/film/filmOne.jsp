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
						<h2>film One List</h2>
						<form id="formModify" method="post" action="${path}/auth/filmOneServlet">
							<table id="addTable">
								<tr>
									<th>제목</th>
									<td><input type="hidden" name="filmId" value="${filmList.film.filmId}">${filmList.film.title}</td>
								</tr>
								<tr>
									<th>설명</th>
									<td><textarea rows="5" name="description" id="description" style="background-color:#BDBDBD;outline:0;border:0;width:100%;padding:5px;font-size: 20px;font-weight: bold;">${filmList.film.description}</textarea>
								</tr>
								<tr>
									<th>카테고리</th>
									<td>${filmList.category.name}</td>
								</tr>
								<tr>
									<th>가격</th>
									<td><input type="text" name="rentalRate" id="rentalRate" value="${filmList.film.rentalRate}" style="background-color:#BDBDBD;outline:0;border:0;text-align:center;"></td>
								</tr>
								<tr>
									<th>상영 시간</th>
									<td>${filmList.film.length}</td>
								</tr>
								<tr>
									<th>시청 등급</th>
									<td>${filmList.film.rating}</td>
								</tr>
								<tr>
									<th>출연 배우</th>
									<td>
										<div>
										<c:forEach var="a" items="${actorList}">
											${a.lastName},&nbsp;
										</c:forEach>
										</div>
									</td>
								</tr>	
							</table>
							<div id="contentSearch2">
								<button id="btnBack" type="button" style="margin-top:15px">돌아가기</button>
							</div>
							<div id="contentSearch">
								<button id="btnResult" type="button" style="margin-top:15px">수정</button>
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
    	$('#btnResult').click(function(){
    		if($('#description').val() == '${filmList.film.description}' && $('#rentalRate').val() == '${filmList.film.rentalRate}'){
    			alert('입력하신 설명과 가격이 현재와 동일합니다.');
    		} else{
    			if(confirm('입력 하신 설명과 가격으로 수정합니다.')){
    				$('#formModify').submit();    			    				
    			}
    		}
    	});
    
    	$('#btnBack').click(function(){
    		location.href='${path}/auth/filmServlet';
    	});
    </script>
</html>