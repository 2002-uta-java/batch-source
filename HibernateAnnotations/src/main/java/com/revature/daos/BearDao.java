package com.revature.daos;

import java.util.List;

import com.revature.models.Bear;

public interface BearDao {
	public List<Bear> getBears();

	public Bear getBearById(int id);

	public int createBear(Bear bear);

	public void updateBear(Bear bear);

	public void deleteBear(Bear bear);

	public Bear getBearByName(String name);

	public default void createBears(Bear... bears) {
		for (final Bear bear : bears)
			createBear(bear);
	}

	public default void deleteBears(final Bear... bears) {
		for (final Bear bear : bears)
			deleteBear(bear);
	}
}
