package com.sajid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserServiceHibernate userServiceHibernate;

    @GetMapping("users")        // get all users from database
    public List<User> getAllUsers() throws SQLException {
        return userService.getAllUsers();
    }

    @PostMapping("/users")      // add user to the database
    public String addUser(@RequestBody User user) throws SQLException {
        userServiceHibernate.addUser(user);
        return "User Added";
    }

    @DeleteMapping("/users/{id}")       // delete user by id from the database
    public String deleteUser(@PathVariable int id) throws Exception {
        userServiceHibernate.deleteUser(id);
        return "User deleted";
    }

    //"users/{userId}" - Get a user from users resource
    //"author/{authorId}" Get a author from authors resource
    //"author/{authorId}/books" Get list of books from a authorId
    //"authorId/{authorId}/books/{bookId}"
//    @GetMapping("/users/{email}")        // get user by email from the database
//    public String getUserByEmail(@PathVariable String email) throws Exception {
//        User user = userService.searchWithEmail(email);
//        if(user == null)
//            return "User with this email does not exist";
//        String str = "ID: "  + user.getId() +  "\nName: " + user.getName() + "\nAge: " + user.getAge() + "\nMobileNumber: " + user.getMobileNumber();
//        return str;
//    }

    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable int id) throws Exception {
        return userServiceHibernate.getUserById(id);
    }

    @PutMapping("/users/{id}")      // update user with id
    public String updateUser(@PathVariable int id, @RequestBody User user) throws SQLException {
        userServiceHibernate.updateUser(id, user);
        return "User Updated";
    }
}
