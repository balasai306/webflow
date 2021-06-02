package com.web.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.web.exep.Myexception;
import com.web.model.employeeModel;
import com.web.util.Closing;
import com.web.util.Connector;

public class DisplyDao {
	String sql = "select * from empDetails";
	Closing close = new Closing();

	public List<employeeModel> display() throws Myexception {

		Connector connection = new Connector();
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		List<employeeModel> emp = new ArrayList<>();

		try {
			con = connection.getConnector();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				employeeModel empl = new employeeModel();
				String empName = rs.getString(1);
				int empId = rs.getInt(2);
				String empMail = rs.getString(3);
				empl.setEmpName(empName);
				empl.setEmpId(empId);
				empl.setEmpMail(empMail);
				emp.add(empl);
			}

		} catch (SQLException e) {
		
			throw new Myexception("Sql exception occured");
		}
		finally {
			close.closeConnection(rs);
			close.closeConnection(stmt);
			close.closeConnection(con);
		}

		return emp;

	}

}
