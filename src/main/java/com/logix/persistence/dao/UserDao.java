package com.logix.persistence.dao;

import com.logix.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface UserDao extends CrudRepository<User, Long>{
    UserDao findByEmail(String email);
}
