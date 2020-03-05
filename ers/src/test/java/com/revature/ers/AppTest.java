package com.revature.ers;

import static org.junit.Assert.*;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import com.revature.dao.*;
import com.revature.model.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AppTest {
	
	EmployeeDAO edao = new EmployeeDaoImpl();
	
	 @Test
	 @Order(1)
	 public void testGetEmployee() {
		 Employee em = new Employee(6, "Danny", "Stormborn", "deathtoall@gmail.com", "6158898889", "1234", true);
		 Employee testem = edao.getEmployee(6);
		 assertEquals(em, testem);
	 }
	 
	 @Test
	 @Order(2)
	 public void testAddEmployee() throws Exception {
		 Employee em = new Employee();
		 em.setfName("Bob"); em.setlName("Jenkins"); em.setEmail("jankyganker@gmail.com"); em.setPhone("6155563322"); em.setPass("1234"); em.setIsManager(true);
		 edao.addEmployee(em);
		 Employee testem = edao.getEmployeeByUsername("jankyganker@gmail.com");
		 em.setId(testem.getId());
		 assertEquals(em, testem);
	 }
	 
	 
	 @Test
	 @Order(3)
	 public void testDeleteEmployee() throws Exception {
		 Employee deleteem = edao.getEmployeeByUsername("jankyganker@gmail.com");
		 edao.deleteEmployee(deleteem.getId());
		 Employee em = new Employee();
		 Employee testem = edao.getEmployeeByUsername("jankyGanker@gmail.com");
		 assertEquals(em, testem);
	 }
}
