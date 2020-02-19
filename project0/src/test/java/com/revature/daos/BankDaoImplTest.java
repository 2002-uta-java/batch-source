/**
 * 
 */
package com.revature.daos;

import static org.junit.Assert.*;


import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.revature.doas.BankDaoImpl;
import com.revature.models.Bank;


/**
 * @author erpac
 *
 */
public class BankDaoImplTest {
	
	private static final BankDaoImpl bDi = new BankDaoImpl();
	private static final Bank b = new Bank("micky mouse", "disney!", "897B", 30);
	@Rule
	public ExpectedException expectedException = ExpectedException.none();
	

	
//	/**testing Create Account Method
//	 * 
//	 */
	
	@Test
	public void successfulCreation() {
		assertEquals(1, bDi.createAccount(b));	
		}
//	
//	/**testing depositMoney
//	 * 
//	 * 
//	 */
	//testing negative input
	@Test
	public void negeativeDeposit() {
		assertEquals(0, bDi.depositMoney(b, -4));	
		}
//	/**
//	 * testing WithdrawMoney
//	 */
	@Test
	public void negeativeWithDraw() {
		assertEquals(0, bDi.withdrawMoney(b, -4));	
		}
//	/**testing checkUserNameAndPassWord
//	 * 
//	 */
	@Test
	public void Wrongpassword() {
		assertFalse(bDi.checkUserNameAndPassWord("Eric Pacheco", "toomm", b));	
		}
	
}	


