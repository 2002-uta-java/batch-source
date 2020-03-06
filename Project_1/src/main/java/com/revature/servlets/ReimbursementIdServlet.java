package com.revature.servlets;

import com.revature.models.Employee;
import com.revature.models.Reimbursement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.services.EmployeeService;
import com.revature.services.ReimbursementService;

public class ReimbursementIdServlet extends HttpServlet {

    private ReimbursementService rs = new ReimbursementService();
    private EmployeeService es = new EmployeeService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        String []  pathVariable = uri.split("\\/");
        System.out.println(pathVariable);
        if(pathVariable[pathVariable.length-1].matches("^\\d+$")) {
            List<Reimbursement> reimbursements = null;
            List<Employee> employees = new ArrayList<>();
            Employee em = es.getEmployeeById(Integer.parseInt(pathVariable[pathVariable.length-1]));

            if (em.getRoleId() == 1){
                reimbursements = rs.getAll();
                employees = es.getAllEmployee();
            }
            else{
                reimbursements = rs.getAllReimbursementByUserId(Integer.parseInt(pathVariable[pathVariable.length-1]));
            }

            if(reimbursements == null) {
                resp.sendError(404, "No Given UserId");
            } else {
                Map<String,Object> map = new HashMap<>();
                map.put("reimbursements",reimbursements);
                map.put("employees",employees);
                ObjectMapper om = new ObjectMapper();
                resp.getWriter().write(om.writeValueAsString(map));
            }
        } else {
            resp.sendError(400, "Unsupported Path Variable");
        }
    }
}
