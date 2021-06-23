package com.sajid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Primary
public class UserServiceRepository implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        List<User> users = (List<User>) userRepository.findAll();
        List<User> userList = new ArrayList<>();
        for (User u : users) userList.add(u);

        return userList;
    }

    @Override
    public User getUserById(int id) {
        Optional<User> u = userRepository.findById(id);

        return u.get();
    }

    @Override
    public void addUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }

    @Override
    public void updateUser(int id, User user) {
        User user1 = getUserById(id);
        user1.setName(user.getName());
        user1.setAge(user.getAge());
        user1.setMobileNumber(user.getMobileNumber());
        user1.setEmail(user.getEmail());
        userRepository.save(user1);
    }
}
