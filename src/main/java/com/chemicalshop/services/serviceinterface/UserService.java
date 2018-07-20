package com.chemicalshop.services.serviceinterface;

import com.chemicalshop.entities.UsersEntity;

import java.util.List;

public interface UserService {
    int addUser(UsersEntity user);
    UsersEntity getById(int id);
    UsersEntity getByUserName(String userName);
}
