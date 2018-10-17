package com.revature.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.dao.EmployeeDAOImpl;
import com.revature.models.Employee;

/**
 * Servlet implementation class EditProfileServlet
 */
public class EditProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditProfileServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("Views/Profile.html");
		EmployeeDAOImpl ed = new EmployeeDAOImpl();
		HttpSession session = request.getSession();
		int id = (int) session.getAttribute("empID");
		
		String name = request.getParameter("name");
		
		String email = request.getParameter("email");
		
		String username = request.getParameter("username");
		
		String password = request.getParameter("password");
		
		String phone = request.getParameter("phone");
		session.setAttribute("name", name);
		session.setAttribute("email", email);
		session.setAttribute("username", username);
		session.setAttribute("password", password);
		session.setAttribute("phone", phone);
		ed.updateEmployee(name, email, username, password, phone, id);
		
		rd.forward(request, response);
	}

}
