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
    </head>
    <body>
		<div id="bg">
			<div id="outer">
				<div id="main">
				
					<jsp:include page="/inc/menu.jsp"></jsp:include>
					
					<div id="content">
						<h2>Customer List</h2>
						
						<div id="contentSearch">
							<form method="post" action="${path}/auth/customerServlet">
								<input name="searchName" value="${searchName}" type="text" placeholder=" Please Enter customer_name">
								<button type="submit">검색</button>
							</form>
						</div>
						
						<table id="contentTable">
							<thead>
								<tr>
									<th style="width:10%;">순번</th>
									<th style="width:40%;">이름</th>
									<th style="width:30%;">연락처</th>
									<th style="width:10%;">활성여부</th>
									<th style="width:10%;">대여여부</th>
								</tr>
							</thead>
								
							<tbody>
								<c:forEach var="l" items="${list}" varStatus="status">
									<tr>
										<td><c:out value="${status.index + 1 + (currentPage-1) * 10}" /></td>
										<td><button onclick="clickOne('${l.customer.customerId}');" type="button" style="color:#6b6b6b;width:100%;height:28px;padding:6px">${l.customer.lastName}</button></td>
										<td>${l.address.phone}</td>
										<td>
											<c:if test="${l.customer.active == 1}">
												활성화
											</c:if>
											<c:if test="${l.customer.active == 0}">
												비활성화
											</c:if>
										</td>
										<td>${l.rental.rentalDate}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
								
						<div id="paging">	
							<a href="${path}/auth/customerServlet?currentPage=${1}&searchName=${searchName}">1</a>
							<a>...</a>
							<c:choose>
								<c:when test="${lastPage < 4 }">
									<c:forEach var="i" begin="${1}" end="${lastPage}">
										<c:choose>
											<c:when test="${i eq currentPage}">
												<a class="currentPage" href="${path}/auth/customerServlet?currentPage=${i}&searchName=${searchName}">${i}</a>	
											</c:when>
											<c:otherwise>
												<a href="${path}/auth/customerServlet?currentPage=${i}&searchName=${searchName}">${i}</a>	
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
														<a class="currentPage" href="${path}/auth/customerServlet?currentPage=${i}&searchName=${searchName}">${i}</a>	
													</c:when>
													<c:otherwise>
														<a href="${path}/auth/customerServlet?currentPage=${i}&searchName=${searchName}">${i}</a>	
													</c:otherwise>
												</c:choose>
											</c:forEach>
										</c:when>
										<c:when test="${currentPage > lastPage-3}">
											<c:forEach var="i" begin="${lastPage-4}" end="${lastPage}">
												<c:choose>
													<c:when test="${i eq currentPage}">
														<a class="currentPage" href="${path}/auth/customerServlet?currentPage=${i}&searchName=${searchName}">${i}</a>	
													</c:when>
													<c:otherwise>
														<a href="${path}/auth/customerServlet?currentPage=${i}&searchName=${searchName}">${i}</a>	
													</c:otherwise>
												</c:choose>
											</c:forEach>
										</c:when>
										<c:otherwise>
											<c:forEach var="i" begin="${currentPage-2}" end="${currentPage+2}">
												<c:choose>
													<c:when test="${i eq currentPage}">
														<a class="currentPage" href="${path}/auth/customerServlet?currentPage=${i}&searchName=${searchName}">${i}</a>	
													</c:when>
													<c:otherwise>
														<a href="${path}/auth/customerServlet?currentPage=${i}&searchName=${searchName}">${i}</a>	
													</c:otherwise>
												</c:choose>
											</c:forEach>
										</c:otherwise>
									</c:choose>
								</c:otherwise>
							</c:choose>
							<a>...</a>
							<a href="${path}/auth/customerServlet?currentPage=${lastPage}&searchName=${searchName}">end</a>		
						</div>
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
    <script>
    	function clickOne(customerId){
    		location.href='${path}/auth/customerOneServlet?customerId=' + customerId;
    	}
    </script>
</html>