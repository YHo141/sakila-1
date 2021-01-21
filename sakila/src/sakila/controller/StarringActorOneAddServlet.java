package sakila.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sakila.dao.ActorDao;
import sakila.service.ActorService;

@WebServlet("/auth/starringActorOneAddServlet")
public class StarringActorOneAddServlet extends HttpServlet {
	private ActorService actorService;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		actorService = new ActorService(new ActorDao());
		
		String actorId = request.getParameter("list");
		int filmId = Integer.parseInt(request.getParameter("filmId"));
		
		actorService.addActorByFilm(actorId, filmId);
		
		response.sendRedirect("starringActorOneServlet?filmId=" + filmId);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
