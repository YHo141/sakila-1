package sakila.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sakila.dao.ActorDao;
import sakila.service.ActorService;

@WebServlet("/auth/actorAddServlet")
public class ActorAddServlet extends HttpServlet {
	private ActorService actorService;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/auth/actor/actorAdd.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		actorService = new ActorService(new ActorDao());
		
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		
		actorService.addActor(firstName, lastName);
		
		response.sendRedirect("actorServlet");
	}

}
