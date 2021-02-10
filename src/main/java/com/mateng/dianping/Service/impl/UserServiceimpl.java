package com.mateng.dianping.Service.impl;

import com.mateng.dianping.Service.UserService;
import com.mateng.dianping.common.BusinessException;
import com.mateng.dianping.common.EmBusinessError;
import com.mateng.dianping.dal.UserModelMapper;
import com.mateng.dianping.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

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

    @Override
    @Transactional
    public UserModel register(UserModel registerUser) throws BusinessException, UnsupportedEncodingException, NoSuchAlgorithmException {
        registerUser.setPassword(encodeByMd5((registerUser.getPassword())));
        registerUser.setCreatedAt(new Date());
        registerUser.setUpdateAt(new Date());

        try {
            userModelMapper.insertSelective(registerUser);
        }catch (DuplicateKeyException e){
            throw new BusinessException(EmBusinessError.REGISTER_DUP_FAIL);
        }

        return getUser(registerUser.getId());
    }

    @Override
    public UserModel login(String telphone, String password) throws UnsupportedEncodingException, NoSuchAlgorithmException, BusinessException {
        UserModel userModel = userModelMapper.selectByTelphoneAndPassword(telphone,encodeByMd5(password));
        if (userModel == null ){
            throw new BusinessException(EmBusinessError.LOGIN_FAIL);
        }
        return userModel;
    }

    private String encodeByMd5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        //确定计算方法MD5
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        BASE64Encoder base64Encoder = new BASE64Encoder();
        return base64Encoder.encode(messageDigest.digest(str.getBytes("utf-8")));
    }
}
