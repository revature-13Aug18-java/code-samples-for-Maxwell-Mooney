package com.revature.servlets;

import java.io.IOException;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dao.TransactionsDAO;
import com.revature.dao.TransactionsDAOImpl;
import com.revature.models.Transactions;

/**
 * Servlet implementation class TransactionsServlet
 */
public class TransactionsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TransactionsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idStr = request.getParameter("id");
		System.out.println(idStr);
		TransactionsDAO tr = new TransactionsDAOImpl();
		ObjectMapper om = new ObjectMapper();
		PrintWriter pw = response.getWriter();
		if(idStr != null) {
			int id = Integer.parseInt(idStr);
			List<Transactions> transactions = tr.getTransactionByID(id);
			String transactionString = om.writeValueAsString(transactions);
			pw.write("{\"transactions\":" + transactionString + "}");
		} else {
			List<Transactions> transactions = tr.getTransactions();
			String transactionString = om.writeValueAsString(transactions);
			transactionString = "{\"transactions\":" + transactionString + "}";
			pw.println(transactionString);
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
