package sakila.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import sakila.dao.StaffDao;
import sakila.service.StaffService;
import sakila.vo.JoinToTable;

@WebServlet("/auth/staffServlet")
public class StaffServlet extends HttpServlet {
	private StaffService staffService;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();	
		Object ob = session.getAttribute("loginStaff");
		int staffId = (Integer)ob;
		//System.out.println(staffId);
		
		staffService = new StaffService(new StaffDao());
		List<JoinToTable> list = staffService.getStaff(staffId);
		
		request.setAttribute("list", list);
		
		
		request.getRequestDispatcher("/WEB-INF/auth/staff/staffOne.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		staffService = new StaffService(new StaffDao());
		
		int staffId = Integer.parseInt(request.getParameter("staffId"));
		String email = request.getParameter("email");
		String username = request.getParameter("username");
		String phone = request.getParameter("phone");
		
		staffService.modifyStaff(username, email, phone, staffId);
		
		response.sendRedirect("staffServlet");
	}
}
