package com.revature.dao;

import java.sql.Connection;
import java.util.List;

import com.revature.models.Employee;

public interface EmployeeDAO {

	public List<Employee> getEmployees();
	public Employee getEmployeeByID(int id);
	public int createEmployee (Employee employee);
	public void updateEmployee (String name, String email, String username, String password, String phone, int id);
	public int deleteEmployeeByID (int id);
	Employee empLogIn(String username, String password);
}
