package delegates;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import daos.UserDaoImp;
import daos.UserDao;
import models.User;

public class UserDelegate {
	private UserDao uDao = new UserDaoImp();


}

}
