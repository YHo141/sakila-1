package sakila.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sakila.dao.CustomerDao;
import sakila.service.CustomerService;
import sakila.vo.JoinToTable;

@WebServlet("/CustomerServlet")
public class CustomerServlet extends HttpServlet {
	private CustomerService customerService;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		customerService = new CustomerService(new CustomerDao());
		List<JoinToTable> list = new ArrayList<JoinToTable>();
		
		list = customerService.getCustomerList();
		
		request.setAttribute("list", list);
		request.getRequestDispatcher("/WEB-INF/auth/customer/customerList.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
}
