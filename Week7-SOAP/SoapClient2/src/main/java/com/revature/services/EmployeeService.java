
package com.revature.services;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "EmployeeService", targetNamespace = "http://services.revature.com/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface EmployeeService {


    /**
     * 
     * @param arg0
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "addEmployee", targetNamespace = "http://services.revature.com/", className = "com.revature.services.AddEmployee")
    @ResponseWrapper(localName = "addEmployeeResponse", targetNamespace = "http://services.revature.com/", className = "com.revature.services.AddEmployeeResponse")
    public String addEmployee(
        @WebParam(name = "arg0", targetNamespace = "")
        Employee arg0);

    /**
     * 
     * @return
     *     returns java.util.List<com.revature.services.Employee>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getAllEmployees", targetNamespace = "http://services.revature.com/", className = "com.revature.services.GetAllEmployees")
    @ResponseWrapper(localName = "getAllEmployeesResponse", targetNamespace = "http://services.revature.com/", className = "com.revature.services.GetAllEmployeesResponse")
    public List<Employee> getAllEmployees();

}
