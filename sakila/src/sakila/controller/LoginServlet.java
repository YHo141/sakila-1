package sakila.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import sakila.dao.StaffDao;
import sakila.dao.StatsDao;
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
		if(session.getAttribute("loginStaff") != null) {
			request.getRequestDispatcher("/WEB-INF/auth/Index.jsp").forward(request, response);;
			return;
		}
		
		statsService = new StatsService(new StatsDao());
		
		// 2���� ���ϰ��� �޾ƿ�
		Map<String, Object> map = statsService.getStats();
		Stats stats = (Stats)(map.get("stats"));
		int sumCnt = (Integer)(map.get("sumCnt"));
		
		request.setAttribute("stats", stats);
		request.setAttribute("sumCount", sumCnt);	
		
		request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int staffId = Integer.parseInt(request.getParameter("staffId"));
		String password = request.getParameter("password");
		
		System.out.println(staffId + "����� ��ȣ Ȯ��");
		System.out.println(password + "����� ��ҹ�ȣ Ȯ��");
		
		staffService = new StaffService(new StaffDao());

		Staff staff = staffService.getStaffIdAndName(staffId, password);
		
		if(staff != null) {
			staffId = staff.getStaffId();
			String userName = staff.getUserName();
			
			request.setAttribute("staffId", staffId);
			request.setAttribute("userName", userName);
			
			statsService = new StatsService(new StatsDao());
			
			// 2���� ���ϰ��� �޾ƿ�
			Map<String, Object> map = statsService.getStats();
			Stats stats = (Stats)(map.get("stats"));
			int sumCnt = (Integer)(map.get("sumCnt"));
			
			// ���� Ȯ��
			System.out.println(stats + "<--- ����");
			System.out.println(sumCnt + "<--- ����");
			
			HttpSession session = request.getSession();
			session.setAttribute("loginStaff", staffId);
			
			
			request.setAttribute("stats", stats);
			request.setAttribute("sumCnt", sumCnt);
			
			request.getRequestDispatcher("/WEB-INF/auth/Index.jsp").forward(request, response);
			
			return;
		}
			
		request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
	}

}