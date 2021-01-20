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
import sakila.vo.JoinToTable;

@WebServlet("/auth/actorOneServlet")
public class ActorOneServlet extends HttpServlet {
	private ActorService actorService;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		actorService = new ActorService(new ActorDao());
		
		int actorId = Integer.parseInt(request.getParameter("actorId"));
		
		List<JoinToTable> list = actorService.getActorOne(actorId);
		
		System.out.println(list);
		
		request.setAttribute("list", list);
		
		request.getRequestDispatcher("/WEB-INF/auth/actor/actorOne.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException ,IOException {
		actorService = new ActorService(new ActorDao());
		
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		int actorId = Integer.parseInt(request.getParameter("actorId"));
		
		actorService.modifyActor(firstName, lastName, actorId);
		
		response.sendRedirect("actorOneServlet?actorId=" + actorId);
	}
}
