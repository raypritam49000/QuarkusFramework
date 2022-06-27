package com.rest.api.service.impl;

import com.rest.api.entity.User;
import com.rest.api.repository.UserRepository;
import com.rest.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User getUserByUsername(String username) throws Exception {
        User user = this.userRepository.findByUsername(username).get();

        if (user != null) {
            return user;
        } else {
            throw new Exception("Data Not Found");
        }
    }

    @Override
    public User createUser(User user) {
        return this.userRepository.save(user);
    }
}
