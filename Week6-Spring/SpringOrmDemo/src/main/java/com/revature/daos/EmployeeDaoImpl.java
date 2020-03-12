package com.revature.daos;

import java.util.List;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.revature.models.Employee;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {
	
	@Autowired
	private SessionFactory sf;

	@Transactional(propagation=Propagation.SUPPORTS)
	@Override
	public List<Employee> getAll() {
		Session s = sf.getCurrentSession();
		List<Employee> employees = s.createQuery("from Employee").list();
		return employees;
	}

	@Transactional(propagation=Propagation.SUPPORTS)
	@Override
	public Employee getById(int id) {
		Session s = sf.getCurrentSession();
		Employee e = (Employee) s.get(Employee.class, id);
		return e;
	}

	// any other dao methods with hibernate implementation
	
}
