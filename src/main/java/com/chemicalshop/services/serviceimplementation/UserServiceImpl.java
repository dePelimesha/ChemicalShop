package com.chemicalshop.services.serviceimplementation;

import com.chemicalshop.dao.daointerface.UserDAO;
import com.chemicalshop.entities.UsersEntity;
import com.chemicalshop.services.serviceinterface.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserDAO userDAO;

    @Override
    public int addUser(UsersEntity user) {
        return userDAO.addUser(user);
    }

    @Override
    public UsersEntity getById(int id) {
        return userDAO.getById(id);
    }

    @Override
    public UsersEntity getByUserName(String userName) {
        return userDAO.getByUserName(userName);
    }
}
