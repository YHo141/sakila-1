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
import sakila.service.FilmService;
import sakila.vo.Language;
import sakila.vo.Category;

@WebServlet("/auth/filmAddServlet")
public class FilmAddServlet extends HttpServlet {
	private FilmService filmService;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		filmService = new FilmService(new FilmDao());
		
		Map<String, Object> map = filmService.getFilmAdd();
		List<Language> languageList = (List<Language>)map.get("languageList");
		List<Category> categoryList = (List<Category>)map.get("categoryList");
		
		request.setAttribute("languageList", languageList);
		request.setAttribute("categoryList", categoryList);
		request.getRequestDispatcher("/WEB-INF/auth/film/filmAdd.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		filmService = new FilmService(new FilmDao());
		
		String title = request.getParameter("title");
		String description = request.getParameter("description");
		int languageId = Integer.parseInt(request.getParameter("languageId"));
		int categoryId = Integer.parseInt(request.getParameter("categoryId"));
		float rentalRate = Float.parseFloat(request.getParameter("rentalRate"));
		int length = Integer.parseInt(request.getParameter("length"));
		String rating = request.getParameter("rating");
		
		filmService.addFilmAction(title, description, languageId, categoryId, rentalRate, length, rating);
		
		response.sendRedirect("filmServlet");
	}

}
