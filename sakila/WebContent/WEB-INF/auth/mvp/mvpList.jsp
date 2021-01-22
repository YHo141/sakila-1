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
							<h2>MVP</h2>
							<p style="margin-top:-30px">MVP기준은 액수 150$ 초과입니다 </p>
							
							<table id="contentTable">
								<thead>
									<tr>
										<th>랭킹</th>
										<th>고객명</th>
										<th>액수($)</th>
									</tr>
								</thead>
								
								<tbody>
									<c:if test="${fn:length(list) != 0}">
										<c:forEach var="l" items="${list}" varStatus="status">
											<tr>
												<td style="width:20%"><c:out value="${status.index + 1 + (currentPage-1) * 10}" /></td>
												<td style="width:40%">${l.customer.lastName}</td>
												<td style="width:40%">${l.payment.amount}</td>
											</tr>
										</c:forEach>
									</c:if>
									<c:if test="${fn:length(list) == 0}">
										<tr>
											<td colspan="2">해당하는 데이터가 없습니다.</td>
										</tr>
									</c:if>
								</tbody>
							</table>
							<div id="paging">	
								<a href="${path}/auth/mvpServlet?currentPage=${1}">1</a>
								<a>...</a>
								<c:choose>
									<c:when test="${lastPage < 4 }">
										<c:forEach var="i" begin="${1}" end="${lastPage}">
											<c:choose>
												<c:when test="${i eq currentPage}">
													<a class="currentPage" href="${path}/auth/mvpServlet?currentPage=${i}">${i}</a>	
												</c:when>
												<c:otherwise>
													<a href="${path}/auth/mvpServlet?currentPage=${i}">${i}</a>	
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
															<a class="currentPage" href="${path}/auth/mvpServlet?currentPage=${i}">${i}</a>	
														</c:when>
														<c:otherwise>
															<a href="${path}/auth/mvpServlet?currentPage=${i}">${i}</a>	
														</c:otherwise>
													</c:choose>
												</c:forEach>
											</c:when>
											<c:when test="${currentPage > lastPage-3}">
												<c:forEach var="i" begin="${lastPage-4}" end="${lastPage}">
													<c:choose>
														<c:when test="${i eq currentPage}">
															<a class="currentPage" href="${path}/auth/mvpServlet?currentPage=${i}">${i}</a>	
														</c:when>
														<c:otherwise>
															<a href="${path}/auth/mvpServlet?currentPage=${i}">${i}</a>	
														</c:otherwise>
													</c:choose>
												</c:forEach>
											</c:when>
											<c:otherwise>
												<c:forEach var="i" begin="${currentPage-2}" end="${currentPage+2}">
													<c:choose>
														<c:when test="${i eq currentPage}">
															<a class="currentPage" href="${path}/auth/mvpServlet?currentPage=${i}">${i}</a>	
														</c:when>
														<c:otherwise>
															<a href="${path}/auth/mvpServlet?currentPage=${i}">${i}</a>	
														</c:otherwise>
													</c:choose>
												</c:forEach>
											</c:otherwise>
										</c:choose>
									</c:otherwise>
								</c:choose>
								<a>...</a>
								<a href="${path}/auth/mvpServlet?currentPage=${lastPage}">end</a>		
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