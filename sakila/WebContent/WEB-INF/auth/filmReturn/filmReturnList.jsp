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
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    </head>
    <body>
		<div id="bg">
			<div id="outer">
				<div id="main">
				
					<jsp:include page="/inc/menu.jsp"></jsp:include>
					
					<div id="content">
							<h2>Film Return</h2>
							
							<div id="contentSearch">
								<input type="text">
								<a href="">검색</a>	
							</div>
							
							<table id="contentTable">
								<thead>	
									<tr>
										<th>rental_id</th>
										<th>film_title</th>
										<th>대여일</th>
										<th>반납예정일</th>
										<th>반납</th>
									</tr>
								</thead>
								
								<tbody>
									<c:forEach var="b" items="${list}">
										<tr>
											<td>${b.rental.rentalId}</td>
											<td>${b.film.title}</td>
											<td>${b.film.rentalDuration}</td>
											<td>${b.rental.rentalDate}</td>
											<td><button type="button">반납</button></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
							
							<div id="paging">
								<a href="${pageContext.request.contextPath}/auth/RentalServlet?currentPage=1">1</a>
								<a>...</a>
								
								<c:if test="${currentPage < lastPage - 2 && currentPage > 3}">
									<c:forEach var="i" begin="${currentPage-2}" end="${currentPage+2}">
										<c:choose>
											<c:when test="${i eq currentPage}">
												 <a class="currentPage" href="${pageContext.request.contextPath}/auth/RentalServlet?currentPage=${i}">${i}</a>
											</c:when>
											
											<c:otherwise>
												<a href="${pageContext.request.contextPath}/auth/RentalServlet?currentPage=${i}">${i}</a>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</c:if>
								
								<c:if test="${currentPage <= 3}">
									<c:forEach var="i" begin="1" end="5">
										<c:choose>
											<c:when test="${i eq currentPage}">
												 <a class="currentPage" href="${pageContext.request.contextPath}/auth/RentalServlet?currentPage=${i}">${i}</a>
											</c:when>
											
											<c:otherwise>
												<a href="${pageContext.request.contextPath}/auth/RentalServlet?currentPage=${i}">${i}</a>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</c:if>
								
								<c:if test="${currentPage >= lastPage - 2}">
									<c:forEach var="i" begin="${lastPage-4}" end="${lastPage}">
										<c:choose>
											<c:when test="${i eq currentPage}">
												 <a class="currentPage" href="${pageContext.request.contextPath}/auth/RentalServlet?currentPage=${i}">${i}</a>
											</c:when>
											
											<c:otherwise>
												<a href="${pageContext.request.contextPath}/auth/RentalServlet?currentPage=${i}">${i}</a>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</c:if>
								
								<a>...</a>
								<a href="${pageContext.request.contextPath}/auth/RentalServlet?currentPage=${lastPage}">end</a>
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
</html>