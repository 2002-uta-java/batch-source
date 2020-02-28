package com.hylicmerit.dao;

import java.util.List;
import com.hylicmerit.models.Worker;

public interface WorkerDao {

	List<Worker> getAll();
	
	Worker getById(String email);
	
	int create(Worker w);
	
	int update(Worker w);
	
	int delete(Worker w);
}
