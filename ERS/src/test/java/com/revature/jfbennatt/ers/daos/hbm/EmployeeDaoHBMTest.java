package com.revature.jfbennatt.ers.daos.hbm;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class EmployeeDaoHBMTest {
	@Test
	public void testDeleteSessionTokenValid() {
		final EmployeeDaoHBM empDao = new EmployeeDaoHBM();
		final String token = "8Y3llBZJCQ1ZjGYDqUdpLYK3sPeipjctOprSyE8a7mlvkiDPvMHdKQGlDRTpI6UC";
		assertTrue(empDao.deleteSessionToken(token));
	}
}
