package sakila.dao;

import java.sql.Connection;
import java.util.List;

import sakila.vo.JoinToTable;

public interface ICustomerDao {
	List<JoinToTable> selectCustomerList(Connection conn) throws Exception;
}
