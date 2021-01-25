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
						<form id="formAdd" method="post" action="${path}/auth/promotionRentalServlet">
							<table id="addTable">
								<tr>
									<th>제목</th>
									<td>${title}</td>
								</tr>
								<tr>
									<th>대여 고객</th>
									<td>
										<input type="text" value="" id="customerName" readonly>
										<input type="hidden" id="customerId" name="customerId" value="">
										<input type="hidden" name="filmId" value="${filmId}">
										<input type="hidden" name="inventoryId" value="${inventoryId}">
										<input type="hidden" name="staffId" value="${loginStaff}">
									</td>
								</tr>
							</table>
							<div id="contentSearch2">
								<button id="btnBack" type="button">돌아가기</button>
							</div>
							<div id="contentSearch">
								<button id="btnResult" type="button">대여</button>
							</div>
						</form>
						<h2></h2>
						
						<table id="contentTable">
							<thead>
								<tr>
									<th style="width:10%;">순번</th>
									<th style="width:40%;">이름</th>
									<th style="width:30%;">
										연락처
									</th>
									<th style="width:20%;"></th>
								</tr>
							</thead>
								
							<tbody>
								<c:forEach var="l" items="${list}" varStatus="status">
									<tr>
										<td><c:out value="${status.index + 1 + (currentPage-1) * 10}" /></td>
										<td>${l.customer.lastName}</td>
										<td>${l.address.phone}</td>
										<td><button onclick="clickOne('${l.customer.lastName}', '${l.customer.customerId}');" type="button" style="color:#6b6b6b;width:100%;height:28px;padding:6px">선택</button></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<div id="paging">	
							<a href="${path}/auth/promotionRentalServlet?currentPage=${1}&filmId=${filmId}&inventoryId=${inventoryId}&title=${title}">1</a>
							<a>...</a>
							<c:choose>
								<c:when test="${lastPage < 4 }">
									<c:forEach var="i" begin="${1}" end="${lastPage}">
										<c:choose>
											<c:when test="${i eq currentPage}">
												<a class="currentPage" href="${path}/auth/customerServlet?currentPage=${i}&filmId=${filmId}&inventoryId=${inventoryId}&title=${title}">${i}</a>	
											</c:when>
											<c:otherwise>
												<a href="${path}/auth/promotionRentalServlet?currentPage=${i}&filmId=${filmId}&inventoryId=${inventoryId}&title=${title}">${i}</a>	
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</c:when>
								<c:otherwise>
									<c:choose>
										<c:when test="${currentPage < 4}">
											<c:forEach var="i" begin="${1}" end="${5}">
												<c:choose>
													<c:when test="${i eq currentPage}">
														<a class="currentPage" href="${path}/auth/promotionRentalServlet?currentPage=${i}&filmId=${filmId}&inventoryId=${inventoryId}&title=${title}">${i}</a>	
													</c:when>
													<c:otherwise>
														<a href="${path}/auth/promotionRentalServlet?currentPage=${i}&filmId=${filmId}&inventoryId=${inventoryId}&title=${title}">${i}</a>	
													</c:otherwise>
												</c:choose>
											</c:forEach>
										</c:when>
										<c:when test="${currentPage > lastPage-3}">
											<c:forEach var="i" begin="${lastPage-4}" end="${lastPage}">
												<c:choose>
													<c:when test="${i eq currentPage}">
														<a class="currentPage" href="${path}/auth/promotionRentalServlet?currentPage=${i}&filmId=${filmId}&inventoryId=${inventoryId}&title=${title}">${i}</a>	
													</c:when>
													<c:otherwise>
														<a href="${path}/auth/promotionRentalServlet?currentPage=${i}&filmId=${filmId}&inventoryId=${inventoryId}&title=${title}">${i}</a>	
													</c:otherwise>
												</c:choose>
											</c:forEach>
										</c:when>
										<c:otherwise>
											<c:forEach var="i" begin="${currentPage-2}" end="${currentPage+2}">
												<c:choose>
													<c:when test="${i eq currentPage}">
														<a class="currentPage" href="${path}/auth/promotionRentalServlet?currentPage=${i}&filmId=${filmId}&inventoryId=${inventoryId}&title=${title}">${i}</a>	
													</c:when>
													<c:otherwise>
														<a href="${path}/auth/promotionRentalServlet?currentPage=${i}&filmId=${filmId}&inventoryId=${inventoryId}&title=${title}">${i}</a>	
													</c:otherwise>
												</c:choose>
											</c:forEach>
										</c:otherwise>
									</c:choose>
								</c:otherwise>
							</c:choose>
							<a>...</a>
							<a href="${path}/auth/promotionRentalServlet?currentPage=${lastPage}&filmId=${filmId}&inventoryId=${inventoryId}&title=${title}">end</a>		
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
    	$('#btnResult').click(function(){
    		if($('#customerId').val() == ''){
    			alert('대여하실 고객 명을 선택해 주세요.');
    		} else{
	    		if(confirm('해당하는 비디오를 대여합니다.')){
	    			$('#formAdd').submit();
	    		}
    		}
    	});
    
    	function clickOne(customerName, customerId){
    		$('#customerName').val(customerName);
    		$('#customerId').val(customerId);
    	}
    
    	$('#btnBack').click(function(){
    		location.href = '${path}/auth/promotionOneServlet?filmId=${filmId}';
    	});
    </script>
</html>