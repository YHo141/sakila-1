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
        <style>
        	#contentTable tbody button:hover{
        		background-color: #F6F6F6;
        	}
        </style>
    </head>
    <body>
		<div id="bg">
			<div id="outer">
				<div id="main">
				
					<jsp:include page="/inc/menu.jsp"></jsp:include>
					
					<div id="content">
							<h2>Actor List</h2>
							
							<div id="contentSearch2">
								<button id="btnAdd" type="submit">신규 등록</button>
							</div>
							
							<div id="contentSearch">
								<form method="post" action="${path}/auth/actorServlet">
									<input name="searchTitle" value="${searchTitle}" type="text" placeholder=" Please Enter actor_name">
									<button type="submit">검색</button>
								</form>
							</div>
							<table id="contentTable">
								<thead>
									<tr>
										<th>번호</th>
										<th>배우 성</th>
										<th>배우 이름</th>
									</tr>
								</thead>
								
								<tbody>
									<c:if test="${fn:length(list) != 0}">
										<c:forEach var="b" items="${list}">
											<tr>
												<td style="width:20%"><button onclick="clickOne('${b.actorId}');" type="button" style="color:#6b6b6b;width:100%;height:28px;padding:6px">${b.actorId}</button></td>
												<td style="width:40%">${b.firstName}</td>
												<td style="width:40%">${b.lastName}</td>
											</tr>
										</c:forEach>
									</c:if>
									<c:if test="${fn:length(list) == 0}">
										<tr>
											<td colspan="3">해당하는 데이터가 없습니다.</td>
										</tr>
									</c:if>
								</tbody>
							</table>
							<div id="paging">	
								<a href="${path}/auth/actorServlet?currentPage=${1}&searchTitle=${searchTitle}">1</a>
								<a>...</a>
								<c:choose>
									<c:when test="${lastPage < 4 }">
										<c:forEach var="i" begin="${1}" end="${lastPage}">
											<c:choose>
												<c:when test="${i eq currentPage}">
													<a class="currentPage" href="${path}/auth/actorServlet?currentPage=${i}&searchTitle=${searchTitle}">${i}</a>	
												</c:when>
												<c:otherwise>
													<a href="${path}/auth/actorServlet?currentPage=${i}&searchTitle=${searchTitle}">${i}</a>	
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
															<a class="currentPage" href="${path}/auth/actorServlet?currentPage=${i}&searchTitle=${searchTitle}">${i}</a>	
														</c:when>
														<c:otherwise>
															<a href="${path}/auth/actorServlet?currentPage=${i}&searchTitle=${searchTitle}">${i}</a>	
														</c:otherwise>
													</c:choose>
												</c:forEach>
											</c:when>
											<c:when test="${currentPage > lastPage-3}">
												<c:forEach var="i" begin="${lastPage-4}" end="${lastPage}">
													<c:choose>
														<c:when test="${i eq currentPage}">
															<a class="currentPage" href="${path}/auth/actorServlet?currentPage=${i}&searchTitle=${searchTitle}">${i}</a>	
														</c:when>
														<c:otherwise>
															<a href="${path}/auth/actorServlet?currentPage=${i}&searchTitle=${searchTitle}">${i}</a>	
														</c:otherwise>
													</c:choose>
												</c:forEach>
											</c:when>
											<c:otherwise>
												<c:forEach var="i" begin="${currentPage-2}" end="${currentPage+2}">
													<c:choose>
														<c:when test="${i eq currentPage}">
															<a class="currentPage" href="${path}/auth/actorServlet?currentPage=${i}&searchTitle=${searchTitle}">${i}</a>	
														</c:when>
														<c:otherwise>
															<a href="${path}/auth/actorServlet?currentPage=${i}&searchTitle=${searchTitle}">${i}</a>	
														</c:otherwise>
													</c:choose>
												</c:forEach>
											</c:otherwise>
										</c:choose>
									</c:otherwise>
								</c:choose>
								<a>...</a>
								<a href="${path}/auth/actorServlet?currentPage=${lastPage}&searchTitle=${searchTitle}">end</a>		
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
    	function clickOne(actorId){
    		location.href = '${path}/auth/actorOneServlet?actorId=' + actorId;
    	}
    
    	$('#btnAdd').click(function(){
    		location.href = '${path}/auth/actorAddServlet';
    	});
    </script>
</html>