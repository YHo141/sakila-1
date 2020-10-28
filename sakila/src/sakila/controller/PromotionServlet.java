package sakila.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sakila.dao.FilmDao;
import sakila.service.FilmService;
import sakila.vo.JoinToTable;

@WebServlet("/PromotionServlet")
public class PromotionServlet extends HttpServlet {
	private FilmService filmService;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<JoinToTable> list = new ArrayList<JoinToTable>();
		filmService = new FilmService(new FilmDao());
		
		list = filmService.getFilmPromotionList();
		request.setAttribute("list", list);
		
		request.getRequestDispatcher("/WEB-INF/auth/filmPromotion/filmPromotionList.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}
}
