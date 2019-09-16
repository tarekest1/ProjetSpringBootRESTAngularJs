package com.thamouda.gestion_magasin.controllors;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.thamouda.gestion_magasin.dao.UserRepository;
import com.thamouda.gestion_magasin.models.User;

@RestController
public class MainRestController {
	
	@Autowired
	private UserRepository userRepository;
	
    // URL:
    // http://localhost:9090/SomeContextPath/users
    // http://localhost:9090/SomeContextPath/users.xml
    // http://localhost:9090/SomeContextPath/users.json
	@RequestMapping(value = "/users", //
            method = RequestMethod.GET, //
            produces = { MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE })
	@ResponseBody
    public Iterable<User> getUsers() {
        Iterable<User> list = userRepository.findAll();
        return list;
    }
	
	  // URL:
    // http://localhost:8080/SomeContextPath/user/{userId}
    // http://localhost:8080/SomeContextPath/user/{userId}.xml
    // http://localhost:8080/SomeContextPath/user/{userId}.json
    @RequestMapping(value = "/users/{userId}", //
            method = RequestMethod.GET, //
            produces = { MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public Optional<User> getUser(@PathVariable("userId") Integer id) {
        return userRepository.findById(id);
    }
  
    // URL:
    // http://localhost:8080/SomeContextPath/user
    // http://localhost:8080/SomeContextPath/user.xml
    // http://localhost:8080/SomeContextPath/user.json
  
    @RequestMapping(value = "/user", //
            method = RequestMethod.POST, //
            produces = { MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public User addUser(@RequestBody User user) {
        System.out.println("(Service Side) Creating user with empNo: " + user.getName());
        return userRepository.save(user);
    }

    
    // URL:
    // http://localhost:8080/SomeContextPath/user
    // http://localhost:8080/SomeContextPath/user.xml
    // http://localhost:8080/SomeContextPath/user.json
    @RequestMapping(value = "/user", //
            method = RequestMethod.PUT, //
            produces = { MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public User updateUser(@RequestBody User user) {
        System.out.println("(Service Side) Editing user with Id: " + user.getId());
        return userRepository.save(user);
    }
  
    // URL:
    // http://localhost:8080/SomeContextPath/employee/{empId}
    @RequestMapping(value = "/user/{userId}", //
            method = RequestMethod.DELETE, //
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public void deleteUser(@PathVariable("userId") Integer userId) {
        System.out.println("(Service Side) Deleting user with Id: " + userId);
        userRepository.deleteById(userId);
    }
	
	

}
