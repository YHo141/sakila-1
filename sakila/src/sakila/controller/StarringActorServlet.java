package sakila.controller;

import java.io.IOException;
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
import sakila.vo.Category;
import sakila.vo.JoinToTable;

@WebServlet("/auth/starringActorServlet")
public class StarringActorServlet extends HttpServlet {
	private ActorService actorService;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		actorService = new ActorService(new ActorDao());
		
		String searchTitle = "";
		String selectOption = "";
		int currentPage = 1;
		int limitPage = 10;
		
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		
		if(request.getParameter("searchTitle") != null) {
			searchTitle = request.getParameter("searchTitle");
		}
		
		if(request.getParameter("selectOption") != null) {
			selectOption = request.getParameter("selectOption");
		}
		
		Map<String, Object> map = actorService.getStarringActorList(currentPage, limitPage, searchTitle, selectOption);
		List<JoinToTable> list = (List<JoinToTable>)map.get("list");
		List<Category> categoryList = actorService.getCategory();
		
		int lastPage = (Integer)map.get("lastPage");
		
		// System.out.println(currentPage + ": 현재 패이지 확인");
		
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("lastPage", lastPage);
		request.setAttribute("searchTitle", searchTitle);
		request.setAttribute("selectOption", selectOption);
		request.setAttribute("list", list);
		request.setAttribute("categoryList", categoryList);
		request.getRequestDispatcher("/WEB-INF/auth/starringActor/starringActorList.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		actorService = new ActorService(new ActorDao());
		
		String searchTitle = "";
		String selectOption = "";
		int currentPage = 1;
		int limitPage = 10;
		
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		
		if(request.getParameter("searchTitle") != null) {
			searchTitle = request.getParameter("searchTitle");
		}
		
		if(request.getParameter("selectOption") != null) {
			selectOption = request.getParameter("selectOption");
		}
		
		Map<String, Object> map = actorService.getStarringActorList(currentPage, limitPage, searchTitle, selectOption);
		List<JoinToTable> list = (List<JoinToTable>)map.get("list");
		List<Category> categoryList = actorService.getCategory();
		
		int lastPage = (Integer)map.get("lastPage");
		
		// System.out.println(currentPage + ": 현재 패이지 확인");
		
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("lastPage", lastPage);
		request.setAttribute("searchTitle", searchTitle);
		request.setAttribute("selectOption", selectOption);
		request.setAttribute("list", list);
		request.setAttribute("categoryList", categoryList);
		request.getRequestDispatcher("/WEB-INF/auth/starringActor/starringActorList.jsp").forward(request, response);
	}

}
