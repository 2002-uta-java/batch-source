package com.revature.servlets;

import com.revature.models.Employee;
import com.revature.services.EmployeeService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class EmployeeProfileServlet extends HttpServlet {

    private EmployeeService employeeService = new EmployeeService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String uri = req.getRequestURI();
        String[] pathVariable = uri.split("\\/");
        int id = Integer.parseInt(pathVariable[pathVariable.length - 1]);
         Employee em = employeeService.getEmployeeById(id);
        PrintWriter out = resp.getWriter();
        String page = "Employee Page";
        if (em.getRoleId() == 1)
            page = "Manager Page";


        out.println("<link href='//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css' rel='stylesheet' id='bootstrap-css'>");
        out.println("<div class='container-fluid'>" +
                "    <nav class='navbar navbar-default'>" +
                "        <div class='container-fluid'>" +
                "            <!-- Brand and toggle get grouped for better mobile display -->" +
                "            <div class='navbar-header'>" +
                "                <button type='button' class='navbar-toggle collapsed' data-toggle='collapse' data-target='#bs-example-navbar-collapse-1' aria-expanded='false'>" +
                "                    <span class='sr-only'>Toggle navigation</span>" +
                "                    <span class='icon-bar'></span>" +
                "                    <span class='icon-bar'></span>" +
                "                    <span class='icon-bar'></span>" +
                "                </button>" +
                "                <a class='navbar-brand' href='#'>" + page + "</a>" +
                "            </div>" +
                "" +
                "            <!-- Collect the nav links, forms, and other content for toggling -->" +
                "            <div class='collapse navbar-collapse' id='bs-example-navbar-collapse-1'>" +
                "                <ul class='nav navbar-nav'>" +
                "                    <li class='active'><a href='/reimbursement'>Home <span class='sr-only'>(current)</span></a></li>" +
                "                </ul>" +
                "                <ul class='nav navbar-nav navbar-right'>" +
                "                    <li><a href='#' id='employee-name'></a></li>" +
                "                    <li class='dropdown'>" +
                "                        <a href='#' class='dropdown-toggle' data-toggle='dropdown' role='button' aria-haspopup='true' aria-expanded='false'>Dropdown <span class='caret'></span></a>" +
                "                        <ul class='dropdown-menu'>" +
                "                            <li>" + String.format("<a href='/employeeProfile/%d'>Profile</a>", em.getEmployeeId()) + "</li>" +
                "                            <li><a href='/login'>Logout</a></li>" +
                "                        </ul>" +
                "                    </li>" +
                "                </ul>" +
                "            </div><!-- /.navbar-collapse -->" +
                "        </div><!-- /.container-fluid -->" +
                "    </nav>" +
                "</div>");

        out.println("<h3 class='text-center' >" + em.getFirstname() + " Profile</h3>");

        out.println("<div class = 'container' style='width:95%'>" +
                "<div class='col-md-6'>" +
                "<table class='table'>"
                + "<tbody>" +
                "<tr><td><b>Firstname</b></td><td>" + em.getFirstname() + "</td></tr>" +
                "<tr><td><b>Lastname</b></td><td>" + em.getLastname() + "</td></tr>" +
                "<tr><td><b>Username</b></td><td>" + em.getUsername() + "</td></tr>" +
                "<tr><tr><td><b>Email</b></td><td>" + em.getEmail() + "</td></tr>" +
                "</tbody></table></div>");
        out.println("<div class='col-md-6'> " +
                "<form role=form' class='form-horizontal' method='post' action=''>" +
                "                        <div class='form-group'>" +
                "                            <label for='email' class='col-sm-2 control-label'>" +
                "                                Name</label>" +
                "                            <div class='col-sm-10'>" +
                "                                <div class='row'>" +
                "                                        <input type='text' class='form-control' name='name' value='"+em.getFirstname()+"' />" +
                "                                </div>" +
                "                            </div>" +
                "                        </div>" +
                "                        <div class='form-group'>" +
                "                            <label for='email' class='col-sm-2 control-label'>" +
                "                                Last Name</label>" +
                "                            <div class='col-sm-10'>" +
                "                                <div class='row'>" +
                "                                    <input type='text' class='form-control'  name='lastname' value='"+em.getLastname()+"' />" +
                "                                </div>" +
                "                            </div>" +
                "                        </div>" +
                "                        <div class='form-group'>" +
                "                            <label for='email' class='col-sm-2 control-label'>" +
                "                                Email</label>" +
                "                            <div class='col-sm-10'>" +
                "                                <input type='email' class='form-control'  name='email' id='email' value='"+em.getEmail()+"' />" +
                "                            </div>" +
                "                        </div>" +
                "                        <div class='form-group'>" +
                "                            <label for='mobile' class='col-sm-2 control-label'>" +
                "                                Username</label>" +
                "                            <div class='col-sm-10'>" +
                "                                <input type='text' class='form-control'  name='username' id='mobile' value='"+em.getUsername()+"' />" +
                "                            </div>" +
                "                        </div>" +
                "                        <div class='form-group'>" +
                "                            <label for='password' class='col-sm-2 control-label'>" +
                "                                Password</label>" +
                "                            <div class='col-sm-10'>" +
                "                                <input type='password' class='form-control'  name='password' id='password' value='"+em.getPasswrd()+"' />" +
                "                            </div>" +
                "                        </div>" +
                "                        <div class='form-group'>" +
                "                            <label for='password' class='col-sm-2 control-label'>" +
                "                                Role</label>" +
                "                            <div class='col-sm-10'>" +
                "                                <input type='text' class='form-control'  name='roleId' id='password' value='"+em.getRoleId()+"' />" +
                "                            </div>" +
                "                        </div>" +
                "" +
                "                        <div class='row'>" +
                "                            <div class='col-sm-2'>" +
                "                            </div>" +
                "                            <div class='col-sm-10'>" +
                "                                <button type='submit' class='btn btn-primary btn-sm'>" +
                "                                    Save</button>" +
                "                            </div>" +
                "                        </div>" +
                "                    </form></div>");

        out.println("<script src='//code.jquery.com/jquery-1.11.1.min.js'></script>" +
                "<script src='//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js'></script>");


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String uri = req.getRequestURI();
        String[] pathVariable = uri.split("\\/");
        int id = Integer.parseInt(pathVariable[pathVariable.length - 1]);
        String name = req.getParameter("name");
        String lastname = req.getParameter("lastname");
        String email = req.getParameter("email");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        int roleId = Integer.parseInt(req.getParameter("roleId"));
        Employee employee = new Employee(id,roleId,name,lastname,username,password,email);
        employeeService.updateEmployee(employee);
        if (roleId == 1)
        resp.sendRedirect("managerProfile/"+id);
        else
            resp.sendRedirect("employeeProfile/"+id);
    }

}