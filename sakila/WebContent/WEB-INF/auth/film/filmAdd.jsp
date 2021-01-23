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
						<h2>film Add List</h2>
						<form id="formAdd" method="post" action="${path}/auth/filmAddServlet">
							<table id="addTable">
								<tr>
									<th>제목</th>
									<td><input type="text" id="title" name="title"></td>
								</tr>
								<tr>
									<th>설명</th>
									<td><textarea name="description" id="description" rows="5" style="width:100%;padding:5px;font-size: 20px;font-weight: bold;"></textarea></td>
								</tr>
								<tr>
									<th>언어</th>
									<td>
										<select id="languageId" name="languageId" style="width:100%;padding:5px;font-size:20px;font-weight: bold;">
											<option value=""></option>
											<c:forEach var="l" items="${languageList}">
												<option value="${l.languageId}">${l.name}</option>
											</c:forEach>
										</select>
									</td>
								</tr>
								<tr>
									<th>카테고리</th>
									<td>
										<select id="categoryId" name="categoryId" style="width:100%;padding:5px;font-size:20px;font-weight: bold;">
											<option value=""></option>
											<c:forEach var="c" items="${categoryList}">
												<option value="${c.categoryId}">${c.name}</option>
											</c:forEach>
										</select>
									</td>
								</tr>
								<tr>
									<th>가격</th>
									<td><input type="text" id="rentalRate" name="rentalRate"></td>
								</tr>
								<tr>
									<th>상영 시간</th>
									<td><input type="text" id="length" name="length"></td>
								</tr>
								<tr>
									<th>시청 등급</th>
									<td>
										<select id="rating" name="rating" style="width:100%;padding:5px;font-size:20px;font-weight: bold;">
											<option value=""></option>
											<option value="G">G</option>
											<option value="PG">PG</option>
											<option value="PG-13">PG-13</option>
											<option value="R">R</option>
											<option value="NC-17">NC-17</option>
										</select>
									</td>
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
    	var titleCheck = '';
    	var desCheck = '';
    	var lanCheck = '';
    	var categoryCheck = '';
    	var rentalRateCheck = '';
    	var lengthCheck = '';
    	var rating = '';
    	
    	$('#btnResult').click(function(){
    		if(titleCheck == ''){
    			$('#title').css('outline', '2px solid #FF0000');
    		}
    		if(desCheck == ''){
    			$('#description').css('outline', '2px solid #FF0000');
    		}
    		if(lanCheck == ''){
    			$('#languageId').css('outline', '2px solid #FF0000');
    		}
    		if(categoryCheck == ''){
    			$('#categoryId').css('outline', '2px solid #FF0000');
    		}
    		if(rentalRateCheck == ''){
    			$('#rentalRate').css('outline', '2px solid #FF0000');
    		}
    		if(lengthCheck == ''){
    			$('#length').css('outline', '2px solid #FF0000');
    		}
    		if(rating == ''){
    			$('#rating').css('outline', '2px solid #FF0000');
    		}
    		if(titleCheck == '' || desCheck == '' || lanCheck == '' || categoryCheck == '' || rentalRateCheck == '' || lengthCheck == '' || rating == ''){
    			alert('형식을 확인해주세요.');
    		} else{
    			if(confirm('입력하신 값으로 영화 등록합니다.')){
    				$('#formAdd').submit();
    			}
    		}
  
    	});
   	 
    	$('#title').blur(function(){
       		if($('#title').val().length >= 1){
    			$('#title').css('outline', '2px solid #5CD1E5');
    			titleCheck = 'check';
    		} else{
    			$('#title').css('outline', '2px solid #FF0000');
    		}
    	});
    	
    	$('#description').blur(function(){
       		if($('#description').val().length >= 1){
    			$('#description').css('outline', '2px solid #5CD1E5');
    			desCheck = 'check';
    		} else{
    			$('#description').css('outline', '2px solid #FF0000');
    		}
    	});
    	
    	$('#languageId').blur(function(){
       		if($('#languageId').val() != ''){
    			$('#languageId').css('outline', '2px solid #5CD1E5');
    			lanCheck = 'check';
    		} else{
    			$('#languageId').css('outline', '2px solid #FF0000');
    		}
    	});
    	
    	$('#categoryId').blur(function(){
       		if($('#categoryId').val() != ''){
    			$('#categoryId').css('outline', '2px solid #5CD1E5');
    			categoryCheck = 'check';
    		} else{
    			$('#categoryId').css('outline', '2px solid #FF0000');
    		}
    	});
    	var numCheck = /^[0-9]+\.?[0-9]+$/
    	
    	$('#rentalRate').blur(function(){
    		if(numCheck.test($('#rentalRate').val())){
    			$('#rentalRate').css('outline', '2px solid #5CD1E5');
    			rentalRateCheck = 'check';
    		} else{
    			$('#rentalRate').css('outline', '2px solid #FF0000');
    		}
    	});
    	
    	var numCheck2 = /^[0-9]/g;
 		
    	$('#length').blur(function(){
    		if(numCheck2.test($('#length').val())){
    			$('#length').css('outline', '2px solid #5CD1E5');
    			lengthCheck = 'check';
    		} else{
    			$('#length').css('outline', '2px solid #FF0000');
    		}
    	});
    	
    	$('#rating').blur(function(){
       		if($('#rating').val() != ''){
    			$('#rating').css('outline', '2px solid #5CD1E5');
    			rating = 'check';
    		} else{
    			$('#rating').css('outline', '2px solid #FF0000');
    		}
    	});
    
    	$('#btnBack').click(function(){
    		location.href='${path}/auth/filmServlet';
    	});
    </script>
</html>