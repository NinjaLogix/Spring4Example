package com.logix.persistence.repository;

import com.logix.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Integer> { //JpaRepository<Object Class, ID type>
    User findByEmail(@Param("email") String email);
}
