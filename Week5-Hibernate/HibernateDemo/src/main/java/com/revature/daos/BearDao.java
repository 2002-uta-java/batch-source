package com.revature.daos;

import java.util.List;

import com.revature.models.Bear;

public interface BearDao {
	
	public List<Bear> getBears();
	public Bear getBearById(int id);
	public int createBear(Bear b);
	public void updateBear(Bear b);
	public void deleteBear(Bear b);
	public List<Bear> getBearByName(String name);
	public long getBearCount();

}
