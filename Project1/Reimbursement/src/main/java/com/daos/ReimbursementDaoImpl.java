package com.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.models.Reimbursement;
import com.revature.util.ConnectionUtil;

public class ReimbursementDaoImpl implements ReimbursementDao {
	public static Logger log = Logger.getRootLogger();
	@Override
	public Reimbursement getReimbursementByRId(int RId) {
		Reimbursement r = new Reimbursement();

		String sql = "select * from p1_reimbursement where r_id = ?";
		ResultSet rs = null;

		try (Connection con = ConnectionUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setInt(1, RId);

			rs = ps.executeQuery();

			while (rs.next()) {
				int reimbId = rs.getInt("r_id");
				r.setRId(reimbId);

				int empId = rs.getInt("emp_id");
				r.setEmpId(empId);

				int manId = rs.getInt("man_id");
				r.setManId(manId);

				String status = rs.getString("status");
				r.setStatus(status);

				String action = rs.getString("act");
				r.setAction(action);

				String desc = rs.getString("description");
				r.setDescription(desc);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return r;
	}

	@Override
	public List<Reimbursement> getReimbursements() {
		List<Reimbursement> reimbList = new ArrayList<Reimbursement>();

		String sql = "select * from p1_reimbursement";
		try (Connection con = ConnectionUtil.getConnection(); Statement s = con.createStatement();) {
			ResultSet rs = s.executeQuery(sql);

			while (rs.next()) {
				Reimbursement r = new Reimbursement();
				int reimbId = rs.getInt("r_id");
				r.setRId(reimbId);

				int empId = rs.getInt("emp_id");
				r.setEmpId(empId);

				int manId = rs.getInt("man_id");
				r.setManId(manId);

				String status = rs.getString("status");
				r.setStatus(status);

				String action = rs.getString("act");
				r.setAction(action);

				String desc = rs.getString("description");
				r.setDescription(desc);

				reimbList.add(r);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return reimbList;
	}

	@Override
	public List<Reimbursement> getReimbursementsByEmpId(int empId) {

		List<Reimbursement> reimbList = new ArrayList<>();
		String sql = "select * from p1_reimbursement where emp_id = ?";
		ResultSet rs = null;

		try (Connection con = ConnectionUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setInt(1, empId);

			rs = ps.executeQuery();

			while (rs.next()) {
				Reimbursement r = new Reimbursement();
				int reimbId = rs.getInt("r_id");
				r.setRId(reimbId);

				r.setEmpId(empId);

				int manId = rs.getInt("man_id");
				r.setManId(manId);

				String status = rs.getString("status");
				r.setStatus(status);

				String action = rs.getString("act");
				r.setAction(action);

				String desc = rs.getString("description");
				r.setDescription(desc);
				reimbList.add(r);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return reimbList;
	}

	@Override
	public int createReimbursement(Reimbursement reimb) {
		int reimbCreated = 0;

		String sql = "insert into p1_reimbursement(emp_id, man_id, "
				+ "status, act, description) values (?, ?, ?, ?, ?)";

		try (Connection con = ConnectionUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setInt(1, reimb.getEmpId());
			ps.setInt(2, reimb.getManId());
			ps.setString(3, reimb.getStatus());
			ps.setString(4, reimb.getAction());
			ps.setString(5, reimb.getDescription());
			reimbCreated = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log.info(reimb);
		return reimbCreated;
	}

	@Override
	public int updateReimbursement(Reimbursement reimb) {

		int reimbUpdated = 0;

		String sql = "update p1_reimbursement" + " set emp_id = ? ," + " man_id = ? ," + " status = ? ," + " act = ? ,"
				+ " description = ? " + "where r_id = ?";
		try (Connection con = ConnectionUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql); ) {
			
			ps.setInt(1, reimb.getEmpId());
			ps.setInt(2, reimb.getManId());
			ps.setString(3, reimb.getStatus());
			ps.setString(4, reimb.getAction());
			ps.setString(5, reimb.getDescription());
			ps.setInt(6, reimb.getRId());
			reimbUpdated = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		    log.info(reimb);
			return reimbUpdated;
	}
}
