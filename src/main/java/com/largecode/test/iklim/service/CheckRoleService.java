package com.largecode.test.iklim.service;

import com.largecode.test.iklim.exception.UserNotFoundException;
import com.largecode.test.iklim.exception.UserNotPermissionException;

import javax.transaction.Transactional;
import java.util.Collection;

/**
 * Created by iklimovskiy on 02.12.2015.
 */
public interface CheckRoleService {

    static enum Role {
        ADMIN,
        USER
    }

    void containRole(String login, Role role) throws UserNotFoundException, UserNotPermissionException;

}
