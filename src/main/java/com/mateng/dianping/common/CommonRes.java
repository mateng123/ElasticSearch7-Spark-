package com.mateng.dianping.common;

import lombok.Data;

/**
 * @ClassName: dianping
 * @description:
 * @author: Mr.Ma
 * @Date: 2020-09-19 14:07
 * @Version 1.0
 **/
@Data
public class CommonRes {

    //表明读请求的返回结果，"success"或"fail"
    private String status;

    //如果status==success是，表明对应的返回json数据
    //若status==fail时，则data内将使用通道的错误码对应的格式
    private Object data;

    //定义一个通用的创建返回方法
    public static CommonRes create(Object result){
        return CommonRes.create(result,"success");
    }

    public static CommonRes create(Object result,String status){
        CommonRes commonRes = new CommonRes();
        commonRes.setStatus(status);
        commonRes.setData(result);

        return commonRes;
    }
}
