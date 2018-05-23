package com.logix.persistence.dao;

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
    public User findById(int id){
        return userRepository.findOne(id);
    }

    @Override
    @Transactional//(rollbackFor = UserNotFoundException.class)
    public User delete(int id){
        User deletedUser = userRepository.findOne(id);

        //if (deletedUser == null){
            //throw UserNotFoundException
        //}
        userRepository.delete(deletedUser);
        return deletedUser;
    }

    @Override
    @Transactional
    public List<User> findAll(){
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public User update(User user){
        User updatedUser = userRepository.findOne(user.getId());

        //if(updatedUser == null){
            //throw UserNotFoundException
        //}

        updatedUser.setFname(user.getFname());
        updatedUser.setLname(user.getLname());
        return updatedUser;
    }
}
