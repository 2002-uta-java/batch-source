package com.revature.daos;

import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;

import com.revature.models.Manager;
import com.revature.util.ConnectionUtil;

public class ManagerDaoImpl implements ManagerDao {

	/**
	 * getting our manager by name
	 */
	public Manager getMangerByName(String name) {
		String sql = "select * form \"Manager\" where \"name\" = ?";

		try (Connection c = ConnectionUtil.getHardConnection(); PreparedStatement ps = c.prepareStatement(sql)) {

			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				String name1 = rs.getString("name");
				String passWord = rs.getString("passWord");
				String email = rs.getString("email");
				int aprovedRequests = rs.getInt("aprovedRequests");
				int empId = rs.getInt("emp_id");

				Manager m = new Manager(name1, passWord, email, aprovedRequests, empId);

				return m;

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * getting our manager by request
	 */
	public Manager getManagerByRequest(String reqeustId) {
		// TODO Auto-generated method stub
		String sql = "select * form \"REQUEST\" where \"aproved_by\" = ?";

		try (Connection c = ConnectionUtil.getHardConnection(); PreparedStatement ps = c.prepareStatement(sql)) {

			ps.setString(1, reqeustId);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				String name1 = rs.getString("name");
				String passWord = rs.getString("passWord");
				String email = rs.getString("email");
				int aprovedRequests = rs.getInt("aprovedRequests");
				int empId = rs.getInt("emp_id");

				Manager m = new Manager(name1, passWord, email, aprovedRequests, empId);
				return m;

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * querying our DB for the manger with a specific name and password
	 */
	public Manager getManagerNameAndPassword(String name, String password) {

		String sql = "select * form \"Manager\" where \"name\" = ? and \"passWord\" =?";

		try (Connection c = ConnectionUtil.getHardConnection(); PreparedStatement ps = c.prepareStatement(sql)) {

			ps.setString(1, name);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				String name1 = rs.getString("name");
				String passWord = rs.getString("passWord");
				String email = rs.getString("email");
				int aprovedRequests = rs.getInt("aprovedRequests");

				int empId = rs.getInt("emp_id");

				Manager m = new Manager(name1, passWord, email, aprovedRequests, empId);
				return m;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
/**
 * returning a list of managers from our database
 */
	public List<Manager> getAllManagers() {
		

		String sql = "select * from \"Manager\" order by \"name\"";
		List<Manager> managers = new ArrayList<Manager>();
		try (Connection c = ConnectionUtil.getHardConnection();
				Statement s = c.createStatement();
				ResultSet rs = s.executeQuery(sql)) {

			while (rs.next()) {
				// getting our manager atributes from the database and
				// putting them into our managers list
				String name = rs.getString("name");
				String passWord = rs.getString("passWord");
				String email = rs.getString("email");
				int aprovedRequests = rs.getInt("aprovedRequests");

				int empId = rs.getInt("emp_id");

				Manager m = new Manager(name, passWord, email, aprovedRequests, empId);
				managers.add(m);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return managers;
	}

}
