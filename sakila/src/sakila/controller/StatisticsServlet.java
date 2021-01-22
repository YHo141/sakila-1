package sakila.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sakila.dao.StatisticsDao;
import sakila.service.StatisticsService;
import sakila.vo.SalesByFilmCategory;

@WebServlet("/auth/statisticsServlet")
public class StatisticsServlet extends HttpServlet {
	private StatisticsService statisticsSerivce;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		statisticsSerivce = new StatisticsService(new StatisticsDao());
		
		int currentPage = 1;
		int limitPage = 5;
		
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		
		int staffId = Integer.parseInt(request.getParameter("staffId"));
		float storeSum = statisticsSerivce.getStoreSum(staffId);
		int storeRentalSum = statisticsSerivce.getStoreRentalSum(staffId);
		Map<String, Object> map = statisticsSerivce.getStoreCategorySum(currentPage, limitPage);
		
		List<SalesByFilmCategory> list = (List<SalesByFilmCategory>)map.get("list");
		int lastPage = (Integer)map.get("lastPage");
		
		request.setAttribute("storeSum", storeSum);
		request.setAttribute("storeRentalSum", storeRentalSum);
		request.setAttribute("list", list);
		request.setAttribute("lastPage", lastPage);
		request.setAttribute("currentPage", currentPage);
		
		request.getRequestDispatcher("/WEB-INF/auth/statistics/statisticsList.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
