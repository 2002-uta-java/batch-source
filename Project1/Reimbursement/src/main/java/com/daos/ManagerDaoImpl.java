package com.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.models.Manager;
import com.revature.util.ConnectionUtil;

public class ManagerDaoImpl implements ManagerDao {
	public static Logger log = Logger.getRootLogger();
	@Override
	public Manager getManagerById(int id) {
		Manager m = new Manager();

		String sql = "select * from p1_manager where man_id = ?";
		ResultSet rs = null;

		try (Connection con = ConnectionUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setInt(1, id);

			rs = ps.executeQuery();

			while (rs.next()) {
				int manId = rs.getInt("man_id");
				m.setId(manId);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return m;
	}

	@Override
	public List<Manager> getManagers() {

		List<Manager> manList = new ArrayList<Manager>();

		String sql = "select * from p1_manager";
		try (Connection con = ConnectionUtil.getConnection(); Statement s = con.createStatement();) {
			ResultSet rs = s.executeQuery(sql);

			while (rs.next()) {
				Manager m = new Manager();
				int manId = rs.getInt("man_id");
				m.setId(manId);

				manList.add(m);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return manList;
	}

// unimplemented methods
	@Override
    public int createManager(Manager manager) {
		int mansCreated = 0;
//
//		String sql = "insert into  ";
	return mansCreated;
	}

}
