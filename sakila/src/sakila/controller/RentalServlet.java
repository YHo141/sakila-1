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

import sakila.dao.RentalDao;
import sakila.service.RentalService;
import sakila.vo.JoinToTable;


@WebServlet("/auth/RentalServlet")
public class RentalServlet extends HttpServlet {
	private RentalService rentalService;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		rentalService = new RentalService(new RentalDao());
		
		int currentPage = 1;
		int limitPage = 10;
		
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		
		Map<String, Object> map = rentalService.getFilmRentalList(currentPage, limitPage);
		List<JoinToTable> list = (List<JoinToTable>)map.get("list");
		int lastPage = (Integer)map.get("lastPage");
		
		System.out.println(currentPage + ": 현재 패이지 확인");
		
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("lastPage", lastPage);
		request.setAttribute("list", list);
		request.getRequestDispatcher("/WEB-INF/auth/filmReturn/filmReturnList.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
