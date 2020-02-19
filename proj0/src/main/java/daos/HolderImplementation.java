package daos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import models.Holder;
import utilities.ConnectionUtility;

public class HolderImplementation implements HolderDao{

	@Override
	public List<Holder> getHolders() {
		// TODO Auto-generated method stub
		String sql = "select * from holder";
		
		List<Holder> holders = new ArrayList <>();
		
		try (Connection c = ConnectionUtility.getConnection(); Statement s = c.createStatement();ResultSet rs = s.executeQuery(sql)){//Statement
			while(rs.next()) {
				String holderUserName = rs.getString("holderUserName");
				String holderPassword = rs.getString("holderPassword");
				double balance = rs.getDouble("balance");
				
				Holder h = new Holder(holderUserName,holderPassword,balance); 
				holders.add(h);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return holders;
	}

	@Override
	public Holder getHolderByUsername(String userName) {
		// TODO Auto-generated method stub
		String sql = "select * from holder where holderUserName = ?";
		Holder h = null;
		ResultSet rs =null;
		
		try (Connection c = ConnectionUtility.getConnection(); 
			 PreparedStatement ps = c.prepareStatement(sql);){
		ps.setString(1, userName);
		rs = ps.executeQuery();
		
		while(rs.next()) {
			String holderUserName = rs.getString("holderUserName");
			String holderPassword = rs.getString("holderPassword");
			double balance = rs.getDouble("balance");
			
			h = new Holder(holderUserName,holderPassword,balance); 
		}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return h;
	}
	
	public void addBalance(String user, double deposit) {
		String sql = "update \"holder\" set \"balance\" = \"balance\" + ? Where \"holderusername\" = ?";
		
		try (Connection c = ConnectionUtility.getConnection(); PreparedStatement ps = c.prepareStatement(sql);){//Statement
			ps.setDouble(1, deposit);
			ps.setString(2, user);
			
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
	}
	
	public void subtractBalance(String user, double widthdrawl) {
		
		String sql = "update \"holder\" set \"balance\" = \"balance\" - ? Where \"holderusername\" = ?";
		
		try (Connection c = ConnectionUtility.getConnection(); PreparedStatement ps = c.prepareStatement(sql);){//Statement
			ps.setDouble(1, widthdrawl);
			ps.setString(2, user);
			
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}

	@Override
	public Holder createHolder(Holder h) {
		// TODO Auto-generated method stub
		String sql ="call add_holder(?, ?)";
		
		ResultSet rs = null;
		
		try(Connection c = ConnectionUtility.getConnection();
				CallableStatement cs = c.prepareCall(sql)){
			cs.setString(1, h.getHolderUserName());
			cs.setString(2, h.getHolderPassword());
			
			cs.execute();
			
			rs = cs.getResultSet();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return h;
	}

}


