package com.mateng.dianping.Controller;

import com.mateng.dianping.Controller.admin.AdminController;
import com.mateng.dianping.common.AdminPremission;
import com.mateng.dianping.common.CommonError;
import com.mateng.dianping.common.CommonRes;
import com.mateng.dianping.common.EmBusinessError;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @author Teng.ma
 * @date 2021-02-13 13:51
 * @description
 */
@Aspect
@Configuration
public class ControllerAspect {

    @Autowired
    private HttpServletRequest httpServletRequest;

    @Autowired
    private HttpServletResponse httpServletResponse;

    @Around("execution(* com.mateng.dianping.Controller.admin.*.*(..)) && " +
            "@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public Object adminControllerBeforeValidation(ProceedingJoinPoint joinPoint) throws Throwable {
        Method method = ((MethodSignature)joinPoint.getSignature()).getMethod();
        method.getAnnotation(AdminPremission.class);
        AdminPremission adminPremission = method.getAnnotation(AdminPremission.class);
        if (adminPremission == null){
            //公共方法
            Object resultObject = joinPoint.proceed();
            return resultObject;
        }
        //判断当前管理员是否登录
        String email = (String) httpServletRequest.getSession().getAttribute(AdminController.CURRENT_ADMIN_SESSION);
        if (email == null){
            if (adminPremission.produceType().equals("text/html")){
                httpServletResponse.sendRedirect("/admin/admin/loginpage");
                return null;
            } else {
                CommonError commonError = new CommonError(EmBusinessError.ADMIN_SHOULD_LOGIN);
                return CommonRes.create(commonError,"fail");
            }

        }else{
            Object resultObject = joinPoint.proceed();
            return resultObject;
        }
    }

}
