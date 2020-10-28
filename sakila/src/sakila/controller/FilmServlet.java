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

@WebServlet("/auth/FilmServlet")
public class FilmServlet extends HttpServlet {
	
	private FilmService filmService;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		filmService = new FilmService(new FilmDao());
		List<JoinToTable> list = new ArrayList<JoinToTable>();
		
		list = filmService.getFilmList();
		
		request.setAttribute("list", list);
		request.getRequestDispatcher("/WEB-INF/auth/film/filmList.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
}
