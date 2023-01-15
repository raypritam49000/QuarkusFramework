package com.rest.api.service;

import com.rest.api.entity.User;

public interface UserService {
    public abstract User getUserByUsername(String username) throws Exception;
    public abstract User createUser(User user);
}
