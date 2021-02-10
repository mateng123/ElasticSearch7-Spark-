package com.mateng.dianping.Controller;

import com.mateng.dianping.Service.UserService;
import com.mateng.dianping.common.*;
import com.mateng.dianping.model.UserModel;
import com.mateng.dianping.request.LoginReq;
import com.mateng.dianping.request.RegisterReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * @ClassName: dianping
 * @description:
 * @author: Mr.Ma
 * @Date: 2020-09-12 18:21
 * @Version 1.0
 **/
@Controller
@RequestMapping("/user")
public class UserController {

    public static final String CURRENT_USER_SESSION = "currentUserSession";

    @Autowired
    private HttpServletRequest httpServletRequest;

    @Autowired
    private UserService userService;

    @RequestMapping("/test")
    @ResponseBody  //以字符串方式返回
    public String test(){
        return "test";
    }

    @RequestMapping("/index")
    public ModelAndView index(){
        String userName = "imooc";
        ModelAndView modelAndView = new ModelAndView("/index.html");
        modelAndView.addObject("name",userName);
        return modelAndView;
    }

    @RequestMapping("/get")
    @ResponseBody
    public CommonRes getUser(@RequestParam(name="id") Integer id) throws BusinessException {
        UserModel userModel = userService.getUser(id);

        if (userModel == null){
            //return CommonRes.create(new CommonError(EmBusinessError.NO_OBJECT_FOUND),"fail");
            throw new BusinessException(EmBusinessError.NO_OBJECT_FOUND);
        }else {
            return CommonRes.create(userModel);
        }

    }

    @RequestMapping("/register")
    @ResponseBody
    public CommonRes register(@Valid @RequestBody RegisterReq registerReq, BindingResult bindingResult) throws BusinessException, UnsupportedEncodingException, NoSuchAlgorithmException {
        if (bindingResult.hasErrors()){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, CommonUtil.processErrorString(bindingResult));
        }
        UserModel registerModel = new UserModel();
        registerModel.setPassword(registerReq.getPassword());
        registerModel.setTelphone(registerReq.getTelephone());
        registerModel.setNickName(registerReq.getNickName());
        registerModel.setGender(registerReq.getGender());

        UserModel userModel = userService.register(registerModel);

        return CommonRes.create(userModel);
    }

    @RequestMapping("login")
    @ResponseBody
    public CommonRes login(@RequestBody @Valid LoginReq loginReq,BindingResult bindingResult) throws BusinessException, UnsupportedEncodingException, NoSuchAlgorithmException {
        if (bindingResult.hasErrors()){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,CommonUtil.processErrorString(bindingResult));
        }
        UserModel userModel = userService.login(loginReq.getTelephone(),loginReq.getPassword());
        httpServletRequest.getSession().setAttribute(CURRENT_USER_SESSION,userModel);

        return CommonRes.create(userModel);
    }

    @RequestMapping("logout")
    @ResponseBody
    public CommonRes logout(@RequestBody @Valid LoginReq loginReq,BindingResult bindingResult) throws BusinessException, UnsupportedEncodingException, NoSuchAlgorithmException {
        httpServletRequest.getSession().invalidate();
        return CommonRes.create(null);
    }

    //获取当前用户信息
    @RequestMapping("getCurrentUser")
    @ResponseBody
    public CommonRes getCurrentUser(){
        UserModel userModel = (UserModel) httpServletRequest.getSession().getAttribute(CURRENT_USER_SESSION);
        return CommonRes.create(userModel);
    }
}
