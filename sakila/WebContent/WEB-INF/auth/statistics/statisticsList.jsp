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
						<h2>Statistics</h2>
						<div>
							<h3>Total store sales</h3>
						</div>
						<div>
							<input type="text" value="${storeSum}" readonly="readonly" style="width:35%;padding:7px;margin-bottom:30px; font-size:1.3em; background-color:#BDBDBD;outline:0;border:0;">
						</div>
						<div>
							<h3>Total number of rentals</h3>
						</div>
						<div>
							<input type="text" value="${storeRentalSum}" readonly="readonly" style="width:35%;padding:7px;margin-bottom:30px; font-size:1.3em; background-color:#BDBDBD;outline:0;border:0;">
						</div>
						<h2></h2>
						<h3>Sales by category</h3>
						<table id="contentTable">
							<thead>
								<tr>
									<th>카테고리</th>
									<th>매출액</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="l" items="${list}">
									<tr>
										<td>${l.category}</td>
										<td>${l.totalSales}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<div id="paging">	
							<a href="${path}/auth/statisticsServlet?currentPage=${1}&staffId=${loginStaff}">1</a>
							<a>...</a>
							<c:choose>
								<c:when test="${lastPage < 5 }">
									<c:forEach var="i" begin="${1}" end="${lastPage}">
										<c:choose>
											<c:when test="${i eq currentPage}">
												<a class="currentPage" href="${path}/auth/statisticsServlet?currentPage=${i}&staffId=${loginStaff}">${i}</a>	
											</c:when>
											<c:otherwise>
												<a href="${path}/auth/statisticsServlet?currentPage=${i}&staffId=${loginStaff}">${i}</a>	
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
														<a class="currentPage" href="${path}/auth/statisticsServlet?currentPage=${i}&staffId=${loginStaff}">${i}</a>	
													</c:when>
													<c:otherwise>
														<a href="${path}/auth/statisticsServlet?currentPage=${i}&staffId=${loginStaff}">${i}</a>	
													</c:otherwise>
												</c:choose>
											</c:forEach>
										</c:when>
										<c:when test="${currentPage > lastPage-3}">
											<c:forEach var="i" begin="${lastPage-4}" end="${lastPage}">
												<c:choose>
													<c:when test="${i eq currentPage}">
														<a class="currentPage" href="${path}/auth/statisticsServlet?currentPage=${i}&staffId=${loginStaff}">${i}</a>	
													</c:when>
													<c:otherwise>
														<a href="${path}/auth/statisticsServlet?currentPage=${i}&staffId=${loginStaff}">${i}</a>	
													</c:otherwise>
												</c:choose>
											</c:forEach>
										</c:when>
										<c:otherwise>
											<c:forEach var="i" begin="${currentPage-2}" end="${currentPage+2}">
												<c:choose>
													<c:when test="${i eq currentPage}">
														<a class="currentPage" href="${path}/auth/statisticsServlet?currentPage=${i}&staffId=${loginStaff}">${i}</a>	
													</c:when>
													<c:otherwise>
														<a href="${path}/auth/statisticsServlet?currentPage=${i}&staffId=${loginStaff}">${i}</a>	
													</c:otherwise>
												</c:choose>
											</c:forEach>
										</c:otherwise>
									</c:choose>
								</c:otherwise>
							</c:choose>
							<a>...</a>
							<a href="${path}/auth/statisticsServlet?currentPage=${lastPage}&staffId=${loginStaff}">end</a>		
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
</html>