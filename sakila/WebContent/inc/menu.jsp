<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<div id="sidebar">
						<h3>
							Sakila Movie
						</h3>
						
						<table id="staff">
							<tr>
								<td rowspan="2"><a href="/sakila/auth/StaffServlet" class='fas fa-user-circle' style='font-size:60px'></a></td>
								<td>서울 지점</td>
							</tr>
							
							<tr>
								<td>*** 관리자님</td>
							</tr>
						</table>
						
						<div>
							<button type="button" id="logout">logout</button>
						</div>
						
						<h3>
							Menu
						</h3>
						
						<ul class="linkedList">
							<li class="line">
								<a href="${pageContext.request.contextPath}/auth/IndexServlet">홈</a>
							</li>
							<li>
								<a href="${pageContext.request.contextPath}/auth/RentalServlet">영화 반납</a>
							</li>
							<li class="line">
								<a href="${pageContext.request.contextPath}/auth/CustomerServlet">회원목록 관리</a>
							</li>
							<li>
								<a href="${pageContext.request.contextPath}/auth/FilmServlet">영화목록 관리</a>
							</li>
							<li>
								<a href="${pageContext.request.contextPath}/auth/promotionServlet">영화재고 관리</a>
							</li>
							<li>
								<a href="${pageContext.request.contextPath}/auth/actorServlet">영화배우 관리</a>
							</li>
							<li>
								<a href="${pageContext.request.contextPath}/auth/starringActorServlet">영화 출연배우 등록</a>
							</li>
							<li class="line">
								<a href="#">매장 통계</a>
							</li>
							<li class="last">
								<a href="#">MVP</a>
							</li>
						</ul>
					</div>