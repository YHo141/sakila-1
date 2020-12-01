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
							<h2>Film Return</h2>
							
							<div id="contentSearch">
								<form method="post" action="${path}/auth/RentalServlet">
									<input name="searchTitle" value="${searchTitle}" type="text" placeholder=" Please Enter film_title">
									<button type="submit">검색</button>
								</form>
							</div>
							<form method="post" action="${path}/auth/RentalReturnServlet">
								<table id="contentTable">
									<thead>	
										<tr>
											<th>rental_id</th>
											<th>film_title</th>
											<th>대여일</th>
											<th>반납예정일</th>
											<th>반납일</th>
											<th>반납</th>
										</tr>
									</thead>
									
									<tbody>
										<c:forEach var="b" items="${list}">
											<tr>
												<td>
													<input type="hidden" name="rentalId" value="${b.rental.rentalId}">
													${b.rental.rentalId}
												</td>
												<td>${b.film.title}</td>
												<td>${b.film.rentalDuration}</td>
												<td>${b.rental.rentalDate}</td>
												<td>${b.rental.returnDate}</td>
												<td>
													<c:if test="${b.rental.returnDate eq null}">
														<button class="green" type="submit">반납</button>
													</c:if>
													<c:if test="${b.rental.returnDate ne null}">
														<button class="black" type="submit">반납 완료</button>
													</c:if>
												</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</form>
							
							<div id="paging">	
								<a href="${path}/auth/RentalServlet?currentPage=${1}&searchTitle=${searchTitle}">1</a>
								<a>...</a>
								<c:choose>
									<c:when test="${lastPage < 4 }">
										<c:forEach var="i" begin="${1}" end="${lastPage}">
											<c:choose>
												<c:when test="${i eq currentPage}">
													<a class="currentPage" href="${path}/auth/RentalServlet?currentPage=${i}&searchTitle=${searchTitle}">${i}</a>	
												</c:when>
												<c:otherwise>
													<a href="${path}/auth/RentalServlet?currentPage=${i}&searchTitle=${searchTitle}">${i}</a>	
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
															<a class="currentPage" href="${path}/auth/RentalServlet?currentPage=${i}&searchTitle=${searchTitle}">${i}</a>	
														</c:when>
														<c:otherwise>
															<a href="${path}/auth/RentalServlet?currentPage=${i}&searchTitle=${searchTitle}">${i}</a>	
														</c:otherwise>
													</c:choose>
												</c:forEach>
											</c:when>
											<c:when test="${currentPage > lastPage-3}">
												<c:forEach var="i" begin="${lastPage-4}" end="${lastPage}">
													<c:choose>
														<c:when test="${i eq currentPage}">
															<a class="currentPage" href="${path}/auth/RentalServlet?currentPage=${i}&searchTitle=${searchTitle}">${i}</a>	
														</c:when>
														<c:otherwise>
															<a href="${path}/auth/RentalServlet?currentPage=${i}&searchTitle=${searchTitle}">${i}</a>	
														</c:otherwise>
													</c:choose>
												</c:forEach>
											</c:when>
											<c:otherwise>
												<c:forEach var="i" begin="${currentPage-2}" end="${currentPage+2}">
													<c:choose>
														<c:when test="${i eq currentPage}">
															<a class="currentPage" href="${path}/auth/RentalServlet?currentPage=${i}&searchTitle=${searchTitle}">${i}</a>	
														</c:when>
														<c:otherwise>
															<a href="${path}/auth/RentalServlet?currentPage=${i}&searchTitle=${searchTitle}">${i}</a>	
														</c:otherwise>
													</c:choose>
												</c:forEach>
											</c:otherwise>
										</c:choose>
									</c:otherwise>
								</c:choose>
								<a>...</a>
								<a href="${path}/auth/RentalServlet?currentPage=${lastPage}&searchTitle=${searchTitle}">end</a>		
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