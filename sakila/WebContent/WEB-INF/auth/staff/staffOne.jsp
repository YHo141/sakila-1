<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
    </head>
    <body>
		<div id="bg">
			<div id="outer">
				<div id="main">
					<jsp:include page="/inc/menu.jsp"></jsp:include>
					
					<div id="content">
						<h2>Administrator information</h2>
							
						<table>
							<thead>	
								<tr>
									<th>별명</th>
									<th>이름</th>
									<th>연락처</th>
									<th>주소</th>
									<th>E-mail</th>
								</tr>
							</thead>
							
							<tbody>
								<c:forEach var="b" items="${list}">
									<tr>	
										<td>${b.staff.userName}</td>
										<td>${b.staff.lastName}</td>
										<td>${b.address.phone}</td>
										<td>${b.country.country}</td>
										<td>${b.staff.email}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
									
						<br class="clear" />
					</div>
					<br class="clear" />
				</div>
				<br class="clear" />
					
				<div id="copyright">
						<!-- 방문자 / 전체 방문자 -->
						<p>visitant :  ${stats.cnt}/${sumCnt} </p>
						&copy; sakila | Made by byoungyoon
				</div>
			</div>
		</div>
    </body>
</html>









