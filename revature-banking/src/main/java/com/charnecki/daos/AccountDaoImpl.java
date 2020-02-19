package com.charnecki.daos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.charnecki.models.Account;
import com.charnecki.models.Checking;
import com.charnecki.models.Savings;
import com.charnecki.util.ConnectionUtil;

public class AccountDaoImpl implements AccountDao {
	
	private String accTable = "account";

	@Override
	public List<Account> getAllAccounts() {

		List<Account> accounts = new ArrayList<Account>();
		String sql = "select * from {oj " + accTable + " a left join user_account ua on (a.id = ua.account_id)} order by a.id";
		
		try (Connection c = ConnectionUtil.getConnection();
				Statement s = c.createStatement();
				ResultSet rs = s.executeQuery(sql);) {
			
			int prevId = 0;
			
			while(rs.next()) {
				Account a = null;
	
				if (rs.getInt("id") == prevId) {
					a = accounts.get(accounts.size()-1);
				} else {
					
					switch(rs.getString("account_type")) {
						case "savings":
							a = new Savings();
							break;
						case "checking":
							a = new Checking();
							break;
					}
					
					a.setId(rs.getInt("id"));
					a.setBalance(rs.getDouble("balance"));
					
					accounts.add(a);
				}
				
				prevId = a.getId();
				
				List<Integer> holders = a.getHolders();
				
				holders.add(rs.getInt("user_id"));
				
				a.setHolders(holders);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return accounts;
		
	}

	@Override
	public Account getAccountById(int id) {
		
		Account a = null;
		String sql = "select * from {oj " + accTable + " a left join user_account ua on (a.id = ua.account_id) }where a.id = ? order by a.id";
		ResultSet rs = null;
		
		try (Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql);) {
			
			ps.setInt(1, id);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
	
				if (a == null) {
	
					switch(rs.getString("account_type")) {
						case "savings":
							a = new Savings();
							break;
						case "checking":
							a = new Checking();
							break;
						default:
							a = new Savings();
					}
					
					a.setId(id);
					a.setBalance(rs.getDouble("balance"));

				}
				
				List<Integer> holders = a.getHolders();
				
				holders.add(rs.getInt("user_id"));
				
				a.setHolders(holders);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try { if(rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
		}
		
		return a;
	}

	@Override
	public int createAccount(Account a) {
		
		String sql = "{call bank.add_account(?, ?)}";
		ResultSet rs = null;
		
		try (Connection c = ConnectionUtil.getConnection();
				CallableStatement cs = c.prepareCall(sql);) {
			
			cs.setString(1, a.getAccountType());
			cs.setInt(2, a.getHolders().get(0));
			
			cs.execute();
			
			rs = cs.getResultSet();
			
			while(rs.next()) {
				return rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try { if(rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
		}
		

		return 0;
	}

	@Override
	public int updateAccount(Account a) {
		
		String sql = "update " + accTable + " set account_type=?, balance=? where id=?";
		
		try (Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql);) {
			
			ps.setString(1, a.getAccountType());
			ps.setDouble(2, a.getBalance());
			ps.setInt(3, a.getId());
			
			return ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
	}

	@Override
	public int deleteAccount(Account a) {
		
		String sql = "{call bank.remove_account(?)}";
		ResultSet rs = null;
		
		try (Connection c = ConnectionUtil.getConnection();
				CallableStatement cs = c.prepareCall(sql);) {
			
			cs.setInt(1, a.getId());
			
			cs.execute();
			
			rs = cs.getResultSet();
			
			while(rs.next()) {
				return 1;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try { if(rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
		}
		

		return 0;
	}

}
