package com.revature.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Status;
import com.revature.util.ConnectionUtil;

public class StatusDaoImpl implements StatusDao {

	@Override
	public List<Status> getAllStatus() {
		
		String sql = "select * from status";
		
		List<Status> staList = new ArrayList<>();
		
		try (Connection con = ConnectionUtil.getConnection();
				Statement s = con.createStatement();
				ResultSet rs = s.executeQuery(sql)){
			
			while (rs.next()) {
				
				Status sta = new Status();
				sta.setId(rs.getInt("id"));
				sta.setName(rs.getString("sta_name"));
				
				staList.add(sta);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return staList;
	}
}
