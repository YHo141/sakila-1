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
    </head>
    <body>
		<div id="bg">
			<div id="outer">
				<div id="main">
					<jsp:include page="/inc/menu.jsp"></jsp:include>
					
					<div id="content">
							<h2>index</h2>
							
							<table id="addTable">
								<tr>
									<th>제작자</th>
									<td>이병윤 (https://github.com/byoungyoon/sakila.git)</td>
								</tr>
								<tr>
									<th>제작 기간</th>
									<td>20.10.03 ~ 20.10.27 / 21.01.23 ~ 21.01.25 <br> (첫 번째 기간에는 배운것을 토대로 만들었고 두번째 기간에는 더 배운 것을 바탕으로 프로젝트를 리펙토링 하였다.)</td>
								</tr>
								<tr>
									<th>사용 방법</th>
									<td>model2 방식의 MVC 구조</td>
								</tr>
								<tr>
									<th>사용 툴</th>
									<td>Mariadb, eclipse, Tomcat</td>
								</tr>
								<tr>
									<th>서버</th>
									<td>AWS EC2 사용(http://legend-by.kro.kr/sakila/LoginServlet)</td>
								</tr>
								<tr>
									<th>프로젝트 설명</th>
									<td>mySql 사이트에서 제공하는 database인 sakila를 이용하여 만든 비디오 대여점 프로젝트</td>
								</tr>
								<tr>
									<th>프로젝트 구조</th>
									<td>
										크게 총 8개의 파트로 이루어진다<br>
										<li>
											영화 재고 : 영화 재고 파트에선 영화 제목에 따라 검색을 할 수 있고, 상세보기에서 영화 반납/대여를 할 수 있다.
										</li>
										<li>
											회원 목록 : 회원 목록 파트에선 회원들의 활성화 여부를 알 수 있고, 그 회원이 비디오를 대여 하였는지 연체중이라면 연체비용이 얼마인지를 확인을 할 수 있다.
										</li>
										<li>
											영화 목록 : 영화 목록 파트에선 그 영화의 대여 비용과 영화의 정보를 상세보기 할 수 있다.
										</li>
										<li>
											영화 배우 : 영화 배우 파트에선 전체 배우 목록을 조회 할 수 있고, 그 배우의 상세보기에서 그 배우가 등장하는 작품을 표현 되도록 구현했다.
										</li>
										<li>
											영화 출연배우 등록 : 영화 출연 배우 등록 파트에선 영화 별로 테이블을 조회하고, 상세보기에서 그 영화를 기준으로 배우를 동적으로 추가 할 수 있다.
										</li>
										<li>
											매장 통계 : 매장 통계 파트에선 현재 매장의 수익과 대여 수 그리고 카테고리별 매출액을 볼 수 있다.
										</li>
										<li>
											MVP : MVP 파트에선 150불 이상 사용 한 고객에 대해서 mvp를 추가하고 그것을 조회 할 수 있다.
										</li>
										<li>
											마이페이지 : 접속중인 staff에 대해서 정보를 볼 수 있다.
										</li>
									</td>
								</tr>
							</table>
								
							
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