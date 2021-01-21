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
						<h2>Starring Actor One</h2>
						<form id="formModify" method="post" action="${path}/auth/starringActorOneServlet">
							<table id="addTable">
								<tr>
									<th>카테고리</th>
									<td>${list[0].category.name}</td>
								</tr>
								<tr>
									<th>영화 제목</th>
									<td>${list[0].film.title}</td>
								</tr>
								<tr>
									<th>출연 배우</th>
									<td>
										<c:forEach var="l" items="${list}">
											${l.actor.lastName},
										</c:forEach>
									</td>
								</tr>
							</table>
							<div id="actorView" style="height:500px; overflow:scroll; margin-top:20px;">
								<table id="contentTable">
									<c:forEach var="a" items="${actorList}">
										<tr>
											<td>${a.lastName}<td>
											<td>
												<button id="btnName${a.actorId}" onclick="clickName('${a.actorId}');" type="button" style="color:#6b6b6b;width:100%;height:28px;padding:6px">선택</button>
												<input type="hidden" id="name${a.actorId}" value="no">
											</td>
										</tr>	
									</c:forEach>
								</table>
							</div>
							<div id="contentSearch">
								<button id="btnResult" type="button" style="width:15%;">배우 추가</button>
							</div>
						</form>
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
    	var list = new Array();
    
    	function clickName(actorId){
    		if($('#name' + actorId).val() == 'no'){
    			$('#btnName' + actorId).css('background-color', '#000000')
    								   .css('color', '#FFFFFF');	
    			$('#name' + actorId).attr('value','yes');
    			list.push(actorId);
    		} else if($('#name' + actorId).val() == 'yes'){
    			$('#btnName' + actorId).css('background-color', '#EAEAEA')
				   					   .css('color', '#6b6b6b');
    			$('#name' + actorId).attr('value','no');
    			list = jQuery.grep(list, function(value) {
    				return value != actorId;
    			});
    		}
    	}
    	
    	$('#btnResult').click(function(){
    		location.href='${path}/auth/starringActorOneAddServlet?list=' + list + '&filmId=${filmId}';
    	});
    </script>
</html>