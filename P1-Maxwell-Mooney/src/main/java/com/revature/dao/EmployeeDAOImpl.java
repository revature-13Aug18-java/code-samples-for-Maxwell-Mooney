package com.revature.dao;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Employee;
import com.revature.utils.ConnectionUtil;
public class EmployeeDAOImpl implements EmployeeDAO{

	@Override
	public List<Employee> getEmployees() {
		List<Employee> employeeList = new ArrayList<>();
		String sql = "SELECT * FROM REIMBURSE_EMPLOYEE";
		ResultSet rs = null;
		try(Connection con = ConnectionUtil.getConnection();
				Statement s = con.createStatement();) {
			rs = s.executeQuery(sql);
			while(rs.next()) {
				Employee e = new Employee();
				int empID = rs.getInt("EMP_ID");
				System.out.println(empID);
				e.setId(empID);
				
				String empName = rs.getString("EMP_NAME");
				System.out.println(empName);
				e.setName(empName);
				
				String empEmail = rs.getString("EMAIL");
				e.setEmail(empEmail);
				
				String empPhone = rs.getString("PHONE");
				e.setPhone(empPhone);
				
				String empUsername = rs.getString("USERNAME");
				e.setUsername(empUsername);
				
				String empPassword = rs.getString("PASSWORD");
				e.setPassword(empPassword);
				
				String isManager = rs.getString("IS_MANAGER");
				e.setIsManager(isManager);
				
				
				employeeList.add(e);
			}
		} catch(IOException | SQLException ex) {
			ex.printStackTrace();
		}
		return employeeList;
	}

	@Override
	public Employee getEmployeeByID(int id) {
		Employee e = new Employee();
		String sql = "SELECT * FROM REIMBURSE_EMPLOYEE WHERE EMP_ID = ?";
		ResultSet rs = null;
		try {
			Connection con = ConnectionUtil.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				int empID = rs.getInt("EMP_ID");
				e.setId(empID);
				
				String empName = rs.getString("EMP_NAME");
				e.setName(empName);
				
				String empEmail = rs.getString("EMAIL");
				e.setEmail(empEmail);
				
				String empUsername = rs.getString("USERNAME");
				e.setUsername(empUsername);
				
				String empPassword = rs.getString("PASSWORD");
				e.setPassword(empPassword);
				
				String isManager = rs.getString("IS_MANAGER");
				e.setIsManager(isManager);
				
				String empPhone = rs.getString("PHONE");
				e.setPhone(empPhone);
				
				//e = new Employee(empID, empName, empEmail, empUsername, empPassword, isManager, empPhone);
			}
		} catch(IOException | SQLException ex) {
			ex.printStackTrace();
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}
		return e;
	}
	
	@Override
	public Employee empLogIn(String username, String password) {
		Employee e = null;
		String sql = "SELECT * FROM REIMBURSE_EMPLOYEE WHERE USERNAME = ? AND PASSWORD = ?";
		ResultSet rs = null;
		try(Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setString(1, username);
			ps.setString(2, password);
			rs = ps.executeQuery();
			while(rs.next()) {
				int empID = rs.getInt("EMP_ID");
				String empName = rs.getString("EMP_NAME");
				String empEmail = rs.getString("EMAIL");
				String empUsername = rs.getString("USERNAME");
				String empPassword = rs.getString("PASSWORD");
				String isManager = rs.getString("IS_MANAGER");
				String empPhone = rs.getString("PHONE");
				e = new Employee(empID, empName, empEmail, empUsername, empPassword, isManager, empPhone);
			}
		} catch(SQLException | IOException ex) {
			ex.printStackTrace();
		}
		return e;
	}

	@Override
	public int createEmployee(Employee employee) {
		int employeesCreated = 0;
		String sql = "INSERT INTO REIMBURSE_EMPLOYEE (EMP_NAME, EMAIL, PHONE, USERNAME, PASSWORD, IS_MANAGER) VALUES (?, ?, ?, ?, ?, ?)";
		try(Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setString(1, employee.getName());
			ps.setString(2, employee.getEmail());
			ps.setString(3, employee.getPhone());
			ps.setString(4, employee.getUsername());
			ps.setString(5, employee.getPassword());
			ps.setString(6, employee.getIsManager());
			employeesCreated = ps.executeUpdate();
		} catch(SQLException | IOException ex) {
			ex.printStackTrace();
		}
		return employeesCreated;
	}

	@Override
	public void updateEmployee(String name, String email, String username, String password, String phone, int id) {
		String sql = "UPDATE REIMBURSE_EMPLOYEE SET EMP_NAME = ?, EMAIL = ?, USERNAME = ?, PASSWORD = ?, PHONE = ? WHERE EMP_ID = ?"; 
		try(Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setString(1, name);
			ps.setString(2, email);
			ps.setString(3, username);
			ps.setString(4, password);
			ps.setString(5, phone);
			ps.setInt(6, id);
			ps.executeUpdate();
		} catch(SQLException | IOException e) {
			e.printStackTrace();
		}
	}
	@Override
	public int deleteEmployeeByID(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

}
