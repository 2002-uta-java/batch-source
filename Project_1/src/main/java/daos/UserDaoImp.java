package daos;

import java.util.ArrayList;
import java.util.List;


import java.sql.*; 
import util.*;
import util.ConnectionUtil;
import models.Reimbursement;
import models.User;

public class UserDaoImp implements UserDao {

	@Override
	public long createUser(String firstname, String lastname, String username, String email, String password) {
		String sql = "{call create_user(?, ?, ?, ?, ?)}";
		ResultSet result = null;
		try(Connection userConn = ConnectionUtil.getConnection()){
			CallableStatement pcall =  userConn.prepareCall(sql);
			pcall.setString(1, firstname);
			pcall.setString(2, lastname);
			pcall.setString(3, username);
			pcall.setString(4, email);
			pcall.setString(5, password);
			pcall.execute();
			result = pcall.getResultSet();
			if (result.next()) {
				return result.getLong(1);
			} else return 0;
		}catch (SQLException e) {
			System.out.println(e.getMessage());
			//e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int deleteUser(User u) {
		String sql = "{call delete_user(?)}";
		ResultSet result = null;
		try(Connection userConn = ConnectionUtil.getConnection()){
			CallableStatement pcall =  userConn.prepareCall(sql);
			pcall.setLong(1,u.getUid());
			pcall.execute();
			result = pcall.getResultSet();
			if (result.next()) {
				return 1;
			} else return 0;
		}catch (SQLException e) {
			System.out.println(e.getMessage());
			//e.printStackTrace();
			return 0;
		}
	}

	@Override
	public void updateUser(User u, String oldname, String oldpw) {
		String query = "update users u "
				+ "SET "
				+ "firstName = ?, "
				+ "lastName = ?, "
				+ "userName = ?, "
				+ "email = ?, "
				+ "role = ?, "
				+ "password = ?, "
				+ "supervisor = ? "
				+ "where (u.userName = ? and u.password = ?)";
		ResultSet result  = null;
		try(Connection userConn = ConnectionUtil.getConnection()){
			PreparedStatement pstatement =  userConn.prepareStatement(query);
			pstatement.setString(1, u.getFirstname());
			pstatement.setString(2, u.getLastname());
			pstatement.setString(3, u.getUsername());
			pstatement.setString(4, u.getEmail());
			pstatement.setString(5, u.getRole());
			pstatement.setString(6, u.getPassword());
			pstatement.setLong(7,u.getSupervisor());
			pstatement.setString(8, oldname);
			pstatement.setString(9, oldpw);
			pstatement.execute();

		} catch (SQLException e) {
			//TODO: send exception message to log?
			System.out.println(e.getMessage());
			e.printStackTrace();

		}
	}

	@Override
	public User getUserByID(long uid) {
		String query = "select * from users where (UserID=?)";
		ResultSet result  = null;
		
		try(Connection userConn = ConnectionUtil.getConnection()){
			PreparedStatement pstatement =  userConn.prepareStatement(query);
			pstatement.setLong(1, uid);
			result = pstatement.executeQuery();
			if(result.next()) {
				User u = new User();
				u.setFirstname(result.getString("firstName"));
				u.setLastname(result.getString("lastName"));
				u.setUsername(result.getString("userName"));
				u.setEmail(result.getString("email"));
				u.setPassword(result.getString("password"));
				u.setUid(result.getLong("UserID"));
				u.setRole(result.getString("role"));
				u.setSupervisor(result.getLong("Supervisor"));
				return u;
			} else return null;
		}catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public List<User> getUsers() {
		String query = "select * from users";
		List<User> users = new ArrayList<>();
		ResultSet result  = null;
		
		try(Connection userConn = ConnectionUtil.getConnection()){
			PreparedStatement pstatement =  userConn.prepareStatement(query);
			result = pstatement.executeQuery();
			
			while(result.next()) {
				User u = new User();
				u.setFirstname(result.getString("firstName"));
				u.setLastname(result.getString("lastName"));
				u.setUsername(result.getString("userName"));
				u.setEmail(result.getString("email"));
				u.setPassword(result.getString("password"));
				u.setUid(result.getLong("UserID"));
				u.setRole(result.getString("role"));
				u.setSupervisor(result.getLong("Supervisor"));
				users.add(u);
			}
				return users;
		
		}catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return null;
		}
	}
	

	@Override
	public List<User> getUsersBySuper(long uid) {
		String query = "select * from users u where (u.Supervisor = ?)";
		List<User> users = new ArrayList<>();
		ResultSet result  = null;
		
		try(Connection userConn = ConnectionUtil.getConnection()){
			PreparedStatement pstatement =  userConn.prepareStatement(query);
			pstatement.setLong(1, uid);
			result = pstatement.executeQuery();
			
			while(result.next()) {
				User u = new User();
				u.setFirstname(result.getString("firstName"));
				u.setLastname(result.getString("lastName"));
				u.setUsername(result.getString("userName"));
				u.setEmail(result.getString("email"));
				u.setPassword(result.getString("password"));
				u.setUid(result.getLong("UserID"));
				u.setRole(result.getString("role"));
				u.setSupervisor(result.getLong("Supervisor"));
				users.add(u);
			}
				return users;
		
		}catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public User getUserbyCred(String uname, String pw) {
		String query = "select * from users where (username = ? and password = ?)";
		ResultSet result  = null;
		
		try(Connection userConn = ConnectionUtil.getConnection()){
			PreparedStatement pstatement =  userConn.prepareStatement(query);
			pstatement.setString(1, uname);
			pstatement.setString(2, pw);
			result = pstatement.executeQuery();
			
			if(result.next()) {
				long userid = result.getLong("UserID");
				String username = result.getString("UserName");
				String fname = result.getString("firstname");
				String lname = result.getString("lastname");
				String password = result.getString("password");
				String email = result.getString("email");
				User u = new User(fname, lname, username,password, email);
				u.setUid(userid);
				u.setRole(result.getString("role"));
				u.setSupervisor(result.getLong("supervisor"));
				return u;
			} else return null;
		} catch (SQLException e) {
			//TODO: send exception message to log?
			System.out.println(e.getMessage());
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Reimbursement> getMyReimbursements(long uid){
		String query = "select * from Reimbursements r where (r.sourceid = ?)";
		List<Reimbursement> reimbs = new ArrayList<>();
		ResultSet result  = null;
		
		try(Connection userConn = ConnectionUtil.getConnection()){
			PreparedStatement pstatement =  userConn.prepareStatement(query);
			pstatement.setLong(1, uid);
			result = pstatement.executeQuery();
			
			while(result.next()) {
				Reimbursement r = new Reimbursement();
				r.setAmount(result.getLong("amount"));
				r.setRequestid(result.getLong("requestid"));
				r.setSourceid(result.getLong("sourceid"));
				r.setSuperid(result.getLong("superid"));
				r.setStatus(result.getString("status"));
				
				reimbs.add(r);
			}
				return reimbs;
		
		}catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public List<Reimbursement> accountsOverseen(long uid){
		String query = "select * from Reimbursements r where (r.superid = ?)";
		List<Reimbursement> reimbs = new ArrayList<>();
		ResultSet result  = null;
		
		try(Connection userConn = ConnectionUtil.getConnection()){
			PreparedStatement pstatement =  userConn.prepareStatement(query);
			pstatement.setLong(1, uid);
			result = pstatement.executeQuery();
			
			while(result.next()) {
				Reimbursement r = new Reimbursement();
				r.setAmount(result.getLong("amount"));
				r.setRequestid(result.getLong("requestid"));
				r.setSourceid(result.getLong("sourceid"));
				r.setSuperid(result.getLong("superid"));
				r.setStatus(result.getString("status"));
				
				reimbs.add(r);
			}
				return reimbs;
		
		}catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public long postRequest(long uid, long sid, long amm, String status) {
		String sql = "{call create_reimb(?, ?, ?, ?}";
		ResultSet result = null;
		try(Connection userConn = ConnectionUtil.getConnection()){
			CallableStatement pcall =  userConn.prepareCall(sql);
			pcall.setLong(1, uid);
			pcall.setLong(2, sid);
			pcall.setLong(3, amm);
			pcall.setString(4, status);
			pcall.execute();
			result = pcall.getResultSet();
			if (result.next()) {
				return result.getLong(1);
			} else return 0;
		}catch (SQLException e) {
			System.out.println(e.getMessage());
			//e.printStackTrace();
			return 0;
		}
	}
	
	@Override
	public long stampRequest(long uid, String status) {
		return 0;
	}
	
	
}
