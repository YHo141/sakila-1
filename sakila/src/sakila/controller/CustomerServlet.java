package sakila.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sakila.dao.CustomerDao;
import sakila.service.CustomerService;
import sakila.vo.JoinToTable;

@WebServlet("/auth/customerServlet")
public class CustomerServlet extends HttpServlet {
	private CustomerService customerService;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		customerService = new CustomerService(new CustomerDao());
		int limitPage = 10;
		int currentPage = 1;
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		
		String searchName = "";
		if(request.getParameter("searchName") != null) {
			searchName = request.getParameter("searchName");
		}
		
		Map<String, Object> map = customerService.getCustomerList(searchName, currentPage, limitPage);
		List<JoinToTable> list = (List<JoinToTable>)map.get("list");
		int lastPage = (Integer)map.get("lastPage");
		
		request.setAttribute("lastPage", lastPage);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("searchName", searchName);
		request.setAttribute("list", list);
		request.getRequestDispatcher("/WEB-INF/auth/customer/customerList.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		customerService = new CustomerService(new CustomerDao());
		int limitPage = 10;
		int currentPage = 1;
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		
		String searchName = "";
		if(request.getParameter("searchName") != null) {
			searchName = request.getParameter("searchName");
		}
		
		Map<String, Object> map = customerService.getCustomerList(searchName, currentPage, limitPage);
		List<JoinToTable> list = (List<JoinToTable>)map.get("list");
		int lastPage = (Integer)map.get("lastPage");
		
		request.setAttribute("lastPage", lastPage);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("searchName", searchName);
		request.setAttribute("list", list);
		request.getRequestDispatcher("/WEB-INF/auth/customer/customerList.jsp").forward(request, response);
	}
}
