import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.revature.models.Employee;
import com.revature.services.EmployeeService;


public class EmployeeServiceTest {
	
		EmployeeService es = new EmployeeService();
		
		@Test
		public void getEmployeeByNullUsername() {
			Employee expected = null;
			String username = "";
			String passwrd = "";
			assertEquals(expected, es.getEmployeeByUsername(username, passwrd));
		}
		
		@Test
		public void getEmployeeByIdInvalidUsername() {
			Employee expected = null;
			String username = "psaleilmmk";
			String passwrd = "adams80";
			assertEquals(expected, es.getEmployeeByUsername(username, passwrd));
		}
		
		@Test
		public void getEmployeeByInvalidPassword() {
			Employee expected = null;
			String username = "adams_andrew";
			String passwrd = "yleiuy65+1856";
			assertEquals(expected, es.getEmployeeByUsername(username, passwrd));
		}

	}