package com.revature.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.dao.EmployeeDAOImpl;
import com.revature.models.Employee;
import com.revature.utils.RequestHelper;

/**
 * Servlet implementation class NewEmployeeServlet
 */
public class NewEmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewEmployeeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("Views/NewEmployee.html");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Employee e = new Employee();
		EmployeeDAOImpl ed = new EmployeeDAOImpl();
		String name = request.getParameter("empName");
		e.setName(name);
		
		String email = request.getParameter("empEmail");
		e.setEmail(email);
		
		String phone = request.getParameter("empPhone");
		e.setPhone(phone);
		
		String isManager = request.getParameter("manager");
		
		String userName = RequestHelper.getUsername(request);
		e.setUsername(userName);
		
		String password = RequestHelper.getPassword(request);
		e.setPassword(password);
		if(isManager.equals("Not Management")) {
			isManager = "N";
			e.setIsManager(isManager);
		} else {
			isManager = "Y";
			e.setIsManager(isManager);
		}
		ed.createEmployee(e);
		doGet(request, response);
	}

}
