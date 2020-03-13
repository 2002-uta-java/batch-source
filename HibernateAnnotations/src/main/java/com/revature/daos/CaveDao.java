package com.revature.daos;

import java.util.List;

import com.revature.models.Cave;

public interface CaveDao {
	public List<Cave> getCaves();

	public Cave getCaveById(int id);

	public int createCave(Cave cave);

	public void updateCave(Cave cave);

	public void deleteCave(Cave cave);

	public default void createCaves(Cave... caves) {
		for (final Cave cave : caves)
			createCave(cave);
	}

	public default void deleteCaves(final Cave... caves) {
		for (final Cave cave : caves)
			deleteCave(cave);
	}
}
