import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.dean.daos.ReimbursementDaoImpl;
import com.dean.models.Reimbursement;
import com.dean.util.ConnectionUtil;

public class Project1Test {

	static final Logger logger = Logger.getRootLogger();
	
	@Test
	public void getEmployees() {
		try (Connection c = ConnectionUtil.getConnection()) {
			//
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void createReimbursement() throws IOException {
		try (Connection c = ConnectionUtil.getConnection()) {
			Reimbursement r = new Reimbursement();
			r.setReimbursementId(113);
			r.setReimbursementAmount(300);
			r.setReimbursementDescription("Flight to LAX");
			r.setReimbursementStatus("APPROVED");
			r.setManagerId(4);
			r.setEmployeeId(1);
			c.rollback();
			ReimbursementDaoImpl rd = new ReimbursementDaoImpl();
			assertEquals(1, rd.createReimbursement(r));
		} catch (SQLException e) {
			logger.info(e.getMessage());
		} catch (ClassNotFoundException e1) {
			logger.info(e1.getMessage());
		}
	}
	
	@Test
	public void denyReimbursementById() throws IOException {
		try (Connection c = ConnectionUtil.getConnection()) {
			Reimbursement r = new Reimbursement();
			r.setEmployeeId(1);
			ReimbursementDaoImpl rd = new ReimbursementDaoImpl();
			assertEquals(1, rd.denyReimbursementByErbId(1));
		} catch (SQLException e) {
			logger.info(e.getMessage());
		} catch (ClassNotFoundException e1) {
			logger.info(e1.getMessage());
		}
	}
	
	@Test
	public void canApproveReimbursementById() throws IOException {
		try (Connection con = ConnectionUtil.getConnection()) {
			Reimbursement r = new Reimbursement();
			r.setEmployeeId(1);
			ReimbursementDaoImpl rdi = new ReimbursementDaoImpl();
			assertEquals(1, rdi.approveReimbursementByErbId(1));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
