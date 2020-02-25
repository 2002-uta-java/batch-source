package com.revature.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.model.Account;
import com.revature.utli.ConnectionUtil;

public class AccountDaoImpl implements AccountDao {

	@Override
	public List<Account> getAccount() {
		String sql = "select * from account";
		
		List<Account> accounts = new ArrayList<>();
		
		try (Connection c = ConnectionUtil.getConnection();
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(sql)) {
		
		   while(rs.next()) {
			   int accountNumber = rs.getInt("account_number");
			   double balance = rs.getDouble("account_balance");
			   int id = rs.getInt("account_id");
			   Account a1 = new Account(id, accountNumber, balance);
			   accounts.add(a1);	   
		   }
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return (List<Account>) accounts;
	}

	@Override
	public int getAccountById(int id) {
		String sql = "select account_id from account where account_id = (select customer_id from customer where customer_id = ?)";
		
		ResultSet rs = null;
		int a = 0;
		
		try(Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql);) {
		   ps.setInt(1, id);
		   rs = ps.executeQuery();
		   while(rs.next()) {
			   a = rs.getInt("account_id");
		   }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try { if (rs!=null) {
				rs.close();
			}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return a;
	}
	@Override
	public int getAccountNumber(int num) {
		String sql = "select account_number from account where account_number = (select customer_id from customer where customer_id = ?)";
		
		ResultSet rs = null;
		int a = 0;
		
		try(Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql);) {
		   ps.setInt(1, num);
		   rs = ps.executeQuery();
		   while(rs.next()) {
			   a = rs.getInt("account_number");
		   }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try { if (rs!=null) {
				rs.close();
			}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return a;
	}

	@Override
	public double getAccountBalance(int num) {
		String sql = "select account_balance from account where account_id = ?";
		
		ResultSet rs = null;
		double a = 0;
		try(Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql);) {
		   ps.setInt(1, num);
		   rs = ps.executeQuery();
		   while(rs.next()) {
			   a = rs.getDouble("account_balance");
			  
		   }
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try { if (rs!=null) {
				rs.close();
			}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return a;
	}

	@Override
	public Account createAccountByFunction(Account a) {
		String sql = "{call account_creation(?, ?, ?)}";
		
		ResultSet rs = null;
		
		try(Connection c = ConnectionUtil.getConnection();
				CallableStatement cs = c.prepareCall(sql)) {
			cs.setInt(1, a.getAccountNumber());
			cs.setDouble(2, a.getAccountBalance());
			cs.setInt(3, a.getId());
			
			cs.execute();
			
			rs = cs.getResultSet();
			while(rs.next()) {
				int account_id = rs.getInt("account_id");
				a.setId(account_id);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try { if (rs!=null) {
				rs.close();
			}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return a;
	}


	@Override
	public Account updateAccountByFunctionAdd(int num, double amount) {
		String sql = "{call deposit_action(?, ?)}";
		
		ResultSet rs = null;
		
		try(Connection c = ConnectionUtil.getConnection();
				CallableStatement cs = c.prepareCall(sql)) {
			cs.setInt(1, num);
			cs.setDouble(2, amount);
			cs.execute();
			
			rs = cs.getResultSet();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try { if (rs!=null) {
				rs.close();
			}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public Account updateAccountByFunctionSub(int num, double amount) {
		String sql = "{call withdraw_action(?, ?)}";
		
		ResultSet rs = null;
		
		try(Connection c = ConnectionUtil.getConnection();
				CallableStatement cs = c.prepareCall(sql)) {
			cs.setInt(1, num);
			cs.setDouble(2, amount);
			cs.execute();
			
			rs = cs.getResultSet();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try { if (rs!=null) {
				rs.close();
			}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

}
