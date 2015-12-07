package com.largecode.test.iklim.service.impl;

import com.largecode.test.iklim.entity.User;
import com.largecode.test.iklim.exception.UserNotFoundException;
import com.largecode.test.iklim.exception.UserNotPermissionException;
import com.largecode.test.iklim.repository.UserRepository;
import com.largecode.test.iklim.service.CheckRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Objects;

/**
 * Created by iklimovskiy on 02.12.2015.
 */
@Service
@org.springframework.transaction.annotation.Transactional
public class DBCheckRoleService implements CheckRoleService {

    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public void containRole(String login, Role role) throws UserNotFoundException, UserNotPermissionException {
        User user = userRepository.findOne(login);
        if (Objects.isNull(user)) {
            throw new UserNotFoundException(login);
        }
        boolean roleCheck = false;
        switch (role) {
            case ADMIN: roleCheck = user.isHaveAdminRole(); break;
            case USER: roleCheck = !user.isHaveAdminRole(); break;
        }
        if (!roleCheck) {
            throw  new UserNotPermissionException(login);
        }
    }
}
