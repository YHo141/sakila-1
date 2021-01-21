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
import sakila.vo.Actor;
import sakila.vo.JoinToTable;

@WebServlet("/auth/starringActorOneServlet")
public class StarringActorOneServlet extends HttpServlet {
	private ActorService actorService;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		actorService = new ActorService(new ActorDao());
		
		int filmId = Integer.parseInt(request.getParameter("filmId"));
		List<JoinToTable> list = actorService.getStarringActorOne(filmId);
		List<Actor> actorList = actorService.getStarringActorNotSelect(filmId);
		
		request.setAttribute("list", list);
		request.setAttribute("actorList", actorList);
		request.setAttribute("filmId", filmId);
		
		request.getRequestDispatcher("/WEB-INF/auth/starringActor/starringActorOne.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
