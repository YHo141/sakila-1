package sakila.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sakila.dao.MvpDao;
import sakila.service.MvpService;
import sakila.vo.JoinToTable;

@WebServlet("/auth/mvpServlet")
public class MvpServlet extends HttpServlet {
	private MvpService mvpService;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		mvpService = new MvpService(new MvpDao());
		
		int currentPage = 1;
		int limitPage = 10;
		
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		
		Map<String, Object> map = mvpService.getPaymentByMvp(currentPage, limitPage);
		List<JoinToTable> list = (List<JoinToTable>)map.get("list");
		int lastPage = (Integer)map.get("lastPage");
		
		request.setAttribute("list", list);
		request.setAttribute("lastPage", lastPage);
		request.setAttribute("currentPage", currentPage);
		
		request.getRequestDispatcher("/WEB-INF/auth/mvp/mvpList.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
