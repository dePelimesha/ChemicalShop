package com.chemicalshop.services.serviceimplementation;

import com.chemicalshop.dao.daointerface.UserStatusDAO;
import com.chemicalshop.entities.UserstatusEntity;
import com.chemicalshop.services.serviceinterface.UserStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("userStatusService")
@Transactional
public class UserStatusServiceImpl implements UserStatusService {

    @Autowired
    UserStatusDAO userStatusDAO;

    public List<UserstatusEntity> getAllStatus() {
        return userStatusDAO.getAllStatus();
    }

    @Override
    public UserstatusEntity getStatusById(int id) {
        return userStatusDAO.getStatusById(id);
    }
}
