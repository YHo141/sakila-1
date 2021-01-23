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
import sakila.vo.Category;
import sakila.vo.JoinToTable;

@WebServlet("/auth/filmServlet")
public class FilmServlet extends HttpServlet {
	
	private FilmService filmService;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		filmService = new FilmService(new FilmDao());
		
		String searchTitle = "";
		String categoryName = "";
		int limitPage = 10;
		int currentPage = 1;
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		
		if(request.getParameter("categoryName") != null) {
			categoryName = request.getParameter("categoryName");
		}
		
		if(request.getParameter("searchTitle") != null) {
			searchTitle = request.getParameter("searchTitle");
		}
		
		Map<String, Object> map = filmService.getFilmList(searchTitle, categoryName, currentPage, limitPage);
		List<JoinToTable> list = (List<JoinToTable>)map.get("list");
		List<Category> categoryList = (List<Category>)map.get("categoryList");
		int lastPage = (Integer)map.get("lastPage");
		
		request.setAttribute("list", list);
		request.setAttribute("categoryList", categoryList);
		request.setAttribute("lastPage", lastPage);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("searchTitle", searchTitle);
		request.setAttribute("categoryName", categoryName);
		request.getRequestDispatcher("/WEB-INF/auth/film/filmList.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		filmService = new FilmService(new FilmDao());
		
		String searchTitle = "";
		String categoryName = "";
		int limitPage = 10;
		int currentPage = 1;
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		
		if(request.getParameter("categoryName") != null) {
			categoryName = request.getParameter("categoryName");
		}
		
		if(request.getParameter("searchTitle") != null) {
			searchTitle = request.getParameter("searchTitle");
		}
		
		Map<String, Object> map = filmService.getFilmList(searchTitle, categoryName, currentPage, limitPage);
		List<JoinToTable> list = (List<JoinToTable>)map.get("list");
		List<Category> categoryList = (List<Category>)map.get("categoryList");
		int lastPage = (Integer)map.get("lastPage");
		
		request.setAttribute("list", list);
		request.setAttribute("categoryList", categoryList);
		request.setAttribute("lastPage", lastPage);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("searchTitle", searchTitle);
		request.setAttribute("categoryName", categoryName);
		request.getRequestDispatcher("/WEB-INF/auth/film/filmList.jsp").forward(request, response);
	}
}
