package org.sdgas.service;

import org.sdgas.base.DAO;
import org.sdgas.model.User;

/**
 * Created by 120378 on 2015-03-05.
 */
public interface UserService extends DAO{

    /**
     * 根据用户名查找用户
     *
     * @param userId 佛燃工号
     * @return 用户
     */
    public User findById(String userId);
}
