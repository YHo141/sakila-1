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

import sakila.dao.FilmDao;
import sakila.service.FilmService;
import sakila.vo.JoinToTable;

@WebServlet("/auth/promotionServlet")
public class PromotionServlet extends HttpServlet {
	private FilmService filmService;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		filmService = new FilmService(new FilmDao());
		
		String searchTitle = "";
		int currentPage = 1;
		int limitPage = 10;
		
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		
		if(request.getParameter("searchTitle") != null) {
			searchTitle = request.getParameter("searchTitle");
		}
		
		Map<String, Object> map = filmService.getFilmPromotionList(currentPage, limitPage, searchTitle);
		List<JoinToTable> list = (List)map.get("list");
		int lastPage = (Integer)map.get("lastPage");
		
		request.setAttribute("list", list);
		request.setAttribute("lastPage", lastPage);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("searchTitle", searchTitle);
		
		request.getRequestDispatcher("/WEB-INF/auth/filmPromotion/filmPromotionList.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		filmService = new FilmService(new FilmDao());
		
		String searchTitle = "";
		int currentPage = 1;
		int limitPage = 10;
		
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		
		if(request.getParameter("searchTitle") != null) {
			searchTitle = request.getParameter("searchTitle");
		}
		
		Map<String, Object> map = filmService.getFilmPromotionList(currentPage, limitPage, searchTitle);
		List<JoinToTable> list = (List)map.get("list");
		int lastPage = (Integer)map.get("lastPage");
		
		request.setAttribute("list", list);
		request.setAttribute("lastPage", lastPage);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("searchTitle", searchTitle);
		
		request.getRequestDispatcher("/WEB-INF/auth/filmPromotion/filmPromotionList.jsp").forward(request, response);
	}
}
