<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
						<h2>Administrator information</h2>
						<form id="modifyForm" method="post" action="${path}/auth/staffServlet">
							<table id="addTable">
								<tr>
									<th>이름</th>
									<td>
										${list[0].staff.lastName}
										<input type="hidden" name="staffId" value="${loginStaff}">
									</td>
								</tr>
								<tr>
									<th>초기 주소</th>
									<td>${list[0].country.country}</td>
								</tr>
								<tr>
									<th>별명</th>
									<td><input type="text" name="username" id="username" value="${list[0].staff.userName}" style="background-color:#BDBDBD;outline:0;border:0;width:100%;padding:5px;font-size: 20px;font-weight: bold;"></td>
								</tr>
								<tr>
									<th>연락처</th>
									<td><input type="text" name="phone" id="phone" value="${list[0].address.phone}" style="background-color:#BDBDBD;outline:0;border:0;width:100%;padding:5px;font-size: 20px;font-weight: bold;"></td>
								</tr>
								<tr>
									<th>E-mail</th>
									<td><input type="text" name="email" id="email" value="${list[0].staff.email}" style="background-color:#BDBDBD;outline:0;border:0;width:100%;padding:5px;font-size: 20px;font-weight: bold;"></td>
								</tr>
							</table>
							<div id="contentSearch">
								<button id="btnResult" type="button">수정</button>
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
		var numCheck= /^[0-9]+$/;
   		var emailCheck=/^[-A-Za-z0-9_]+[-A-Za-z0-9_.]*[@]{1}[-A-Za-z0-9_]+[-A-Za-z0-9_.]*[.]{1}[A-Za-z]{1,5}$/;
   		
    	$('#contentSearch').click(function(){
    		if($('#username').val().length < 1){
    			alert('별명을 확인해주세요.');
    		} else if(!numCheck.test($('#phone').val())){
    			alert('연락처을 확인해주세요.');
    		} else if(!emailCheck.test($('#email').val())){
    			alert('이메일을 확인해주세요.');
    		} else{
    			if($('#username').val() == '${list[0].staff.userName}' && $('#phone').val() == '${list[0].address.phone}' && $('#email').val() == '${list[0].staff.email}'){
    				alert('현재 별명, 연락처 그리고 이메일과 동일합니다.');
    			} else{
    				if(confirm('현재 수정하신 값으로 변경합니다.')){
			    		$('#modifyForm').submit();								
    				}
    			}
    		}
    	});
    </script>
</html>









