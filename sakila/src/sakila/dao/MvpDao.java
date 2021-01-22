package sakila.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import sakila.quary.ActorQuery;
import sakila.quary.MvpQuery;
import sakila.vo.Customer;
import sakila.vo.JoinToTable;
import sakila.vo.Payment;

public class MvpDao implements IMvpDao{
	private MvpQuery mvpQuery;
	
	@Override
	public int selectPaymentByMvpCount(Connection conn) throws Exception{
		int lastPage = 0;
		mvpQuery = new MvpQuery();
		
		PreparedStatement stmt = conn.prepareStatement(mvpQuery.SELECT_PAYMENT_BY_MVP_COUNT);
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()) {
			lastPage++;
		}
		
		stmt.close();
		rs.close();
		
		return lastPage;
	}
	
	@Override
	public List<JoinToTable> selectPaymentByMvp(Connection conn, int currentPage, int limitPage) throws Exception{
		List<JoinToTable> list = new ArrayList<JoinToTable>();
		mvpQuery = new MvpQuery();
		
		PreparedStatement stmt = conn.prepareStatement(mvpQuery.SELECT_PATMENT_BY_MVP);
		stmt.setInt(1, (currentPage-1)*limitPage);
		stmt.setInt(2, limitPage);
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()) {
			JoinToTable join = new JoinToTable();
			join.setCustomer(new Customer());
			join.setPayment(new Payment());
			
			join.getCustomer().setLastName(rs.getString("customerName"));
			join.getPayment().setAmount(rs.getFloat("amount"));
			
			list.add(join);
		}
		
		stmt.close();
		rs.close();
		
		return list;
	}
}
