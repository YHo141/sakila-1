package sakila.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sakila.dao.CustomerDao;
import sakila.service.CustomerService;
import sakila.vo.JoinToTable;

@WebServlet("/auth/customerOneServlet")
public class CustomerOneServlet extends HttpServlet {
	private CustomerService customerService;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		customerService = new CustomerService(new CustomerDao());
		int customerId = Integer.parseInt(request.getParameter("customerId"));
		
		JoinToTable join = customerService.getCustomerOne(customerId);
		List<JoinToTable> list = customerService.getCustomerOneByFilm(customerId);
		
		request.setAttribute("join", join);
		request.setAttribute("list", list);
		request.getRequestDispatcher("/WEB-INF/auth/customer/customerOne.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		customerService = new CustomerService(new CustomerDao());
		int customerId = Integer.parseInt(request.getParameter("customerId"));
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		
		System.out.println(phone);
		
		customerService.modifyCustomer(phone, email, customerId);
		
		response.sendRedirect("customerOneServlet?customerId=" + customerId);
	}

}
