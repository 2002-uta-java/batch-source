package com.revature.daos;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.revature.models.Bear;
import com.revature.util.HibernateUtil;

public class BearDaoImpl implements BearDao {

	@Override
	public List<Bear> getBears() {
		try(Session s = HibernateUtil.getSession()){
			String hql = "from Bear";
			Query<Bear> bearQuery = s.createQuery(hql, Bear.class);
			List<Bear> bears = bearQuery.list();
			return bears;
		}
	}

	@Override
	public Bear getBearById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int createBear(Bear b) {
		try(Session s = HibernateUtil.getSession()){
			Transaction tx = s.beginTransaction();
			int pk = (int) s.save(b);
			tx.commit();
			return pk;
		}
	}

	@Override
	public void updateBear(Bear b) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteBear(Bear b) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Bear> getBearByName(String name) {
		
		try(Session s = HibernateUtil.getSession()){
			String sql = "select * from bear where bear_name = ?";
			Query<Bear> q = s.createNativeQuery(sql, Bear.class);
			q.setParameter(1, name);
			List<Bear> bears = q.list();
			return bears;
		}
		
		/*
		try(Session s = HibernateUtil.getSession()){
			String hql = "from Bear where name = :nameVar";
			Query<Bear> bearQuery = s.createQuery(hql, Bear.class);
			bearQuery.setParameter("nameVar", name);
			List<Bear> bears = bearQuery.list();
			return bears;
		}
		*/
		/*
		try(Session s = HibernateUtil.getSession()){
			Query<Bear> bearQuery = s.getNamedQuery("getByName");
			bearQuery.setParameter("nameVar", name);
			List<Bear> bears = bearQuery.list();
			return bears;
		}
		
		
		try(Session s = HibernateUtil.getSession()){
			CriteriaBuilder cb = s.getCriteriaBuilder();
			CriteriaQuery<Bear> cq = cb.createQuery(Bear.class);
			
			Root<Bear> root = cq.from(Bear.class);
			cq.select(root);
			
			cq.where(cb.equal(root.get("name"), name));
			
			Query<Bear> bearQuery = s.createQuery(cq);
			List<Bear> bears = bearQuery.list();
			return bears;
			
		}
		*/
	}

	@Override
	public long getBearCount() {
		try(Session s = HibernateUtil.getSession()){
			CriteriaBuilder cb = s.getCriteriaBuilder();
			CriteriaQuery<Long> cq = cb.createQuery(Long.class);
			
			Root<Bear> root = cq.from(Bear.class);
			cq.select(cb.count(root));
			
			Query<Long> countQuery = s.createQuery(cq);
			Long count = countQuery.getSingleResult();
			return count;
		}
	}

}
