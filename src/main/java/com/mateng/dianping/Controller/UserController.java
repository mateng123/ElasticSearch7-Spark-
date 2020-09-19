package com.mateng.dianping.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @RequestMapping("/test")
    @ResponseBody  //以字符串方式返回
    public String test(){
        return "test";
    }

}
