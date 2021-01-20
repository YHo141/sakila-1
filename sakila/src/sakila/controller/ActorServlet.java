package sakila.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sakila.dao.ActorDao;
import sakila.service.ActorService;
import sakila.vo.Actor;
import sakila.vo.JoinToTable;

@WebServlet("/auth/actorServlet")
public class ActorServlet extends HttpServlet {
	private ActorService actorService;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		actorService = new ActorService(new ActorDao());
		
		String searchTitle = "";
		int currentPage = 1;
		int limitPage = 10;
		
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		
		if(request.getParameter("searchTitle") != null) {
			searchTitle = request.getParameter("searchTitle");
		}
		
		Map<String, Object> map = actorService.getActorList(currentPage, limitPage, searchTitle);
		List<Actor> list = (List<Actor>)map.get("list");
		int lastPage = (Integer)map.get("lastPage");
		
		// System.out.println(currentPage + ": 현재 패이지 확인");
		
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("lastPage", lastPage);
		request.setAttribute("searchTitle", searchTitle);
		request.setAttribute("list", list);
		request.getRequestDispatcher("/WEB-INF/auth/actor/actorList.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		actorService = new ActorService(new ActorDao());
		
		String searchTitle = "";
		int currentPage = 1;
		int limitPage = 10;
		
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		
		if(request.getParameter("searchTitle") != null) {
			searchTitle = request.getParameter("searchTitle");
		}
		
		Map<String, Object> map = actorService.getActorList(currentPage, limitPage, searchTitle);
		List<Actor> list = (List<Actor>)map.get("list");
		int lastPage = (Integer)map.get("lastPage");
		
		// System.out.println(currentPage + ": 현재 패이지 확인");
		
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("lastPage", lastPage);
		request.setAttribute("searchTitle", searchTitle);
		request.setAttribute("list", list);
		request.getRequestDispatcher("/WEB-INF/auth/actor/actorList.jsp").forward(request, response);
	}
}
