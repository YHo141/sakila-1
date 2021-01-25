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
						<h2>Film Promotion One</h2>
						<div>
							<h3>Title</h3>
						</div>
						<div>
							<input type="text" value="${title}" readonly="readonly" style="width:35%;padding:7px;margin-bottom:30px; font-size:1.3em; background-color:#BDBDBD;outline:0;border:0;">
						</div>	
						<div>
							<h3>Total/Rental inventory</h3>
						</div>
						<div>
							<input type="text" value="${allPromotion} / ${returnPromotion}" readonly="readonly" style="width:35%;padding:7px;margin-bottom:30px; font-size:1.3em; background-color:#BDBDBD;outline:0;border:0;">
						</div>
						<h2></h2>
						<table id="contentTable">
							<thead>
								<tr>
									<th>번호</th>
									<th>대여일</th>
									<th>대여/반납</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="l" items="${list}" varStatus="status">
									<tr>
										<td><c:out value="${status.index + 1}" /></td>
										<td>${l.rentalDate}<br>${l.customerName}</td>
										<c:if test="${l.rentalDate != null}">
											<td>
												<button onclick="clickReturn('${l.inventoryId}')" type="button" style="background-color: #000000">반납</button>
											</td>
										</c:if>
										<c:if test="${l.rentalDate == null}">
											<td>
												<button onclick="clickRental('${l.inventoryId}')" type="button" style="background-color: #369;">대여</button>
											</td>
										</c:if>
									</tr>
								</c:forEach>
							</tbody>
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
    	function clickReturn(inventoryId){
    		if(confirm('반납 하시겠습니까?')){
	    		location.href='${path}/auth/promotionReturnServlet?filmId=${filmId}&inventoryId=' + inventoryId;			
    		}
    	}
    	
    	function clickRental(inventoryId){
    		location.href='${path}/auth/promotionRentalServlet?title=${title}&filmId=${filmId}&inventoryId=' + inventoryId;
    	}
    
    	$('#btnBack').click(function(){
    		location.href = '${path}/auth/promotionServlet';
    	});
    </script>
</html>