package sakila.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sakila.dao.FilmDao;
import sakila.service.FilmService;

@WebServlet("/auth/promotionReturnServlet")
public class PromotionReturnServlet extends HttpServlet {
	private FilmService filmService;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		filmService = new FilmService(new FilmDao());
		
		int filmId = Integer.parseInt(request.getParameter("filmId"));
		int inventoryId = Integer.parseInt(request.getParameter("inventoryId"));
		
		filmService.modifyFilmPromotionByReturn(inventoryId);
		
		response.sendRedirect("promotionOneServlet?filmId=" + filmId);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
