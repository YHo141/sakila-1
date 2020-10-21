package sakila.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import sakila.service.StaffService;
import sakila.service.StatsService;
import sakila.vo.Staff;
import sakila.vo.Stats;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private StatsService statsService;
	private StaffService staffService;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		// 세션을 서블렛이 실행 될때 확인..
		// 로그인이 구현되면 로그인 했을 경우 세션 발생
		session.setAttribute("test", "test");
		
		/*
		if(session.getAttribute("loginStaff") != null) {
			response.sendRedirect(request.getContextPath()+"/auth/IndexServlet");
			return;
		}*/
		
		statsService = new StatsService();
		
		// 2개의 리턴값을 받아옴
		Map<String, Object> map = statsService.getStats();
		Stats stats = (Stats)(map.get("stats"));
		int sumCnt = (Integer)(map.get("sumCnt"));
		
		// 최종 확인
		System.out.println(stats + "<--- 최종");
		System.out.println(sumCnt + "<--- 최종");
		
		
		request.setAttribute("stats", stats);
		request.setAttribute("sumCount", sumCnt);
		
		request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int staffId = Integer.parseInt(request.getParameter("staffId"));
		String password = request.getParameter("password");
		
		System.out.println(staffId + "사용자 번호 확인");
		System.out.println(password + "사용자 비닐번호 확인");
		
		staffService = new StaffService();

		Staff staff = staffService.getStaffIdAndName(staffId, password);
		
		if(staff != null) {
			staffId = staff.getStaffId();
			String userName = staff.getUserName();
			
			request.setAttribute("staffId", staffId);
			request.setAttribute("userName", userName);
			
			request.getRequestDispatcher("/WEB-INF/auth/Index.jsp").forward(request, response);
			
			return;
		}
		
		request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
	}

}























