package com.revature.dao;

import java.sql.Connection;
import java.sql.Statement;

import com.revature.util.ConnectionUtil;

public class UserToBankAccountDaoImpl implements UserToBankAccountDao{

	@Override
	public void establishAssociation(int userAccountId, int bankAccountId) {
		
		String sql = "insert into user_to_bank_account (user_id, account_id) values (" + userAccountId + "," + bankAccountId + ")";
		
		try (Connection con = ConnectionUtil.getConnection();
				Statement s = con.createStatement()){
			
			s.execute(sql);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
