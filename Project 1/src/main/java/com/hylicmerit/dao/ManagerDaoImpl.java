package com.hylicmerit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.hylicmerit.ConnectionUtil;
import com.hylicmerit.models.Manager;
import com.hylicmerit.models.Worker;

public class ManagerDaoImpl implements WorkerDao {

	@Override
	public List<Worker> getAll() {
		String sql = "select * from manager";
		List<Worker> managers = new ArrayList<>();
		try (Connection c = ConnectionUtil.getConnection();
				Statement s = c.createStatement();
				ResultSet rs = s.executeQuery(sql);) {
			while (rs.next()) {
				String email = rs.getString("manager_email");
				String password = rs.getString("password");
				String name = rs.getString("name");
				String bio = rs.getString("bio");
				String birthday = rs.getString("birthday");

				Worker w = new Manager(email, password, name, bio, birthday);

				managers.add(w);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return managers;
	}

	@Override
	public Worker getById(String manager_email) {
		String sql = "select * from manager where manager_email = ?";
		ResultSet rs = null;
		Worker w = null;
		try (Connection c = ConnectionUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
			ps.setString(1, manager_email);
			rs = ps.executeQuery();
			while (rs.next()) {
				String email = rs.getString("manager_email");
				String password = rs.getString("password");
				String name = rs.getString("name");
				String bio = rs.getString("bio");
				String birthday = rs.getString("birthday");

				w = new Manager(email, password, name, bio, birthday);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return w;
	}

	@Override
	public int create(Worker w) {
		String sql = "insert into manager (manager_email, password, name, bio, birthday) values (?,?,?,?,?)";
		int numRowsAffected = 0;
		try (Connection c = ConnectionUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
			numRowsAffected = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return numRowsAffected;
	}

	@Override
	public int update(Worker w) {
		String sql = "update manager set password = ?, name = ?, bio = ?, birthday = ? where manager_email = ?";
		int numRowsAffected = 0;
		try (Connection c = ConnectionUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
			numRowsAffected = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return numRowsAffected;
	}

	@Override
	public int delete(Worker w) {
		String sql = "delete from manager where manager_email = ?";
		int numRowsAffected = 0;
		try (Connection c = ConnectionUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
			numRowsAffected = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return numRowsAffected;
	}

}
