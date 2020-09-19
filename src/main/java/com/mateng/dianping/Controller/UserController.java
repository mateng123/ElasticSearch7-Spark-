package com.mateng.dianping.Controller;

import com.mateng.dianping.Service.UserService;
import com.mateng.dianping.common.BusinessException;
import com.mateng.dianping.common.CommonError;
import com.mateng.dianping.common.CommonRes;
import com.mateng.dianping.common.EmBusinessError;
import com.mateng.dianping.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @ClassName: dianping
 * @description:
 * @author: Mr.Ma
 * @Date: 2020-09-12 18:21
 * @Version 1.0
 **/
@Controller("/user")
@RequestMapping("/user")
public class UserController {

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


}
