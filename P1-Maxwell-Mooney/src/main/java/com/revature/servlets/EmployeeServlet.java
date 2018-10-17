package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dao.EmployeeDAO;
import com.revature.dao.EmployeeDAOImpl;
import com.revature.models.Employee;

/**
 * Servlet implementation class EmployeeServlet
 */
public class EmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idStr = request.getParameter("id");
		//System.out.println("we got a param value:" + idStr);
		
		EmployeeDAO ed = new EmployeeDAOImpl();
		ObjectMapper om = new ObjectMapper();
		PrintWriter pw = response.getWriter();
		if(idStr != null) {
			int id = Integer.parseInt(idStr);
			Employee e = ed.getEmployeeByID(id);
			if(e.getId() == 0) {
				pw.print("invalid employee id");
			} else {
				String employeeString = om.writeValueAsString(e);
				pw.write(employeeString);
			}
		} else {
			List<Employee> employees = ed.getEmployees();
			String employeeString = om.writeValueAsString(employees);
			employeeString = "{\"employees\":"+employeeString+"}";
			pw.println(employeeString);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
