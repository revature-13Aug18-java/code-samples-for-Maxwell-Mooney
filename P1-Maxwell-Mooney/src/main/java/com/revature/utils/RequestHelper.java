package com.revature.utils;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.ThreadLocalRandom;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import com.revature.dao.EmployeeDAO;
import com.revature.dao.EmployeeDAOImpl;
import com.revature.models.Employee;

public class RequestHelper {
	public static String process(HttpServletRequest request) throws IOException {
		HttpSession session = request.getSession();
		EmployeeDAO ed = new EmployeeDAOImpl();
		//HttpSession session = request.getSession();
		String destination = "";
		Employee e = ed.empLogIn(request.getParameter("username"), request.getParameter("password"));
		if(e != null) {
			if(e.getIsManager().equals("Y")) {
				session.setAttribute("empID", e.getId());
				session.setAttribute("name", e.getName());
				session.setAttribute("email", e.getEmail());
				session.setAttribute("username", e.getUsername());
				session.setAttribute("password", e.getPassword());
				session.setAttribute("isManager", e.getIsManager());
				session.setAttribute("phone", e.getPhone());
				destination = "reimbursements";
			} else {
				session.setAttribute("empID", e.getId());
				session.setAttribute("name", e.getName());
				session.setAttribute("email", e.getEmail());
				session.setAttribute("username", e.getUsername());
				session.setAttribute("password", e.getPassword());
				session.setAttribute("isManager", e.getIsManager());
				session.setAttribute("phone", e.getPhone());
				destination = ("userReimbursement?id="+e.getId());
			}
		} else {
			destination = "login";
		}
		return destination;
	}
	
	public static String getUsername(HttpServletRequest request) throws IOException {
		String sql = "SELECT USERNAME FROM REIMBURSE_EMPLOYEES";
		String numbers = "0123456789";
		String tempUser = "user";
		int same = 0;
		ResultSet rs = null;
		for(int i = 0; i < 4; i++) {
			tempUser += numbers.charAt(ThreadLocalRandom.current().nextInt(0, 10));
		}
		try(Connection con = ConnectionUtil.getConnection();
				Statement s = con.createStatement();) {
			rs = s.executeQuery(sql);
			while(rs.next()) {
				String user = rs.getString("USERNAME");
				if(tempUser.equals(user)) {
					same++;
				}
			}
			if(same > 0) {
				tempUser = getUsername(request);
			}

		} catch(SQLException | IOException e) {
			e.printStackTrace();
		}
		return tempUser;
	}
	
	public static String getPassword(HttpServletRequest request) throws IOException {
		String numbers = "0123456789";
		String tempPass = "temp";
		for(int i = 0; i < 4; i++) {
			tempPass += numbers.charAt(ThreadLocalRandom.current().nextInt(0, 10));
		}
		
		return tempPass;
	}
}
