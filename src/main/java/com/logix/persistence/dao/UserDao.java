package com.logix.persistence.dao;

import com.logix.model.User;
import java.util.List;

public interface UserDao {
    User create(User user);
    User delete(int id); //throws UserNotFoundException;
    List<User> findAll();
    User update(User user); //thows UserNotFoundException;
    User findById(int id);
}
