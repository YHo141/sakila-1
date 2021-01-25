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
						<h2>Starring Actor One</h2>
						<form id="formModify" method="post" action="${path}/auth/customerOneServlet">
							<table id="addTable">
								<tr>
									<th>소속 매장</th>
									<td>
										${join.customer.storeId}
										<input type="hidden" name="customerId" value="${join.customer.customerId}">
									</td>
								</tr>
								<tr>
									<th>이름</th>
									<td>${join.customer.lastName}</td>
								</tr>
								<tr>
									<th>연락처</th>
									<td><input type="text" name="phone" id="phone" value="${join.address.phone}" style="background-color:#BDBDBD;outline:0;border:0;text-align:center;">	</td>
								</tr>
								<tr>
									<th>초기 주소</th>
									<td>${join.address.address}</td>
								</tr>
								<tr>
									<th>이메일</th>
									<td><input type="text" name="email" id="email" value="${join.customer.email}" style="background-color:#BDBDBD;outline:0;border:0;text-align:center;">	</td>
								</tr>
							</table>
							<div id="contentSearch">
								<button id="btnResult" type="button" style="margin-top:15px">수정</button>
							</div>
						</form>
						<h2></h2>
						<h3>Overdue</h3>
						<table id="contentTable">
							<thead>
								<tr>
									<th>영화 이름</th>
									<th>대여일</th>
									<th>예정 반납일</th>
									<th>연체액($)</th>
								</tr>
							</thead>
							<c:if test="${fn:length(list) == 0}">
								<td colspan="4">현재 연체중인 것이 없습니다.</td>
							</c:if>
							<c:if test="${fn:length(list) != 0}">							
								<tbody>
									<c:forEach var="l" items="${list}">
										<tr>
											<td>${l.film.title}</td>
											<td>${l.rental.rentalDate}</td>
											<td>${l.rental.scheduledReturnDate}</td>
											<td>${l.rental.arrears}</td>
										</tr>
									</c:forEach>
								</tbody>
							</c:if>
						</table>
						<div id="contentSearch2">
							<button id="btnBack" type="button" style="margin-top:15px">돌아가기</button>
						</div>
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
   		var numCheck= /^[0-9]+$/;
   		var emailCheck=/^[-A-Za-z0-9_]+[-A-Za-z0-9_.]*[@]{1}[-A-Za-z0-9_]+[-A-Za-z0-9_.]*[.]{1}[A-Za-z]{1,5}$/;
    
    	$('#btnResult').click(function(){
    		if(!numCheck.test($('#phone').val())){
    			alert('수정 하신 번호를 확인해주세요');
    		} else if(!emailCheck.test($('#email').val())){
    			alert('수정 하신 이메일을 확인해주세요.');
    		} else{	
	    		if($('#phone').val() == '${join.address.phone}' && $('#email').val() == '${join.customer.email}'){
	    			alert('현재 번호와 이메일이 동일합니다.');
	    		} else{
	    			if(confirm('수정하신 번호와 이메일로 변경합니다.')){
	    				$('#formModify').submit();
	    			}
	    		}
    		}
    	});
    
    	$('#btnBack').click(function(){
    		location.href='${path}/auth/customerServlet';
    	});
   	</script>
</html>