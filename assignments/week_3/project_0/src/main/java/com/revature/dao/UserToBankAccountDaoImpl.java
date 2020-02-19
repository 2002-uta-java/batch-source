package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.revature.util.ConnectionUtil;

public class UserToBankAccountDaoImpl implements UserToBankAccountDao{

	@Override
	public boolean establishAssociation(int userAccountId, int bankAccountId) {
		
		boolean didItExecute = false;
		int queryResult = 0;
		
		String sql = "insert into user_to_bank_account (user_id, account_id) values (?, ?)";
		
		try (Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)){
			
			ps.setInt(1, userAccountId);
			ps.setInt(2, bankAccountId);
			
			queryResult = ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(queryResult > 0) {
			didItExecute = true;
		}
		
		return didItExecute;
	}
}
