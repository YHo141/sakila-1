package sakila.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sakila.dao.RentalDao;
import sakila.service.RentalService;

@WebServlet("/auth/RentalReturnServlet")
public class RentalReturnServlet extends HttpServlet {
	private RentalService rentalService;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		rentalService = new RentalService(new RentalDao());
		
		int rentalId = Integer.parseInt(request.getParameter("rentalId"));
		
		rentalService.getUpdateRentalReturnDate(rentalId);
		
		response.sendRedirect(request.getContextPath()+"/auth/RentalServlet");
	}

}
