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
- parameterizing routes allow you annotate method parameters:
    - @RequestParam("id") int id when an id request parameter is provided
    - @PathVariable("id") int id when an id is included in the path (@GetMapping("/))
    - can still pass in an HttpServletRequest/Response if we want to our controller methods
- return string representing a view name, view resolver will prepend and append configured prefix and suffix 
- return objects/text back in the response body when method is annotated with @ResponseBody
- a method parameter annotated with @RequestBody will use jackson to map JSON into the annotated Java object 
- @ResponseStatus(value=HttpStatus.NOT_FOUND, reason="some description") can be used to return a particular http status to the client
- @ControllerAdvice annotation can be used to globally handle exceptions, this allows us to set response status based on certain exceptions being thrown anywhere in your application


