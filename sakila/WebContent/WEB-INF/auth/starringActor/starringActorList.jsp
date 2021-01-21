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
							<h2>Starring Actor</h2>
							
							<div id="contentSearch">
								<form method="post" action="${path}/auth/starringActorServlet">
									<select name="selectOption" id="selectOption">
										<c:if test="${selectOption != ''}">
											<option value="${selectOption}">${selectOption}</option>										
										</c:if>
										<option value="">전체</option>
										<c:forEach var="c" items="${categoryList}">
											<c:if test="${selectOption != c.name}">
												<option value="${c.name}">${c.name}</option>
											</c:if>
										</c:forEach>
									</select>
									<input name="searchTitle" value="${searchTitle}" type="text" placeholder=" Please Enter film_title">
									<button type="submit">검색</button>
								</form>
							</div>
							<table id="contentTable">
								<thead>	
									<tr>
										<th>카테고리</th>
										<th>영화 제목</th>
										<th>선택</th>
									</tr>
								</thead>
								
								<tbody>
									<c:forEach var="b" items="${list}">
										<tr>
											<td style="width:30%;">${b.category.name}</td>
											<td style="width:50%;">${b.film.title}</td>
											<td style="width:20%;"><button onclick="clickOne('${b.film.filmId}');" type="button" style="color:#6b6b6b;width:100%;height:28px;padding:6px">선택</button></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
							<div id="paging">	
								<a href="${path}/auth/starringActorServlet?currentPage=${1}&searchTitle=${searchTitle}">1</a>
								<a>...</a>
								<c:choose>
									<c:when test="${lastPage < 4 }">
										<c:forEach var="i" begin="${1}" end="${lastPage}">
											<c:choose>
												<c:when test="${i eq currentPage}">
													<a class="currentPage" href="${path}/auth/starringActorServlet?currentPage=${i}&searchTitle=${searchTitle}">${i}</a>	
												</c:when>
												<c:otherwise>
													<a href="${path}/auth/starringActorServlet?currentPage=${i}&searchTitle=${searchTitle}">${i}</a>	
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
															<a class="currentPage" href="${path}/auth/starringActorServlet?currentPage=${i}&searchTitle=${searchTitle}">${i}</a>	
														</c:when>
														<c:otherwise>
															<a href="${path}/auth/starringActorServlet?currentPage=${i}&searchTitle=${searchTitle}">${i}</a>	
														</c:otherwise>
													</c:choose>
												</c:forEach>
											</c:when>
											<c:when test="${currentPage > lastPage-3}">
												<c:forEach var="i" begin="${lastPage-4}" end="${lastPage}">
													<c:choose>
														<c:when test="${i eq currentPage}">
															<a class="currentPage" href="${path}/auth/starringActorServlet?currentPage=${i}&searchTitle=${searchTitle}">${i}</a>	
														</c:when>
														<c:otherwise>
															<a href="${path}/auth/starringActorServlet?currentPage=${i}&searchTitle=${searchTitle}">${i}</a>	
														</c:otherwise>
													</c:choose>
												</c:forEach>
											</c:when>
											<c:otherwise>
												<c:forEach var="i" begin="${currentPage-2}" end="${currentPage+2}">
													<c:choose>
														<c:when test="${i eq currentPage}">
															<a class="currentPage" href="${path}/auth/starringActorServlet?currentPage=${i}&searchTitle=${searchTitle}">${i}</a>	
														</c:when>
														<c:otherwise>
															<a href="${path}/auth/starringActorServlet?currentPage=${i}&searchTitle=${searchTitle}">${i}</a>	
														</c:otherwise>
													</c:choose>
												</c:forEach>
											</c:otherwise>
										</c:choose>
									</c:otherwise>
								</c:choose>
								<a>...</a>
								<a href="${path}/auth/starringActorServlet?currentPage=${lastPage}&searchTitle=${searchTitle}">end</a>		
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
    	function clickOne(filmId){
    		location.href='${path}/auth/starringActorOneServlet?filmId=' + filmId;
    	}
    </script>
</html>


