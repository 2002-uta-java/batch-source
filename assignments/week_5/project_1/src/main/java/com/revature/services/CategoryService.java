package com.revature.services;

import java.util.List;

import com.revature.dao.CategoryDao;
import com.revature.dao.CategoryDaoImpl;
import com.revature.models.Category;

public class CategoryService {
	
	private CategoryDao cd = new CategoryDaoImpl();
	
	public List<Category> getAllCategories() {
		return cd.getAllCategories();
		
	}

}
