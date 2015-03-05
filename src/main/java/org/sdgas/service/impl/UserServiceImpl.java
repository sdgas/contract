package org.sdgas.service.impl;

import org.sdgas.base.DaoSupport;
import org.sdgas.model.User;
import org.sdgas.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by 120378 on 2015-03-05.
 */
@Service
@Transactional
public class UserServiceImpl extends DaoSupport<User> implements UserService {

    @Override
    public User findById(String userId) {
        return this.find(User.class, userId);
    }
}
