package com.logix.service;

import com.logix.exception.UserNotFoundException;
import com.logix.model.User;
import com.logix.model.UserRole;
import com.logix.persistence.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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

        if (user.getDetails() == null){
            user.getDetails().setEnabled(true);
            user.getDetails().setAcctNotExpired(true);
            user.getDetails().setCredsNotExpired(true);
            user.getDetails().setAcctNotLocked(true);
        }

        List<String> roles = new ArrayList<String>();
        for (UserRole role : user.getRoles()){
            roles.add(role.getRole());
        }

        return new org.springframework.security.core.userdetails.User(user.getEmail(),
                user.getPass().toLowerCase(),
                user.getDetails().getEnabled(),
                user.getDetails().getAcctNotExpired(),
                user.getDetails().getCredsNotExpired(),
                user.getDetails().getAcctNotLocked(),
                getAuthorities(roles));
    }

    private static List<GrantedAuthority> getAuthorities(List<String> roles){
        List<GrantedAuthority> authorities = new ArrayList<>();

        for (String role: roles){
            authorities.add(new SimpleGrantedAuthority(role));
        }

        return authorities;
    }

}