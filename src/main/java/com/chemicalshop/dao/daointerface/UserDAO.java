package com.chemicalshop.dao.daointerface;

import com.chemicalshop.entities.UsersEntity;
import org.springframework.stereotype.Repository;

public interface UserDAO {
    int addUser(UsersEntity user);
    UsersEntity getById(int id);
    UsersEntity getByUserName(String userName);
}
