package sakila.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sakila.dao.FilmDao;
import sakila.dao.RentalDao;
import sakila.service.FilmService;
import sakila.service.RentalService;
import sakila.vo.JoinToTable;

@WebServlet("/auth/promotionNotnullServlet")
public class PromotionNotnullServlet extends HttpServlet {
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
		
		Map<String, Object> map = filmService.getFilmPromotionNotnullList(currentPage, limitPage, searchTitle);
		List<JoinToTable> list = (List<JoinToTable>)map.get("list");
		int lastPage = (Integer)map.get("lastPage");
		
		// System.out.println(currentPage + ": 현재 패이지 확인");
		
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("lastPage", lastPage);
		request.setAttribute("searchTitle", searchTitle);
		request.setAttribute("list", list);
		request.getRequestDispatcher("/WEB-INF/auth/filmPromotion/filmPromotionNotnullList.jsp").forward(request, response);
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
		
		Map<String, Object> map = filmService.getFilmPromotionNotnullList(currentPage, limitPage, searchTitle);
		List<JoinToTable> list = (List<JoinToTable>)map.get("list");
		int lastPage = (Integer)map.get("lastPage");
		
		// System.out.println(currentPage + ": 현재 패이지 확인");
		
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("lastPage", lastPage);
		request.setAttribute("searchTitle", searchTitle);
		request.setAttribute("list", list);
		request.getRequestDispatcher("/WEB-INF/auth/filmPromotion/filmPromotionNotnullList.jsp").forward(request, response);
	}

}
