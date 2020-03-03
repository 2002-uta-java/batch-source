package com.revature.dao;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Category;
import com.revature.util.ConnectionUtil;

public class CategoryDaoImpl implements CategoryDao{

	@Override
	public List<Category> getAllCategories() {
		
		String sql = "select * from category";
		
		List<Category> catList = new ArrayList<>();
		
		try (Connection con = ConnectionUtil.getConnection();
				Statement s = con.createStatement();
				ResultSet rs = s.executeQuery(sql)){
			
			while (rs.next()) {
				
				Category cat = new Category();
				cat.setId(rs.getInt("id"));
				cat.setName(rs.getString("cat_name"));
				
				catList.add(cat);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return catList;
	}

}
