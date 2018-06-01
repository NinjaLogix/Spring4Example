package com.logix.service;

import com.logix.exception.UserNotFoundException;
import com.logix.model.Details;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserDetailsService {
    UserDetails loadUserByUsername(String email) throws UserNotFoundException;
}