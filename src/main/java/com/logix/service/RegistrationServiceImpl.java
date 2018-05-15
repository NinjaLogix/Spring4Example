package com.logix.service;

import com.logix.persistence.dao.UserDao;
import com.logix.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.logix.persistence.dto.UserDto;


@Service
@Transactional
public class RegistrationServiceImpl implements RegistrationService {
    @Autowired
    UserDao dao;

    @Override
    public User handleNewAccount(UserDto userDto){
        User user = mapToUser(userDto);
        dao.save(user);
        return user;
    }

    User mapToUser(UserDto userDto){
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setFname(userDto.getFirstName());
        user.setLname(userDto.getLastName());
        user.setPass(userDto.getPass());
        user.setCpass(userDto.getCpass());

        return user;
    }
}
