package com.mateng.dianping.common;

import lombok.Data;

/**
 * @ClassName: dianping
 * @description:
 * @author: Mr.Ma
 * @Date: 2020-09-19 14:53
 * @Version 1.0
 **/
@Data
public class BusinessException extends Exception {

    private CommonError commonError;

    public BusinessException(EmBusinessError emBusinessError){
        super();
        this.commonError = new CommonError(emBusinessError);
    }
}
