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

	public Employee getEmployee(int empId) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Employee emp = null;

		try (Connection conn = Connections.getConnection()) {
			String sql = "SELECT a.emp_fname, a.emp_lname, a.emp_reportsto, b.depart_name, c.title, a.emp_availreim,"
					+ " a.emp_addr, a.emp_city, a.emp_state, a.emp_zip"
					+ " FROM employees a, empdepartments b, employeetitles c"
					+ " WHERE emp_id = ? AND a.emp_department = b.depart_id AND a.emp_title_id = c.title_id";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, empId);
			rs = ps.executeQuery();

			if (rs.next()) {
				emp = new Employee(empId, rs.getString(1), rs.getString(2), rs.getInt(3), rs.getString(4),
						rs.getString(5), rs.getFloat(6), rs.getString(7), rs.getString(8), rs.getString(9),
						rs.getInt(10));
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
		} finally {
			close(ps);
			close(rs);
		}

		return emp;
	}

	@Override
	public int getTitle(int empId) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		int titleNum = -1;

		try (Connection conn = Connections.getConnection()) {
			String sql = "SELECT emp_title_id FROM employees WHERE emp_id = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, empId);
			rs = ps.executeQuery();

			if (rs.next()) {
				titleNum = rs.getInt(1);
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
		} finally {
			close(ps);
			close(rs);
		}
		
		return titleNum;
	}


	@Override
	public int getSubordinates(int empId) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		int subNum = -1;

		try (Connection conn = Connections.getConnection()) {
			String sql = "SELECT count(emp_reportsto) FROM employees WHERE emp_reportsto = ?"; // TODO modify to db query
			ps = conn.prepareStatement(sql);
			ps.setInt(1, empId);
			rs = ps.executeQuery();

			if (rs.next()) {
				subNum = rs.getInt(1);
			}
		} catch(SQLException e) {
			logger.error(e.getMessage());
		} finally {
			close(ps);
			close(rs);
		}
		return subNum;
	}

	@Override
	public int getReportsTo(int empId) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		int rt = -1;

		try (Connection conn = Connections.getConnection()) {
			String sql = "SELECT emp_reportsto FROM employees WHERE emp_id = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, empId);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				rt = rs.getInt(1);
			}
		} catch(SQLException e) {
			logger.error(e.getMessage());
		} finally {
			close(ps);
			close(rs);
		}
		
		return rt;
	}

	@Override
	public Employee getDepartAndTitle(int empId) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Employee e = new Employee();
		
		try (Connection conn = Connections.getConnection()) {
			String sql = "SELECT emp_department, emp_title_id FROM employees WHERE emp_id = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, empId);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				e.setDepartmentId(rs.getInt(1));
				e.setTitleId(rs.getInt(2));
			}
		} catch(SQLException er) {
			logger.error(er.getMessage());
		} finally {
			close(ps);
			close(rs);
		}
		
		return e;
	}

	@Override
	public int getDepartmentHeadIdBy(int departId) {
		PreparedStatement ps = null;
		int departHeadId = -1;
		
		try(Connection conn = Connections.getConnection()) {
			String sql = "SELECT emp_id FROM employees WHERE emp_department = ? AND (emp_title_id = 0 OR emp_title_id = 50)";
			ps = conn.prepareStatement(sql);
			logger.info("getDepartmentHeadIdBy() : departId=" + departId);
			ps.setInt(1, departId);
			departHeadId = ps.executeUpdate();
			logger.info("departHeadId=" + departHeadId);
		} catch(SQLException e) {
			logger.error(e.getMessage());
		} finally {
			close(ps);
		}
		
		return departHeadId;
	}

	@Override
	public int updateAvailReimb(int rId, int empId) {
		PreparedStatement ps = null;
		int result = -1;
		// TODO make a check ensure that available reimburse is enough to cover the projected amount
		try(Connection conn = Connections.getConnection()) {
			String sql = "UPDATE employees SET emp_availreim = "
					+ "(-(SELECT reimburse_projreimb FROM reimbursements WHERE reimburse_id = ?) + emp_availreim) "
					+ "WHERE emp_id = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, rId);
			ps.setInt(2, empId);
			result = ps.executeUpdate();
		} catch(SQLException e) {
			logger.error(e.getMessage());
		} finally {
			close(ps);
		}
		
		return result;
	}


}
