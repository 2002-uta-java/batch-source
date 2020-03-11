package com.revature.daos;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.LazyInitializationException;
import org.hibernate.NonUniqueObjectException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.revature.models.Cave;
import com.revature.util.HibernateUtil;

public class CaveDaoImpl implements CaveDao {

	@Override
	public List<Cave> getCaves() {
		// HQL query
//		try (final Session session = HibernateUtil.getSession()) {
//			final String hql = "from Cave";
//			final Query<Cave> query = session.createQuery(hql, Cave.class);
//			return query.list();
//		}

		// Criteria query
		try (final Session session = HibernateUtil.getSession()) {
			final CriteriaBuilder critBuilder = session.getCriteriaBuilder();
			final CriteriaQuery<Cave> critQuery = critBuilder.createQuery(Cave.class);

			// pretty sure these two lines are unnecessary (it ran fine without me using
			// root)
			final Root<Cave> root = critQuery.from(Cave.class);
			critQuery.select(root);

			final Query<Cave> query = session.createQuery(critQuery);
			return query.list();
		}
	}

	/**
	 * Querying an object with a particular ID <br>
	 * (1) Query + native sql, hql, named query or criteria query <br>
	 * 
	 * (2) get <br>
	 * - eagerly fetches object <br>
	 * - If id doesn't exist, returns null (3) <br>
	 * 
	 * load <br>
	 * -lazily fetches object, data is reprsented as a proxy (anynomous subclass of
	 * our object) <br>
	 * - if we load an object and we do not access any of its fields within the
	 * scope of the session we cannot access them after the session is closed <br>
	 * - if we attempt to acces data from our proxy in this way, it results in a
	 * {@link LazyInitializationException} <br>
	 * - if we attempt to access an id that doesn't exist, we'll get an exception
	 */
	@Override
	public Cave getCaveById(int id) {
		try (final Session session = HibernateUtil.getSession()) {
			return session.get(Cave.class, id);
		}
	}

	/**
	 * Convert transient object to persistent (the Cave object)
	 * 
	 * save: <br>
	 * - return the serializable id - pk (primary key) of the persisted object <br>
	 * - can make a detached object as well as a transient object persistent
	 * 
	 * persist: <br>
	 * - void return type, no immediate access to generated primary keys <br>
	 * - throws an exception if we attempt to make a detached object persistent <br>
	 * - will not execute non-transactionally
	 * 
	 */
	@Override
	public int createCave(Cave cave) {
		try (final Session session = HibernateUtil.getSession()) {
			final Transaction tx = session.beginTransaction();
			final int pk = (int) session.save(cave);
			tx.commit();
			return pk;
		}
	}

	/**
	 * detached -> persistent state
	 * 
	 * udpdate: <br>
	 * - void return type <br>
	 * - if there's already a persistent object in the session with the same ID a
	 * {@link NonUniqueObjectException} will be thrown.<br>
	 * - an exception is also thrown if you try to update an object that is not in
	 * the database.
	 * 
	 * merge: <br>
	 * - returns merged object <br>
	 * - if there is already a persistent object in the current session with the
	 * same ID the transient object will be merged with the persistent object.
	 */
	@Override
	public void updateCave(Cave cave) {
		try (final Session session = HibernateUtil.getSession()) {
			final Transaction tx = session.beginTransaction();
			session.update(cave);
			tx.commit();
		}
	}

	@Override
	public void deleteCave(Cave cave) {
		try (final Session session = HibernateUtil.getSession()) {
			final Transaction tx = session.beginTransaction();
			session.delete(cave);
			tx.commit();
		}
	}

}
