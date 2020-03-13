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

	private static final long serialVersionUID = 1L;

	@Autowired
	private SessionFactory sessionFactory;

	// just a read operation
	@Transactional(propagation = Propagation.SUPPORTS)
	@Override
	public List<Employee> getAll() {
		final Session session = sessionFactory.getCurrentSession();
		return session.createQuery("from Employee").list();
	}

	@Transactional
	@Override
	public Employee getById(int id) {
		final Session session = sessionFactory.getCurrentSession();
		return (Employee) session.get(Employee.class, id);
	}

}
