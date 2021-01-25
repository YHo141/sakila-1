package sakila.dao;

import java.sql.Connection;
import java.util.List;

import sakila.vo.JoinToTable;

public interface ICustomerDao {
	List<JoinToTable> selectCustomerList(Connection conn, String searchName, int currentPage, int limitPage) throws Exception;
	List<String> selectCustomerListByReturnDate(Connection conn, int customerId) throws Exception;
	int selectCustomerListCount(Connection conn, String searchName) throws Exception;
	JoinToTable selectCustomerOne(Connection conn, int customerId) throws Exception;
	List<JoinToTable> selectCustomerByFilmReturn(Connection conn, int customerId) throws Exception;
	void updateCustomer(Connection conn, JoinToTable join) throws Exception;
}
