package sakila.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sakila.dao.RentalDao;
import sakila.service.RentalService;
import sakila.vo.JoinToTable;


@WebServlet("/RentalServlet")
public class RentalServlet extends HttpServlet {
	private RentalService rentalService;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		rentalService = new RentalService(new RentalDao());
		List<JoinToTable> list = new ArrayList<JoinToTable>();
		
		list = rentalService.getFilmRentalList();
		request.setAttribute("list", list);
		
		request.getRequestDispatcher("/WEB-INF/auth/filmReturn/filmReturnList.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
