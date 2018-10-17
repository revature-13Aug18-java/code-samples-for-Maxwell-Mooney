package com.revature.servlets;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.dao.TransactionsDAOImpl;
import com.revature.models.Transactions;

/**
 * Servlet implementation class NewTransactionServlet
 */
public class NewTransactionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewTransactionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("Views/NewTransaction.html");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Transactions t = new Transactions();
		TransactionsDAOImpl tr = new TransactionsDAOImpl();
		Date date = Date.valueOf(request.getParameter("transDate"));
		String reason = request.getParameter("transReason");
		double amount = Double.parseDouble(request.getParameter("transAmount"));
		amount = Math.round(amount * 100.0) / 100.0;
		int empID = (int)session.getAttribute("empID");
		tr.createTransaction(date, reason, amount, empID);
		doGet(request, response);
	}

}
