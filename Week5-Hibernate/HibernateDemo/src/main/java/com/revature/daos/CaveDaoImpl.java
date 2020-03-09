package com.revature.daos;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.models.Cave;
import com.revature.util.HibernateUtil;

public class CaveDaoImpl implements CaveDao {

	
	@Override
	public List<Cave> getCaves() {
		/*
		List<Cave> caves = null;
		try(Session s = HibernateUtil.getSession()){
			String hql = "from Cave";
			Query<Cave> q = s.createQuery(hql, Cave.class);
			caves = q.list();
		}
		return caves;
		*/
		try(Session s = HibernateUtil.getSession()){
			CriteriaBuilder cb = s.getCriteriaBuilder();
			CriteriaQuery<Cave> cq = cb.createQuery(Cave.class); 
			
			Root<Cave> root = cq.from(Cave.class);
			cq.select(root);
			
			Query<Cave> q = s.createQuery(cq);
			List<Cave> caves = q.list();
			
			return caves;
		}
	}

	/* Querying an object with a particular ID 
	 * (1) Query + native sql, hql, named query or criteria query
	 * (2) get
	 * 		- eagerly fetches object
	 * 		- returns null when getting an id not in db
	 * (3) load 
	 * 		- lazily fetches object, data is represented as a proxy (anonymous subclass of our object)
	 * 		- if we load an object and we do not access any of its fields within the scope of the session
	 * 			we cannot access them after the session is closed
	 * 		- if we attempt to access data from our proxy in this way, it results in a LazyInitializationException
	 * 		- if we attempt to access an object that is not in the db, ObjectNotFoundException is thrown
	 */
	@Override
	public Cave getCaveById(int id) {
		Cave c = null;
		try(Session s = HibernateUtil.getSession()){
			c = s.load(Cave.class, id);
			System.out.println(c);
		}
		return c;
	}

	/*
	 * transient -> persistent 
	 * 
	 * save: 
	 * - return the serializable id - pk of the persisted object
	 * - can make a detached object as well as a transient object persistent
	 * 
	 * persist: 
	 * - void return type, no immediate access to generated primary keys
	 * - throws an exception if we attempt to make a detached object persistent 
	 * - will not execute non transactionally
	 * 
	 */
	@Override
	public int createCave(Cave c) {
		int pk = 0;
		try (Session s = HibernateUtil.getSession();){
			Transaction tx = s.beginTransaction();
			pk = (int) s.save(c);
			tx.commit();
		}
		return pk;
	}

	/* detached -> persistent state
	 * 
	 * update: 
	 * - void return type
	 * - if there's already a persistent object in the session with the same ID
	 * 		a NonUniqueObjectException is thrown
	 * - an exception is also thrown if you try to update an object that has an ID
	 * 		that's not in the db
	 * 
	 * merge: 
	 * - returns merged object
	 * - if there is already a persistent object in the current session with the same ID
	 * 		the transient object will be merged with the persistent object 
	 * 
	 *  */
	
	@Override
	public void updateCave(Cave c) {
		try(Session s = HibernateUtil.getSession()){
			Transaction tx = s.beginTransaction();
			s.update(c);
			tx.commit();
		}
		
	}

	@Override
	public void deleteCave(Cave c) {
		try(Session s = HibernateUtil.getSession()){
			Transaction tx = s.beginTransaction();
			s.delete(c);
			tx.commit();
		}		
	}

}
