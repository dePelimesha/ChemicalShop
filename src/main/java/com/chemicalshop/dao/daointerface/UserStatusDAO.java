package com.chemicalshop.dao.daointerface;

import com.chemicalshop.entities.UserstatusEntity;

import java.util.List;

public interface UserStatusDAO {
    List<UserstatusEntity> getAllStatus();
    UserstatusEntity getStatusById(int id);
}
