package com.logix.persistence.dao;

import com.logix.exception.UserNotFoundException;
import com.logix.model.User;
import java.util.List;

public interface UserDao {
    User create(User user);
    User delete(String id) throws UserNotFoundException;
    List<User> findAll();
    User update(User user) throws UserNotFoundException;
    User findById(String id);
    User findByEmail(String email);
}
