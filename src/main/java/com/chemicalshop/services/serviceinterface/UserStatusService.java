package com.chemicalshop.services.serviceinterface;

import com.chemicalshop.entities.UserstatusEntity;

import java.util.List;

public interface UserStatusService {
    List<UserstatusEntity> getAllStatus();
    UserstatusEntity getStatusById(int id);
}
