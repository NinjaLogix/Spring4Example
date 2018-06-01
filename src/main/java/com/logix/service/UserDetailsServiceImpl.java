package com.logix.service;

import com.logix.exception.UserNotFoundException;
import com.logix.model.User;
import com.logix.persistence.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserDao userDao;

    public UserDetails loadUserByUsername(String email) throws UserNotFoundException {
        User user = userDao.findByEmail(email);

        if (user == null){
            throw new UserNotFoundException(getClass().getName() + " : User not found with email -> " + email);
        }

        //set user details from user object then save user object.

        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;

        //test array list of auths
        List<GrantedAuthority> authorities = new ArrayList<>();

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPass().toLowerCase(),
                enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);

    }

    private static List<GrantedAuthority> getAuthorities(List<String> roles){
        List<GrantedAuthority> authorities = new ArrayList<>();
        //convert user authorities here
        return authorities;
    }

}