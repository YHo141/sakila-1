package sakila.dao;

import java.sql.Connection;
import java.util.List;

import sakila.vo.CoAndCiAndAAndS;
import sakila.vo.Staff;

public interface IStaffDao {
	Staff selectStaffByKey(Connection conn, Staff staff) throws Exception;
	
	List<CoAndCiAndAAndS> selectStaff(Connection conn, int staffId) throws Exception;
}
