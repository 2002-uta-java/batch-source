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

public class RegisterServlet extends HttpServlet {

    private EmployeeDao employeeDao=new EmployeeDaoImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstname = req.getParameter("firstname");
        String lastname = req.getParameter("lastname");
        String username = req.getParameter("username");
        String passwrd = req.getParameter("passwrd");
        String email = req.getParameter("email");
        int roleId = Integer.parseInt(req.getParameter("roleId"));
        Employee employee = new Employee(0,roleId,firstname,lastname,username,passwrd,email);
        int result = employeeDao.save(employee);
        Map<String,Object> map = new HashMap<>();
        if (result>0){
            map.put("success",true);
            map.put("data",employee);
            resp.setStatus(201);
        }
        else{
            map.put("success",false);
            map.put("message","Failed");
            resp.setStatus(400);
        }
        ObjectMapper om = new ObjectMapper();
        String json = om.writeValueAsString(map);
        try(PrintWriter pw = resp.getWriter()){
            pw.write(json);
        }
    }
}
