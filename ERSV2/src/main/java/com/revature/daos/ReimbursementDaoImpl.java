package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;

import com.revature.models.Employee;
import com.revature.models.Reimbursement;
import com.revature.util.ConnectionUtil;

public class ReimbursementDaoImpl implements ReimbursementDao {
	
	SimpleDateFormat fmtDate = new SimpleDateFormat("MM-dd-yyyy");

	public boolean createReimbursement(Reimbursement newReimbursement) {
		String sql = "insert into ers.\"Reimbursement\" (date,description, category, cost, status, comments, employee_id)\r\n"
				+ "values (now(),?,?,?,'PENDING',?,?)";

		try (Connection conn = ConnectionUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			// ps.setTimestamp(1,new Timestamp(System.currentTimeMillis()));//Current date
			// and
			ps.setString(1, newReimbursement.getDescription());
			ps.setString(2, newReimbursement.getCategory());
			ps.setString(3, newReimbursement.getCost());
			ps.setString(4, newReimbursement.getComments());
			ps.setInt(5, newReimbursement.getEmployee_id());
			// ps.setInt(8, newReimbursement.getReimbursementId());

			ps.execute();

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;

	}

//	public Reimbursement viewReimbursementDetails(int reimbursementId) {
//		String sql = "\r\n" + "SELECT *\r\n" + "FROM ers.\"Reimbursement\" \r\n" + "WHERE reimbursementid = ?";
//		Reimbursement reimburseFromDB = new Reimbursement();
//
//		try (Connection c = ConnectionUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
//			ps.setInt(1, reimbursementId);
//
//			ResultSet rs = ps.executeQuery();
//			while (rs.next()) {
//				reimburseFromDB.setReimbursementId(rs.getInt("ReimbursementId"));
//				reimburseFromDB.setDate(rs.getDate("Date"));
//				reimburseFromDB.setDescription(rs.getString("Description"));
//				reimburseFromDB.setCategory(rs.getString("Category"));
//				reimburseFromDB.setCost(rs.getString("Cost"));
//				reimburseFromDB.setStatus(rs.getString("status"));
//				reimburseFromDB.setComments(rs.getString("comments"));
//				reimburseFromDB.setEmployee_id(rs.getInt("employee_id"));
//				ps.execute();
//			}
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		// String result = "Updated Customer";
//		System.out.print("Reimbursement object from DB " + reimburseFromDB);
//
//		return reimburseFromDB;
//	}

	public Reimbursement viewReimbursementDetails(int reimbursementId) {
		String sql = "\r\n" + "SELECT *\r\n" + "FROM ers.\"Reimbursement\" \r\n" + "WHERE reimbursementid = ?";
		Reimbursement reimburseFromDB = new Reimbursement();

		try (Connection c = ConnectionUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
			ps.setInt(1, reimbursementId);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				reimburseFromDB.setReimbursementId(rs.getInt("ReimbursementId"));
				reimburseFromDB.setDate(rs.getDate("Date"));
				reimburseFromDB.setDescription(rs.getString("Description"));
				reimburseFromDB.setCategory(rs.getString("Category"));
				reimburseFromDB.setCost(rs.getString("Cost"));
				reimburseFromDB.setStatus(rs.getString("status"));
				reimburseFromDB.setComments(rs.getString("comments"));
				reimburseFromDB.setEmployee_id(rs.getInt("employee_id"));
				//Get approval details if the status is APPROVED or Denied
				if( (reimburseFromDB.getStatus().equalsIgnoreCase("APPROVED"))
					|| (reimburseFromDB.getStatus().equalsIgnoreCase("DENIED"))) {
					String employeename = getEmployeeName(rs.getInt("reviewed_by"));				
					reimburseFromDB.setReviewed_by(employeename);
					reimburseFromDB.setReviewed_date(rs.getTimestamp("reviewed_date"));
				}
			
				//ps.execute();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		// String result = "Updated Customer";
		System.out.print("Reimbursement object from DB " + reimburseFromDB);

		return reimburseFromDB;
	}
	
	
	public String getEmployeeName(int emplID) {
		String sql = "SELECT * FROM ers.\"Employee\" WHERE  EmployeeId=?";
		
		String empName="";
		Employee emplFromDB = new Employee();

		try (Connection c = ConnectionUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
			ps.setInt(1,emplID);
			
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				empName = rs.getString("FirstName");
				
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		// String result = "Updated Customer";
		System.out.print("Employee object from DB " + emplFromDB);

		return empName;
	}


	
	
	
	public boolean updateReimbursement(Reimbursement updateReimbursement, int reimbursementid) {
		String sql = "update ers.\"Reimbursement\" set Date=?, Description =?, \r\n"
				+ "Category=?, Cost=?, status=?, comments=?, employee_id =? where reimbursementid=?";

		try (Connection c = ConnectionUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
			ps.setTimestamp(1, new Timestamp(System.currentTimeMillis()));// Current date and time
			ps.setString(2, updateReimbursement.getDescription());
			ps.setString(3, updateReimbursement.getCategory());
			ps.setString(4, updateReimbursement.getCost());
			ps.setString(5, updateReimbursement.getStatus());
			ps.setString(6, updateReimbursement.getComments());
			ps.setInt(7, updateReimbursement.getEmployee_id());
			ps.setInt(8, reimbursementid);// id passed in as param
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}
	public boolean updateReimbursementStatus(JSONArray arrReimbiIds, String status) {
		String sql = "update ers.\"Reimbursement\" set status=? where reimbursementid=?";
		int reimbursementid=0;
		try (Connection c = ConnectionUtil.getConnection(); 
			PreparedStatement ps = c.prepareStatement(sql)) {
			ps.setString(1, status);// Current date and time
			
			for (int i = 0; i < arrReimbiIds.length(); i++) {
				reimbursementid = Integer.valueOf((String)arrReimbiIds.get(i)).intValue();	
				ps.setInt(2, reimbursementid);// id passed in as param
				ps.executeUpdate();
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	
	public boolean updateReimbursementStatus(JSONArray arrReimbiIds, String status, int reviewerId) {
		String sql = "update ers.\"Reimbursement\" set status=?, reviewed_by=?, reviewed_date=? where reimbursementid=? ";
		int reimbursementid=0;
		
		try (Connection c = ConnectionUtil.getConnection(); 
			PreparedStatement ps = c.prepareStatement(sql)) {
			ps.setString(1, status);// Current date and time
			ps.setInt(2, reviewerId);// Current date and time
			ps.setTimestamp(3, new Timestamp(System.currentTimeMillis()));// Current date and time
			
			for (int i = 0; i < arrReimbiIds.length(); i++) {
				reimbursementid = Integer.valueOf((String)arrReimbiIds.get(i)).intValue();	
				ps.setInt(4, reimbursementid);// id passed in as param
				ps.executeUpdate();
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	
	
	
	public String deleteReimbursement(int reimbursementId) {
		String sql = "delete from ers.Reimbursement where reimbursementId=?";

		try (Connection conn = ConnectionUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, reimbursementId);
			ps.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return "Deleted Reimbursement";
	}

	public List<Reimbursement> getAllReimbursements() {
		List<Reimbursement> reimbursements = new ArrayList<Reimbursement>();
		String sql = "select * from ers.\"Reimbursement\";";

		try (Connection conn = ConnectionUtil.getConnection();
				Statement ps = conn.createStatement();
				ResultSet rs = ps.executeQuery(sql)) {

			while (rs.next()) {
				// get data from each employee
				Reimbursement reim = new Reimbursement();
				reim.setReimbursementId(rs.getInt("ReimbursementId"));
				reim.setDate(rs.getDate("Date"));
				reim.setDescription(rs.getString("Description"));
				reim.setCategory(rs.getString("Category"));
				reim.setCost(rs.getString("Cost"));
				reim.setStatus(rs.getString("status"));
				reim.setComments(rs.getString("comments"));
				reim.setEmployee_id(rs.getInt("employee_id"));

				//Get approval details if the status is APPROVED or Denied
				if( (reim.getStatus().equalsIgnoreCase("APPROVED"))
					|| (reim.getStatus().equalsIgnoreCase("DENIED"))) {
					String employeename = getEmployeeName(rs.getInt("reviewed_by"));				
					reim.setReviewed_by(employeename);
					reim.setReviewed_date(rs.getTimestamp("reviewed_date"));
				}
				reimbursements.add(reim);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return reimbursements;
	}

	public List<Reimbursement> getAllReimbursementsByEmployee(int employeeid) {
		List<Reimbursement> reimbursements = new ArrayList<Reimbursement>();
		String sql = "select * from ers.\"Reimbursement\" where employee_id=?;";

		try (Connection conn = ConnectionUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, employeeid);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				// get data from each employee
				Reimbursement reim = new Reimbursement();
				reim.setReimbursementId(rs.getInt("ReimbursementId"));
				reim.setDate(rs.getDate("Date"));
				reim.setDescription(rs.getString("Description"));
				reim.setCategory(rs.getString("Category"));
				reim.setCost(rs.getString("Cost"));
				reim.setStatus(rs.getString("status"));
				reim.setComments(rs.getString("comments"));
				reim.setEmployee_id(rs.getInt("employee_id"));
				//Get approval details if the status is APPROVED or Denied
				if( (reim.getStatus().equalsIgnoreCase("APPROVED"))
					|| (reim.getStatus().equalsIgnoreCase("DENIED"))) {
					String employeename = getEmployeeName(rs.getInt("reviewed_by"));				
					reim.setReviewed_by(employeename);
					reim.setReviewed_date(rs.getTimestamp("reviewed_date"));
				}

				reimbursements.add(reim);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return reimbursements;
	}

}
