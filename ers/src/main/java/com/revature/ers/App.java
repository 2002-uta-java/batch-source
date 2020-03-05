package com.revature.ers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

/**
Expense Reimbursement System (ERS)

Project 1 will be an individual project based off of an employee expense reimbursement system (or similar*). 
It will be a full stack application utilizing HTML, CSS, and JavaScript on the frontend and a 
Java application on the backend. This Java application will utilize Servlets to process user requests 
from the front end. The following user stories are required of the application:

-An Employee can login x
-An Employee can view the Employee Homepage x
-An Employee can logout x
-An Employee can submit a reimbursement request x
-An Employee can view their pending reimbursement requests x
-An Employee can view their resolved reimbursement requests x
-An Employee can view their information (profile) x
-An Employee can update their information x

-A Manager can login x
-A Manager can view the Manager Homepage x
-A Manager can logout x
-A Manager can approve/deny pending reimbursement requests x
-A Manager can view all pending requests from all employees x
-A Manager can view all resolved requests from all employees and see which manager resolved it x
-A Manager can view all Employees x
-A Manager can view reimbursement requests from a single Employee x
 


Additional Requirements:
for testing application code
Ensuring code quality with a static code analysis x
Logging using log4j or a similar logging framework
JUnit x


Optional User Stories:
------------------------------------------------------------------
-An Employee can upload an image of his/her receipt as part of the reimbursement request 
-A Manager can view images of the receipts from reimbursement requests
-An Employee receives an email when one of their reimbursement requests is resolved 
-A Manager can register an Employee, which sends the Employee an email with their username  and temp password 
-An Employee can reset their password x
-A Manager can update any Employee x
-Password encryption
-Including visual aids, such as charts showing spending categories or spending over time 


Technologies:
------------------------------------------------------------------
-Java 1.8
-Servlets
-JDBC
-SQL
-HTML
-CSS
-Javascript
-Bootstrap
-AJAX
-JUnit
-log4j 

Environment:
------------------------------------------------------------------
-Tomcat Server
-PostgreSQL Database
-Eclipse/Spring Tools Suite
-DBeaver

It is possible to do a variation of the ERS system if you’d like. 
If you have a similar idea that you would like to implement, it can be discussed 
with your trainer, as long as there are equivalent user stories that can be defined
*/
public class App 
{
	static final Logger logger = (Logger) LogManager.getLogger(com.revature.ers.App.class);
    public static void main( String[] args )
    {
    	logger.info("Hi from main");
        System.out.println( "Hello World!" );
    }
}
