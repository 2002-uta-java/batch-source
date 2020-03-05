package com.revature.daos;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

import com.revature.models.Reimbursement;
import com.revature.models.User;
import com.revature.utils.ConnectionUtil;

public class ReimbursementDAOImpl implements ReimbursementDAO {

	@Override
	public List<Reimbursement> getAll() {
		List<Reimbursement> rl = new ArrayList<Reimbursement>();
		
		String query = "select r.id, description, status, amount, p.username from reimbursements r left join person p on r.user_id = p.id;";
		ResultSet rs = null;
		
		try (Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(query)) {
			//ps.setInt(1, a.getId());

			rs = ps.executeQuery();

			while (rs.next()) {
				Reimbursement r = new Reimbursement();
				/*
				 * private int id;
	private String description;
	private String status;
	private BigDecimal amount;
	private User user;
	private User manager;
				 */
				r.setId(rs.getInt("id"));
				r.setDescription(rs.getString("description"));
				r.setStatus(rs.getString("status"));
				r.setAmount(rs.getBigDecimal("amount"));
				User u = new User();
				u.setUsername(rs.getString("username"));
				r.setUser(u);
				rl.add(r);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ServletException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return rl;
	}

}
