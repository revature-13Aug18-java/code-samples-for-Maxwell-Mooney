package com.revature.dao;
import java.sql.Date;
import java.util.List;

import com.revature.models.Transactions;
public interface TransactionsDAO {
	public List<Transactions> getTransactions();
	public List<Transactions> getTransactionByID(int id);
	public void createTransaction (Date date, String reason, double amount, int empID);
	public void approveTransaction (int id, String manager);
	public void denyTransaction (int id, String manager);
	public void deleteTransactionByID (int id);
}
