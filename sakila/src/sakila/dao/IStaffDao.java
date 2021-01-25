package sakila.dao;

import java.sql.Connection;
import java.util.List;

import sakila.vo.JoinToTable;
import sakila.vo.Staff;

public interface IStaffDao {
	Staff selectStaffByKey(Connection conn, Staff staff) throws Exception;
	
	List<JoinToTable> selectStaff(Connection conn, int staffId) throws Exception;
	void updateStaff(Connection conn, JoinToTable join) throws Exception;
}
