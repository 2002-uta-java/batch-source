package com.dean.daos;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.dean.models.Reimbursement;
import com.dean.util.ConnectionUtil;

public class ReimbursementDaoImpl implements ReimbursementDao {

	@Override
	public int createReimbursement(Reimbursement reimbursement) {
		int reimbursementsCreated = 0;
		String sql = "INSERT INTO REIMBURSEMENT (ERB_DESC, ERB_AMOUNT, ERB_STATUS, EMP_ID) VALUES (?,?,?,?)";

		try (Connection con = ConnectionUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
//			ps.setInt(1, reimbursement.getReimbursementId());
			ps.setString(1, reimbursement.getReimbursementDescription());
			ps.setFloat(2, reimbursement.getReimbursementAmount());
			ps.setString(3, reimbursement.getReimbursementStatus());
//			ps.setInt(5, reimbursement.getManagerId());
			ps.setInt(4, reimbursement.getEmployeeId());

			reimbursementsCreated = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		if (reimbursementsCreated > 0) {
			System.out.println("Congratulations, your request has been successfully submitted!");
		}

		return reimbursementsCreated;
	}
	
	

	@Override
	public int approveReimbursementByErbId(int id) {
		int approvedReimbursements = 0;
		String sql = "UPDATE REIMBURSEMENT SET ERB_STATUS = 'APPROVED', MANAGER_ID=2 WHERE ERB_ID = ?";
		try (Connection con = ConnectionUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, id);
			approvedReimbursements = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return approvedReimbursements;
	}

	@Override
	// deny reimbursement by id
	public int denyReimbursementByErbId(int id) {
		int deniedReimbursements = 0;
		
		String sql = "UPDATE REIMBURSEMENT SET ERB_STATUS = 'DENIED',MANAGER_ID=2 WHERE ERB_ID = ?";
		try (Connection con = ConnectionUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, id);
			
			deniedReimbursements = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return deniedReimbursements;
	}

	@Override
	public Reimbursement getReimbursementById(int id) {
		Reimbursement r = new Reimbursement();
		ResultSet rs = null;
		String sql = "SELECT * FROM REIMBURSEMENT WHERE ERB_ID = ?";

		try (Connection con = ConnectionUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setInt(1, id);
			rs = ps.executeQuery();

			while (rs.next()) {
				r.setReimbursementId(rs.getInt("ERB_ID"));
				r.setReimbursementDescription(rs.getString("ERB_DESC"));
				r.setReimbursementAmount(rs.getFloat("ERB_AMOUNT"));
				r.setReimbursementStatus(rs.getString("ERB_STATUS"));
				r.setManagerId(rs.getInt("MANAGER_ID"));
				r.setEmployeeId(rs.getInt("EMP_ID"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return r;
	}

	@Override
	public List<Reimbursement> getAllPendingReimbursementsByEmployeeId(int id) {

		List<Reimbursement> r = new ArrayList<Reimbursement>();
		ResultSet rs = null;
		String sql = "SELECT * FROM REIMBURSEMENT WHERE ERB_STATUS = 'PENDING' AND EMP_ID = ?";
		try (Connection con = ConnectionUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {

			ps.setInt(1, id);
			rs = ps.executeQuery(sql);

			while (rs.next()) {
				Reimbursement reimbursement = new Reimbursement();
				reimbursement.setReimbursementId(rs.getInt("ERB_ID"));
				reimbursement.setReimbursementDescription(rs.getString("ERB_DESC"));
				reimbursement.setReimbursementAmount(rs.getFloat("ERB_AMOUNT"));
				reimbursement.setReimbursementStatus(rs.getString("ERB_STATUS"));
				reimbursement.setManagerId(rs.getInt("MANAGER_ID"));
				reimbursement.setEmployeeId(rs.getInt("EMP_ID"));
				r.add(reimbursement);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return r;
	}

	@Override
	public List<Reimbursement> getAllResolvedReimbursementsEmployeeId(int id) {
		List<Reimbursement> r = new ArrayList<Reimbursement>();
		ResultSet rs = null;
		String sql = "SELECT * FROM REIMBURSEMENT WHERE ERB_STATUS = 'APPROVED' OR ERB_STATUS = 'DENIED' AND EMP_ID = ?";

		try (Connection con = ConnectionUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {

			ps.setInt(1, id);
			rs = ps.executeQuery(sql);

			while (rs.next()) {
				Reimbursement reimbursement = new Reimbursement();
				reimbursement.setReimbursementId(rs.getInt("ERB_ID"));
				reimbursement.setReimbursementDescription(rs.getString("ERB_DESC"));
				reimbursement.setReimbursementAmount(rs.getFloat("ERB_AMOUNT"));
				reimbursement.setReimbursementStatus(rs.getString("ERB_STATUS"));
				reimbursement.setManagerId(rs.getInt("MANAGER_ID"));
				reimbursement.setEmployeeId(rs.getInt("EMP_ID"));
				r.add(reimbursement);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return r;
	}

	@Override
	public List<Reimbursement> getReimbursementsByEmployeeId(int id) {
		List<Reimbursement> r = new ArrayList<Reimbursement>();

		ResultSet rs = null;
		String sql = "SELECT * FROM REIMBURSEMENT WHERE EMP_ID = ?";

		try (Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);) {

			ps.setInt(1, id);
			rs = ps.executeQuery();

			while (rs.next()) {
				Reimbursement reimbursement = new Reimbursement();
				reimbursement.setReimbursementId(rs.getInt("ERB_ID"));
				reimbursement.setReimbursementDescription(rs.getString("ERB_DESC"));
				reimbursement.setReimbursementAmount(rs.getFloat("ERB_AMOUNT"));
				reimbursement.setReimbursementStatus(rs.getString("ERB_STATUS"));
				reimbursement.setManagerId(rs.getInt("MANAGER_ID"));
				reimbursement.setEmployeeId(rs.getInt("EMP_ID"));
				r.add(reimbursement);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return r;
	}

	@Override
	public List<Reimbursement> getAllPendingReimbursements() {
		List<Reimbursement> r = new ArrayList<Reimbursement>();

		ResultSet rs = null;
		String sql = "SELECT * FROM REIMBURSEMENT WHERE ERB_STATUS = 'PENDING'";

		try (Connection con = ConnectionUtil.getConnection(); Statement s = con.createStatement();) {
			rs = s.executeQuery(sql);

			while (rs.next()) {
				Reimbursement reimbursement = new Reimbursement();
				reimbursement.setReimbursementId(rs.getInt("ERB_ID"));
				reimbursement.setReimbursementDescription(rs.getString("ERB_DESC"));
				reimbursement.setReimbursementAmount(rs.getFloat("ERB_AMOUNT"));
				reimbursement.setReimbursementStatus(rs.getString("ERB_STATUS"));
				reimbursement.setManagerId(rs.getInt("MANAGER_ID"));
				reimbursement.setEmployeeId(rs.getInt("EMP_ID"));
				r.add(reimbursement);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return r;
	}

	@Override
	public List<Reimbursement> getAllResolvedReimbursements() {
		List<Reimbursement> r = new ArrayList<Reimbursement>();

		ResultSet rs = null;
		String sql = "SELECT * FROM REIMBURSEMENT WHERE ERB_STATUS = 'APPROVED' OR ERB_STATUS = 'DENIED'";
		try (Connection con = ConnectionUtil.getConnection(); Statement s = con.createStatement();) {
			rs = s.executeQuery(sql);

			while (rs.next()) {
				Reimbursement reimbursement = new Reimbursement();
				reimbursement.setReimbursementId(rs.getInt("ERB_ID"));
				reimbursement.setReimbursementDescription(rs.getString("ERB_DESC"));
				reimbursement.setReimbursementAmount(rs.getFloat("ERB_AMOUNT"));
				reimbursement.setReimbursementStatus(rs.getString("ERB_STATUS"));
				reimbursement.setManagerId(rs.getInt("MANAGER_ID"));
				reimbursement.setEmployeeId(rs.getInt("EMP_ID"));
				r.add(reimbursement);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return r;
	}

	@Override
	public List<Reimbursement> getAllReimbursements() {
		List<Reimbursement> reimbursementsList = new ArrayList<Reimbursement>();

		String sql = "SELECT * FROM REIMBURSEMENT";

		ResultSet rs = null;

		try (Connection con = ConnectionUtil.getConnection();
				Statement s = con.createStatement();) {

			rs = s.executeQuery(sql);

			while (rs.next()) {
				Reimbursement r = new Reimbursement();

				r.setReimbursementId(rs.getInt("ERB_ID"));
				r.setReimbursementDescription(rs.getString("ERB_DESC"));
				r.setReimbursementAmount(rs.getFloat("ERB_AMOUNT"));
				r.setReimbursementStatus(rs.getString("ERB_STATUS"));
				r.setManagerId(rs.getInt("MANAGER_ID"));
				r.setEmployeeId(rs.getInt("EMP_ID"));

				reimbursementsList.add(r);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return reimbursementsList;
	}

	@Override
	public List<Reimbursement> getAllDeniedReimbursements() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
	
	
	@Override
	public List<Reimbursement> getReimbursementsByEmployeeUsername(String username) {
		List<Reimbursement> r = new ArrayList<Reimbursement>();

		ResultSet rs = null;
		String sql = "SELECT * FROM REIMBURSEMENT WHERE EMP_USERNAME = ?";

		try (Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);) {

			ps.setString(1, username);
			rs = ps.executeQuery();

			while (rs.next()) {
				Reimbursement reimbursement = new Reimbursement();
				reimbursement.setReimbursementId(rs.getInt("ERB_ID"));
				reimbursement.setReimbursementDescription(rs.getString("ERB_DESC"));
				reimbursement.setReimbursementAmount(rs.getFloat("ERB_AMOUNT"));
				reimbursement.setReimbursementStatus(rs.getString("ERB_STATUS"));
				reimbursement.setManagerId(rs.getInt("MANAGER_ID"));
				reimbursement.setEmployeeId(rs.getInt("EMP_ID"));
				r.add(reimbursement);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return r;
	}

}
