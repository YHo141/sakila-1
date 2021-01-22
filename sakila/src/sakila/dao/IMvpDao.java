package sakila.dao;

import java.sql.Connection;
import java.util.List;

import sakila.vo.JoinToTable;

public interface IMvpDao {
	List<JoinToTable> selectPaymentByMvp(Connection conn, int currentPage, int limitPage) throws Exception;
	int selectPaymentByMvpCount(Connection conn) throws Exception;
}
