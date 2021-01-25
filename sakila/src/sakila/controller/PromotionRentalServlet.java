package sakila.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sakila.dao.CustomerDao;
import sakila.dao.FilmDao;
import sakila.service.CustomerService;
import sakila.service.FilmService;
import sakila.vo.Customer;

@WebServlet("/auth/promotionRentalServlet")
public class PromotionRentalServlet extends HttpServlet {
	private FilmService filmService;
	private CustomerService customerService;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		filmService = new FilmService(new FilmDao());
		customerService = new CustomerService(new CustomerDao());
		int filmId = Integer.parseInt(request.getParameter("filmId"));
		int inventoryId = Integer.parseInt(request.getParameter("inventoryId"));
		
		String searchName = "";
		int currentPage = 1;
		int limitPage = 7;
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		String title = request.getParameter("title");
		
		Map<String, Object> map = customerService.getCustomerList(searchName, currentPage, limitPage);
		List<Customer> list = (List<Customer>)map.get("list");
		int lastPage = (Integer)map.get("lastPage");
		
		request.setAttribute("list", list);
		request.setAttribute("lastPage", lastPage);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("searchName", searchName);
		request.setAttribute("filmId", filmId);
		request.setAttribute("title", title);
		request.setAttribute("inventoryId", inventoryId);
		request.getRequestDispatcher("/WEB-INF/auth/filmPromotion/filmPromotionRental.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		filmService = new FilmService(new FilmDao());
		
		int filmId = Integer.parseInt(request.getParameter("filmId"));
		int inventoryId = Integer.parseInt(request.getParameter("inventoryId"));
		int customerId = Integer.parseInt(request.getParameter("customerId"));
		int staffId = Integer.parseInt(request.getParameter("staffId"));
		
		filmService.addFilmPromotionByRental(inventoryId, customerId, staffId);
		
		response.sendRedirect("promotionOneServlet?filmId=" + filmId);
	}

}
