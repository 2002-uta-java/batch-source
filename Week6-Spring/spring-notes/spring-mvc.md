# Spring MVC
- set up spring applications to handle http requests

### Workflow
- http request hits our server and goes first to our deployment descriptor 
- goes to our front controller servlet
    - org.springframework.web.servlet.DispatcherServlet
    - this is a servlet, configure in web.xml
- Dispatcher servlet consults Handler Mapping
    - org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping
    - this is a bean, configure it in our beans.xml
    - also need to enable annotations ```<mvc:annotation-driven>```
- directs request to the appropriate controller which processes it according 
- controller returns a view name or model data
    - consults view resolver if a view name
    - returned to client in response body if not


### Controllers
- @Controller defines a Spring bean says class will handle request 
- @RequestMapping(method=RequestMethod.GET, value="/home")
- @GetRequest("/home")
- @PostRequest("/login")
- parameterizing routes:
    - @RequestParam("id") int id when @GetRequest("/employee")
    - @PathVariable("id) int
    - can still pass in an HttpServletRequest/Response if we want to our controller methods
- return string representing a view name
- return objects/text back in the response body
- @ResponseStatus(value=HttpStatus.NOT_FOUND, reason="some description")