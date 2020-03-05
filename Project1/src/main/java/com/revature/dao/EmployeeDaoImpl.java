package com.revature.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.model.Employee;
import com.revature.utli.ConnectionUtil;

public class EmployeeDaoImpl implements EmployeeDao {
	private static Logger log = Logger.getRootLogger();
	public EmployeeDaoImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Employee> getEmployee() {
			String sql = "select * from employee";
			
			List<Employee> employees = new ArrayList<>();
			
			try (Connection c = ConnectionUtil.getConnection();
				Statement s = c.createStatement();
				ResultSet rs = s.executeQuery(sql)) {
			
			   while(rs.next()) {
				   int emplId = rs.getInt("empl_id");
				   String fname = rs.getString("empl_firstname");
				   String lname = rs.getString("empl_lastname");
				   String emplEmail = rs.getString("empl_email");
				   String title = rs.getString("empl_title");
				   int mid = rs.getInt("manager_id");
				   int report = rs.getInt("reports_to");
				   Employee el1 = new Employee(emplId, fname, lname, emplEmail, title, mid, report);
				   employees.add(el1);	   
			   }
				
			} catch (SQLException e) {
			log.error("Unable to pull employee list", e);
			}
			
			return (List<Employee>) employees;
		}

	@Override
	public int getEmployeeId(String email) {
		String sql = "select empl_id from employee where empl_email = ?";
		ResultSet rs = null;
		int e2 = 0;
		
		try(Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql);){
		        ps.setString(1, email);
				rs = ps.executeQuery();
			
				while(rs.next()) {
					int id = rs.getInt("empl_id");
					Employee e1 = new Employee();
					e2 = e1.setEmployeeId(id);
				}
		} catch (SQLException e) {
			log.error("Unable to get employee id" + e);
		} finally {
			try { if (rs!=null) {
				rs.close();
			}
			} catch (SQLException e) {
				log.error("Unable to close resource employee id search" + e);
			}
		}
		return e2;
	}

	@Override
	public String getFirstName(int id) {
		String sql = "select empl_firstname from employee where empl_id = ?";
		ResultSet rs = null;
		String e2 = null;
		
		try(Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql);){
		        ps.setInt(1, id);;
				rs = ps.executeQuery();
			
				while(rs.next()) {
					String name = rs.getString("empl_firstname");
					Employee e1 = new Employee();
					e2 = e1.setFirstName(name);
				}
		} catch (SQLException e) {
			log.error("Unable to get employee first name" + e);
		} finally {
			try { if (rs!=null) {
				rs.close();
			}
			} catch (SQLException e) {
				log.error("Unable to close resource employee first name" + e);
			}
		}
		return e2;
	}

	@Override
	public String getLastname(int id) {
			String sql = "select empl_lastname from employee where empl_id = ?";
			ResultSet rs = null;
			String e2 = null;
			
			try(Connection c = ConnectionUtil.getConnection();
					PreparedStatement ps = c.prepareStatement(sql);){
			        ps.setInt(1, id);;
					rs = ps.executeQuery();
				
					while(rs.next()) {
						String name = rs.getString("empl_lastname");
						Employee e1 = new Employee();
						e2 = e1.setLastName(name);
					}
			} catch (SQLException e) {
				log.error("Unable to get employee last name" + e);
			} finally {
				try { if (rs!=null) {
					rs.close();
				}
				} catch (SQLException e) {
					log.error("Unable to close resource employee last name" + e);
				}
			}
			return e2;
		}
	@Override
	public int getManagerId(int id) {
		String sql = "select manager_id from employee where empl_id = ?";
		ResultSet rs = null;
		int e2 = 0;
		
		try(Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql);){
		        ps.setInt(1, id);
				rs = ps.executeQuery();
			
				while(rs.next()) {
					int eId = rs.getInt("manager_id");
					Employee e1 = new Employee();
					e2 = e1.setManagerId(eId);
				}
		} catch (SQLException e) {
			log.error("Unable to get manager id" + e);
		} finally {
			try { if (rs!=null) {
				rs.close();
			}
			} catch (SQLException e) {
				log.error("Unable to close resource manager id search" + e);
			}
		}
		return e2;
	}

	@Override
	public String getTitle(int id) {
			String sql = "select empl_title from employee where empl_id = ?";
			ResultSet rs = null;
			String e2 = null;
			
			try(Connection c = ConnectionUtil.getConnection();
					PreparedStatement ps = c.prepareStatement(sql);){
			        ps.setInt(1, id);;
					rs = ps.executeQuery();
				
					while(rs.next()) {
						String name = rs.getString("empl_title");
						Employee e1 = new Employee();
						e2 = e1.setTitle(name);
					}
			} catch (SQLException e) {
				log.error("Unable to get employee title name" + e);
			} finally {
				try { if (rs!=null) {
					rs.close();
				}
				} catch (SQLException e) {
					log.error("Unable to close resource employee title search" + e);
				}
			}
			return e2;
		}
	@Override
	public int getReportsTo(int id) {
			String sql = "select reports_to from employee where empl_id = ?)";
			ResultSet rs = null;
			int e2 = 0;
			
			try(Connection c = ConnectionUtil.getConnection();
					PreparedStatement ps = c.prepareStatement(sql);){
			        ps.setInt(1, id);
					rs = ps.executeQuery();
				
					while(rs.next()) {
						int eId = rs.getInt("reports_to");
						Employee e1 = new Employee();
						e2 = e1.setReportsToId(eId);
					}
			} catch (SQLException e) {
				log.error("Unable to get reports to id" + e);
			} finally {
				try { if (rs!=null) {
					rs.close();
				}
				} catch (SQLException e) {
					log.error("Unable to close resource reports to search" + e);
				}
			}
			return e2;
		}
	@Override
	public Employee createEmployeeByFunction(Employee e1) {
		String sql = "{call employee_entry(?,?,?,?,?,?)}";
		ResultSet rs = null;
		
			try(Connection c = ConnectionUtil.getConnection(); 
				CallableStatement cs = c.prepareCall(sql);) {
				cs.setString(1, e1.getFirstName());
				cs.setString(2, e1.getLastName());
				cs.setString(3, e1.getEmail());
				cs.setString(4, e1.getTitle());
				cs.setInt(5, e1.getManagerId());
				cs.setInt(6, e1.getReportsToId());
				
				cs.execute();
				rs = cs.getResultSet();
				
				while(rs.next()) {
					int cId = rs.getInt("empl_id");
				
					e1.setEmployeeId(cId);
				}
					
				} catch (SQLException e) {
					log.error("Unable to create a new employee" + e);
				} finally {
					try { if (rs!=null) {
						rs.close();
						}
					} catch (SQLException e) {
						log.error("Unable to close resource for create employee" + e);
					}
				}
		return e1;
	}

	@Override
	public Employee updateEmployeeManagerByFunction(Employee e1) {
		String sql = "{call update_employee_manager(?,?)}";
		ResultSet rs = null;
		
			try(Connection c = ConnectionUtil.getConnection(); 
				CallableStatement cs = c.prepareCall(sql);) {
				cs.setInt(1, e1.getManagerId());
				cs.setInt(2, e1.getReportsToId());
				
				cs.execute();
				rs = cs.getResultSet();
				
				while(rs.next()) {
					int cId = rs.getInt("reports_to");
				
					e1.setReportsToId(cId);
				}
					
				} catch (SQLException e) {
					log.error("Unable to create a new employee" + e);
				} finally {
					try { if (rs!=null) {
						rs.close();
						}
					} catch (SQLException e) {
						log.error("Unable to close resource for create employee" + e);
					}
				}
		return e1;
	}

	@Override
	public int deleteEmployee(int id) {
			String sql = "delete from employee where empl_id = ?)";
			ResultSet rs = null;
			
			try(Connection c = ConnectionUtil.getConnection();
					PreparedStatement ps = c.prepareStatement(sql);){
			        ps.setInt(1, id);
					rs = ps.executeQuery();
					
			} catch (SQLException e) {
				log.error("Unable to get employee email" + e);
			} finally {
				try { if (rs!=null) {
					rs.close();
				}
				} catch (SQLException e) {
					log.error("Unable to close resource employee email search" + e);
				}
			}
			return 1;
		}

	@Override
	public String getEmail(String email) {
		String sql = "select empl_email from employee where empl_email =(select client_email from client where client_email = ?)";
		ResultSet rs = null;
		String e2 = null;
		try(Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql);){
		        ps.setString(1, email);;
				rs = ps.executeQuery();
			
				while(rs.next()) {
					String eId = rs.getString("empl_email");
					Employee e1 = new Employee();
					e2 = e1.setEmail(eId);
				}
		} catch (SQLException e) {
			log.error("Unable to get employee id" + e);
		} finally {
			try { if (rs!=null) {
				rs.close();
			}
			} catch (SQLException e) {
				log.error("Unable to close resource employee id search" + e);
			}
		}
		return e2;
	}

	@Override
	public List<Employee> employeeById(int id) {
		String sql = "select * from employee where empl_id = ?";
		ResultSet rs = null;
		List<Employee> employees = new ArrayList<>();
		
		try(Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql);){
		        ps.setInt(1, id);
				rs = ps.executeQuery();
			
				   while(rs.next()) {
					   int emplId = rs.getInt("empl_id");
					   String fname = rs.getString("empl_firstname");
					   String lname = rs.getString("empl_lastname");
					   String emplEmail = rs.getString("empl_email");
					   String title = rs.getString("empl_title");
					   int mid = rs.getInt("manager_id");
					   int report = rs.getInt("reports_to");
					   Employee el1 = new Employee(emplId, fname, lname, emplEmail, title, mid, report);
					   employees.add(el1);	   
				   }
					
		} catch (SQLException e) {
			log.error("Unable to get employee id" + e);
		} finally {
			try { if (rs!=null) {
				rs.close();
			}
			} catch (SQLException e) {
				log.error("Unable to close resource employee id search" + e);
			}
		}
		return employees;
	}

	
	@Override
	public String updateFirstName(String a, int id) {
		String sql = "update employee set empl_firstname = ? where empl_id = ?";
		ResultSet rs = null;	
		String name = null;
		try(Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql);){
			 	ps.setString(1, a);
				ps.setInt(2, id);	        
				rs = ps.executeQuery();
			
				while(rs.next()) {
					String fname = rs.getString("empl_firstname");
					Employee e = new Employee();
					name = e.setFirstName(fname);
					
				}
		} catch (SQLException e) {
			log.error("Unable to get employee title name" + e);
		} finally {
			try { if (rs!=null) {
				rs.close();
			}
			} catch (SQLException e) {
				log.error("Unable to close resource employee title search" + e);
			}
		}
		return name;
	}


	@Override
	public String updateLastName(String a, int id) {
		String sql = "update employee set empl_lastname = ? where empl_id = ?";
		ResultSet rs = null;	
		String name = null;
		try(Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql);){
			 	ps.setString(1, a);
				ps.setInt(2, id);	        
				rs = ps.executeQuery();
			
				while(rs.next()) {
					String fname = rs.getString("empl_lastname");
					Employee e = new Employee();
					name = e.setLastName(fname);
					
				}
		} catch (SQLException e) {
			log.error("Unable to get employee title name" + e);
		} finally {
			try { if (rs!=null) {
				rs.close();
			}
			} catch (SQLException e) {
				log.error("Unable to close resource employee title search" + e);
			}
		}
		return name;
	}
	@Override
	public String updateTitle(String a, int id) {
			String sql = "update employee set empl_title = ? where empl_id = ?";
			ResultSet rs = null;	
			String name = null;
			try(Connection c = ConnectionUtil.getConnection();
					PreparedStatement ps = c.prepareStatement(sql);){
			        ps.setString(1, a);
					ps.setInt(2, id);	        
					rs = ps.executeQuery();
				
					while(rs.next()) {
						String fname = rs.getString("empl_title");
						Employee e = new Employee();
						name = e.setTitle(fname);
						
					}
			} catch (SQLException e) {
				log.error("Unable to get employee title name" + e);
			} finally {
				try { if (rs!=null) {
					rs.close();
				}
				} catch (SQLException e) {
					log.error("Unable to close resource employee title search" + e);
				}
			}
			return name;
		}

}
