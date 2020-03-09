package com.revature;

import org.hibernate.Session;

import com.revature.daos.BearDao;
import com.revature.daos.BearDaoImpl;
import com.revature.daos.CaveDao;
import com.revature.daos.CaveDaoImpl;
import com.revature.models.Bear;
import com.revature.models.Cave;
import com.revature.util.HibernateUtil;

public class Driver {

	public static void main(String[] args) {
//		Session s = HibernateUtil.getSession();
//		// perform some db operation
//		s.close();
		
		CaveDao caveDao = new CaveDaoImpl();
		Cave c1 = new Cave(1, "Mammoth Cave");
		Cave c2 = new Cave(2, "Plato's Cave");
		Cave c3 = new Cave(3, "Carlsbad Caverns");
		
//		System.out.println(caveDao.createCave(c1));
//		System.out.println(caveDao.createCave(c2));
//		System.out.println(caveDao.createCave(c3));
		
//		System.out.println(caveDao.getCaveById(2));
		
//		System.out.println(caveDao.getCaves());
		
//		caveDao.updateCave(new Cave(2, "Not Plato's Cave"));
		
//		caveDao.deleteCave(new Cave(2, "Not Plato's Cave"));
		
		Bear b1 = new Bear("Winnie", c1);
		Bear b2 = new Bear("Yogi", new Cave(6, "Cool New Cave"));
		Bear b3 = new Bear("Smokey (the Bear)", c1);
		BearDao bearDao = new BearDaoImpl();
//		bearDao.createBear(b1);
//		bearDao.createBear(b2);
//		bearDao.createBear(b3);
		
//		System.out.println(bearDao.getBears());
//		System.out.println(bearDao.getBearByName("Yogi"));
		System.out.println(bearDao.getBearCount());

		

	}

}
