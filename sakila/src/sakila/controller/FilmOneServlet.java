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
import sakila.vo.Actor;
import sakila.vo.FilmList;
import sakila.vo.JoinToTable;


@WebServlet("/auth/filmOneServlet")
public class FilmOneServlet extends HttpServlet {
	private FilmService filmService;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		filmService = new FilmService(new FilmDao());
		
		int filmId = Integer.parseInt(request.getParameter("filmId"));
		
		Map<String, Object> map = filmService.getFilmListOne(filmId);
		
		JoinToTable filmList = (JoinToTable)map.get("filmList");
		List<Actor> actorList = (List<Actor>)map.get("actorList");
		
		request.setAttribute("filmList", filmList);
		request.setAttribute("actorList", actorList);
		request.getRequestDispatcher("/WEB-INF/auth/film/filmOne.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		filmService = new FilmService(new FilmDao());
		
		String description = request.getParameter("description");
		float rentalRate = Float.parseFloat(request.getParameter("rentalRate"));
		int filmId = Integer.parseInt(request.getParameter("filmId"));
		
		filmService.modifyFilm(description, rentalRate, filmId);
		
		response.sendRedirect("filmOneServlet?filmId=" + filmId);
	}

}
