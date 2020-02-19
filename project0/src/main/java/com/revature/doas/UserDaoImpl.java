package com.revature.doas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

import com.revature.models.User;
import com.revature.util.ConnectionUtil;

public class UserDaoImpl implements UserDao {
	public UserDaoImpl() {
		// TODO Auto-generated constructor stub
	}


/**
 * checks to see if user exists in our user data base if not then we 
 */
	public int createUser(User user) {
		// TODO Note passWord is varchar10

		String sql = "insert into \"User\" (\"UserName\", \"PassWord\", \"AccountNumber\") "
				+ "values (?, ?, ?)";
		int usersmade = 0;
		try(Connection c = ConnectionUtil.getHardConnection();
				PreparedStatement ps = c.prepareStatement(sql)) {
			
			ps.setString(1, user.getUserName());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getAccountNumber());
			usersmade = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return usersmade;
	}

/**
 * used to generate a string for the accountNumer.
 * it takes in a variable for the length of the account number
 * however right now we only care about numbers that are four characters long
 */
@Override
public String generateAccountNumber(int len) {
	String alvaiableChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
    StringBuilder sb = new StringBuilder();
    Random rand = new Random();
    while (sb.length() < len) { // length of the random string.
        int index = (int) (rand.nextFloat() * alvaiableChars.length());
        sb.append(alvaiableChars.charAt(index));
    }
    String accNum = sb.toString();
    return accNum;
}
}
