package com.revature.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.daos.EmployeeDao;
import com.revature.daos.EmployeeDaoImpl;
import com.revature.models.Employee;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class LoginServlet extends HttpServlet {

    private EmployeeDao employeeDao=new EmployeeDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("Views/Login.html").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        Map<String,Object> map = new HashMap<>();
        Employee employee = employeeDao.getEmployeeByUsername(username,password);
        ObjectMapper om = new ObjectMapper();
        if (employee!=null)
        {
            map.put("success",true);
            map.put("data",employee);
            resp.setStatus(200);
        }
        else{
          map.put("success",false);
          map.put("data",null);
          map.put("message","Unauthorized");
          resp.setStatus(401);
        }
        String json = om.writeValueAsString(map);
        try(PrintWriter pw = resp.getWriter()){
            pw.write(json);
        }
    }
}
