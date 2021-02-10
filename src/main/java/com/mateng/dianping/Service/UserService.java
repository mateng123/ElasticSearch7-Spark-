package com.mateng.dianping.Service;

import com.mateng.dianping.common.BusinessException;
import com.mateng.dianping.model.UserModel;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * @ClassName: dianping
 * @description:
 * @author: Mr.Ma
 * @Date: 2020-09-12 18:22
 * @Version 1.0
 **/
public interface UserService {

    UserModel getUser(Integer id);

    UserModel register(UserModel registerUser) throws BusinessException, UnsupportedEncodingException, NoSuchAlgorithmException;

    UserModel login(String telphone,String password) throws UnsupportedEncodingException, NoSuchAlgorithmException, BusinessException;
}
