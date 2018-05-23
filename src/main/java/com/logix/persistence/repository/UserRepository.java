package com.logix.persistence.repository;

import com.logix.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> { //JpaRepository<Object Class, ID type>
}
