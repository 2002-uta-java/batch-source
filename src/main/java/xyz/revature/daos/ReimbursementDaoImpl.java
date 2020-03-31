package xyz.revature.daos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import xyz.revature.models.Reimbursement;
import xyz.revature.util.DatabaseConnecter;

public class ReimbursementDaoImpl implements ReimbursementDao {
	
	@Override
	public int addReimbursement(Reimbursement reimbursement) {
		int id = 0;
		String sql = "select add_reimbursement(?, ?, ?)";
		ResultSet rs = null;
		try (Connection c = DatabaseConnecter.hardcodedConnection();
				CallableStatement cs = c.prepareCall(sql)){
			cs.setInt(1, reimbursement.getEmployeeId());
			cs.setString(2, reimbursement.getExpenseType());
			cs.setInt(3, reimbursement.getAmount());
			cs.execute();
			rs = cs.getResultSet();
			while (rs.next()) {
				id = rs.getInt(1);
				reimbursement.setId(id);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return id;
	}
	
	@Override
	public List<Reimbursement> getAllReimbursements() {
		List<Reimbursement> reimbursements = new ArrayList<>();
		String sql = "select * from Reimbursement";
		ResultSet rs = null;
		try (Connection c = DatabaseConnecter.hardcodedConnection();
				PreparedStatement ps = c.prepareStatement(sql);) {
			rs = ps.executeQuery();
			while (rs.next()) {
				Reimbursement r = new Reimbursement();
				System.out.println(rs.getInt("reimbursement_id"));
				r.setId(rs.getInt("reimbursement_id"));
				r.setEmployeeId(rs.getInt("employee_id"));
				r.setExpenseType(rs.getString("expense_type"));
				r.setAmount(rs.getInt("amount"));
				r.setApproved(rs.getBoolean("approved"));
				reimbursements.add(r);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return reimbursements;
	}
	@Override
	public List<Reimbursement> getReimbursementsOfEmployee(int id) {
		List<Reimbursement> reimbursements = new ArrayList<>();
		ResultSet rs = null;
		String sql = "select * from Reimbursement where employee_id = ?";
		try (Connection c = DatabaseConnecter.hardcodedConnection();
				PreparedStatement ps = c.prepareStatement(sql);){
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (!rs.next()) {
				System.out.println("not result set");
				return reimbursements;
			}
			while (rs.next()) {
				Reimbursement r = new Reimbursement();
				r.setId(rs.getInt("reimbursement_id"));
				r.setEmployeeId(rs.getInt("employee_id"));
				r.setExpenseType(rs.getString("expense_type"));
				r.setAmount(rs.getInt("amount"));
				r.setApproved(rs.getBoolean("approved"));
				reimbursements.add(r);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return reimbursements;
	}
	
	@Override
	public int update(int id, boolean t) {
		String sql = "update Reimbursement set approved = ? where reimbursement_id = ?";
		int updated = 0;
		try (Connection c = DatabaseConnecter.hardcodedConnection();
				PreparedStatement ps = c.prepareStatement(sql);){
			ps.setBoolean(1, t);
			ps.setInt(2, id);
			updated = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return updated;
	}

}
