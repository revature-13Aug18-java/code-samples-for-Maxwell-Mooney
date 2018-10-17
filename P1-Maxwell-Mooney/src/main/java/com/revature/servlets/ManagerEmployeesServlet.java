package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ManagerReimbursementServlet
 */
public class ManagerEmployeesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManagerEmployeesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//HttpSession session = request.getSession(false);
		//PrintWriter pw = response.getWriter();
		//response.setContentType("application/json");
		//if(session != null && session.getAttribute("username") != null && session.getAttribute("isManager").equals("Y")) {
			RequestDispatcher rd = request.getRequestDispatcher("Views/ManagerEmployees.html");
			rd.forward(request, response);
		//} else if(session != null && session.getAttribute("username") != null && session.getAttribute("isManager").equals("N")){
			//RequestDispatcher rd = request.getRequestDispatcher("Views/Employee.html");
			//rd.forward(request, response);
		//} else {
			//RequestDispatcher rd = request.getRequestDispatcher("login");
		//}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
