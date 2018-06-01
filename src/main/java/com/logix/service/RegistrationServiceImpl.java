package com.logix.service;

import com.logix.exception.EmailExistsException;
import com.logix.persistence.dao.UserDao;
import com.logix.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.logix.persistence.dto.UserDto;

@Service
@Transactional
public class RegistrationServiceImpl implements RegistrationService {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserDao dao;

    @Autowired
    private UserDetailsService details;

    @Override
    public User createAccount(UserDto userDto){
        User createdUser = null;
        try{
            createdUser = handleNewAccount(userDto);
        } catch ( EmailExistsException ex ){
            ex.printStackTrace();
            return null;
        }
        return createdUser;
    }

    private User handleNewAccount(UserDto userDto) throws EmailExistsException{
        if (!uniqueAccount(userDto.getEmail())){
            throw new EmailExistsException("This already exists: " + userDto.getEmail());
        }

        //TODO - user registration is not full here
        User registeredUser = mapToUser(userDto);
        dao.create(registeredUser);

        //TODO - handle user details here? Still following article

        return registeredUser;
    }

    private boolean uniqueAccount(String email){
        User user = dao.findByEmail(email);
        if (user == null){
            return true;
        } else {
            return false;
        }
    }

    private User mapToUser(UserDto userDto){
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setFname(userDto.getFirstName());
        user.setLname(userDto.getLastName());
        user.setPass(userDto.getPass());
        user.setCpass(userDto.getCpass());

        return user;
    }
}
