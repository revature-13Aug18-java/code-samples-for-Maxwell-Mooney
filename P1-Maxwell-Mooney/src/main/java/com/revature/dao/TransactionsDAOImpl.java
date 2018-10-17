package com.revature.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Employee;
import com.revature.models.Transactions;
import com.revature.utils.ConnectionUtil;

public class TransactionsDAOImpl implements TransactionsDAO {

	@Override
	public List<Transactions> getTransactions() {
		List<Transactions> transactionsList = new ArrayList<>();
		String sql = "SELECT * FROM (SELECT * FROM TRANSACTIONS ORDER BY TRANS_DATE DESC)";
		try(Connection con = ConnectionUtil.getConnection();
				Statement s = con.createStatement();
				ResultSet rs = s.executeQuery(sql);) {
			while(rs.next()) {
				Transactions t = new Transactions();
				int transID = rs.getInt("TRANS_ID");
				t.setId(transID);
				
				Date date = rs.getDate("TRANS_DATE");
				t.setDate(date);
				
				String reason = rs.getString("REASON");
				t.setReason(reason);
				
				String status = rs.getString("STATUS");
				t.setStatus(status);
				
				String manager = rs.getString("RESOLVING_MANAGER");
				t.setManager(manager);
				
				double amount = rs.getDouble("AMOUNT");
				t.setAmount(amount);
				
				int empID = rs.getInt("EMP_ID");
				EmployeeDAOImpl edi = new EmployeeDAOImpl();
				Employee e = edi.getEmployeeByID(empID);
				t.setEmployee(e);
				
				transactionsList.add(t);
			}
		} catch(IOException | SQLException ex) {
			ex.printStackTrace();
		}
		return transactionsList;
	}

	@Override
	public List<Transactions> getTransactionByID(int id) {
		List<Transactions> transactionList = new ArrayList<>();
		String sql = "SELECT * FROM (SELECT * FROM TRANSACTIONS ORDER BY TRANS_DATE DESC) WHERE EMP_ID = ?";
		ResultSet rs = null;
		try (Connection con = ConnectionUtil.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Transactions t = new Transactions();
				int transID = rs.getInt("TRANS_ID");
				t.setId(transID);
				
				Date date = rs.getDate("TRANS_DATE");
				t.setDate(date);
				
				String reason = rs.getString("REASON");
				t.setReason(reason);
				
				String status = rs.getString("STATUS");
				t.setStatus(status);
				
				String manager = rs.getString("RESOLVING_MANAGER");
				t.setManager(manager);
				
				double amount = rs.getDouble("AMOUNT");
				t.setAmount(amount);
				int empID = rs.getInt("EMP_ID");
				EmployeeDAOImpl edi = new EmployeeDAOImpl();
				Employee e = edi.getEmployeeByID(empID);
				t.setEmployee(e);
				
				transactionList.add(t);
			}
		} catch(IOException | SQLException ex) {
			ex.printStackTrace();
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch(SQLException e1) {
					e1.printStackTrace();
				}
			}
		}
		return transactionList;
	}

	@Override
	public void createTransaction(Date date, String reason, double amount, int id) {
		String sql = "INSERT INTO TRANSACTIONS (TRANS_DATE, REASON, STATUS, RESOLVING_MANAGER, AMOUNT, EMP_ID) VALUES (?, ?, 'Pending', 'N/A', ?, ?)";
		try(Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setDate(1, date);
			ps.setString(2, reason);
			ps.setDouble(3, amount);
			ps.setInt(4, id);
			ps.executeUpdate();
		} catch(SQLException | IOException ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void approveTransaction(int id, String manager) {
		String sql = "UPDATE TRANSACTIONS SET STATUS = 'Approved', RESOLVING_MANAGER = ? WHERE TRANS_ID = ?";
		try(Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setString(1, manager);
			ps.setInt(2, id);
			ps.executeUpdate();
		} catch(SQLException | IOException e) {
			e.printStackTrace();
		}
	}
	
	public void denyTransaction(int id, String manager) {
		String sql = "UPDATE TRANSACTIONS SET STATUS = 'Denied', RESOLVING_MANAGER = ? WHERE TRANS_ID = ?";
		try(Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setString(1, manager);
			ps.setInt(2, id);
			ps.executeUpdate();
		} catch(SQLException | IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteTransactionByID(int id) {
		String sql = "DELETE FROM TRANSACTIONS WHERE TRANS_ID = ?";
		try(Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch(SQLException | IOException e) {
			e.printStackTrace();
		}
	}
}
