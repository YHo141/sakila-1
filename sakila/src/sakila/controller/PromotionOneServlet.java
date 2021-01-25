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
import sakila.vo.Rental;

@WebServlet("/auth/promotionOneServlet")
public class PromotionOneServlet extends HttpServlet {
	private FilmService filmService;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		filmService = new FilmService(new FilmDao());
		
		int filmId = Integer.parseInt(request.getParameter("filmId"));
		
		Map<String, Object> map = filmService.getFilmPromotionOne(filmId);
		List<Rental> list = (List<Rental>)map.get("list");
		int allPromotion = (Integer)map.get("allPromotion");
		int returnPromotion = (Integer)map.get("returnPromotion");
		
		request.setAttribute("list", list);
		request.setAttribute("allPromotion", allPromotion);
		request.setAttribute("returnPromotion", returnPromotion);
		request.setAttribute("filmId", filmId);
		request.setAttribute("title", list.get(0).getArrears());
		request.getRequestDispatcher("/WEB-INF/auth/filmPromotion/filmPromotionOne.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
