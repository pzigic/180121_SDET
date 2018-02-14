package com.trms.daos;

import static com.trms.util.CloseStreams.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.trms.beans.Employee;
import com.trms.util.Connections;

public class EmployeeDaoImpl implements EmployeeDao {
	final static Logger logger = Logger.getLogger(EmployeeDaoImpl.class);
	
	public Employee getEmployee(int empId){
		PreparedStatement ps = null;
		ResultSet rs = null;
		Employee emp = null;
		
		try(Connection conn = Connections.getConnection()){
			String sql = "SELECT a.emp_fname, a.emp_lname, a.emp_reportsto, b.depart_name, c.title, a.emp_availreim," + 
					" a.emp_addr, a.emp_city, a.emp_state, a.emp_zip" + 
					" FROM employees a, empdepartments b, employeetitles c" + 
					" WHERE emp_id = ? AND a.emp_department = b.depart_id AND a.emp_title_id = c.title_id";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, empId);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				emp = new Employee(empId, rs.getString(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getString(5),
						rs.getFloat(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getInt(10));
			}
		} catch(SQLException e) {
			logger.error(e.getMessage());
		} finally {
			close(ps);
			close(rs);
		}
		
		return emp;
	}
}