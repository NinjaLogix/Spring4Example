package com.logix.persistence.dao;

import com.logix.exception.UserNotFoundException;
import com.logix.model.User;
import com.logix.persistence.repository.UserRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
    @Resource
    private UserRepository userRepository;

    @Override
    @Transactional
    public User create(User user){
        User createdUser = user;
        return userRepository.save(createdUser);
    }

    @Override
    @Transactional
    public User findById(String id){
        return userRepository.findOne(id);
    }

    @Override
    @Transactional(rollbackFor = UserNotFoundException.class)
    public User delete(String id){
        User deletedUser = userRepository.findOne(id);

        if (deletedUser == null){
            throw new UserNotFoundException();
        }
        userRepository.delete(deletedUser);
        return deletedUser;
    }

    @Override
    @Transactional
    public List<User> findAll(){
        return userRepository.findAll();
    }

    @Override
    @Transactional(rollbackFor = UserNotFoundException.class)
    public User update(User user){
        User updatedUser = userRepository.findOne(user.getId());

        if(updatedUser == null){
            throw new UserNotFoundException();
        }

        updatedUser.setFname(user.getFname());
        updatedUser.setLname(user.getLname());
        return updatedUser;
    }

    @Override
    @Transactional
    public User findByEmail(String email){ return userRepository.findByEmail(email); }
}
