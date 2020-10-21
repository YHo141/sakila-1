package sakila.dao;

import java.sql.Connection;

import sakila.vo.Staff;

public interface IStaffDao {
	Staff selectStaffByKey(Connection conn, Staff staff) throws Exception;
}
