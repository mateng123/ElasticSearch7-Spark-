package com.mateng.dianping.Controller;

import com.mateng.dianping.Service.UserService;
import com.mateng.dianping.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @RequestMapping("/get")
    @ResponseBody
    public UserModel getUser(@RequestParam(name="id") Integer id){
        return userService.getUser(id);
    }
}
