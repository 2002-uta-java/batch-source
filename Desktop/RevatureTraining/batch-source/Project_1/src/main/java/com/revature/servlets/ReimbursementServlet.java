package com.revature.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.daos.ReimbursementDao;
import com.revature.daos.ReimbursementDaoImp;
import com.revature.models.Reimbursement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class ReimbursementServlet extends HttpServlet {

    private ReimbursementDao reimbursementDao = new ReimbursementDaoImp();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String procces =req.getParameter("p");
        if (procces!=null)
        {
            int rId = Integer.parseInt(req.getParameter("id"));
            Reimbursement reimbursement = new Reimbursement();
            reimbursement.setReimbersementId(rId);
            reimbursement.setStatus(procces);
            reimbursementDao.updateReimbursement(reimbursement);
        }
        req.getRequestDispatcher("Views/ReimbursementRequest.html").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        double amount = Double.parseDouble(req.getParameter("amount"));
        String category=req.getParameter("category");
        int empId = Integer.parseInt(req.getParameter("employeeId"));
        Reimbursement reimbursement = new Reimbursement();
        reimbursement.setAmount(amount);
        reimbursement.setCategory(category);
        reimbursement.setEmployeeId(empId);
        reimbursement.setStatus("Pending");
        int result = reimbursementDao.createReimbursement(reimbursement);
        Map<String,Object> map = new HashMap<>();

        ObjectMapper om = new ObjectMapper();
        if(result!=0) {
            map.put("status",true);
            map.put("data",reimbursement);
            resp.setStatus(201);
        }
        else{
            map.put("status",false);
            map.put("messge","Bad Request");
            resp.setStatus(400);
        }
        String json = om.writeValueAsString(map);
        try(PrintWriter pw = resp.getWriter()){
            pw.write(json);
        }

    }
}
