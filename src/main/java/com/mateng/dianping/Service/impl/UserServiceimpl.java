package com.mateng.dianping.Service.impl;

import com.mateng.dianping.Service.UserService;
import com.mateng.dianping.dal.UserModelMapper;
import com.mateng.dianping.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName: dianping
 * @description:
 * @author: Mr.Ma
 * @Date: 2020-09-19 11:03
 * @Version 1.0
 **/
@Service
public class UserServiceimpl implements UserService {

    @Autowired
    private UserModelMapper userModelMapper;

    @Override
    public UserModel getUser(Integer id) {
        return userModelMapper.selectByPrimaryKey(id);
    }
}
