package com.mateng.dianping.common;

import lombok.Data;

/**
 * @ClassName: dianping
 * @description:
 * @author: Mr.Ma
 * @Date: 2020-09-19 14:28
 * @Version 1.0
 **/
@Data
public class CommonError {

    //错误码
    private Integer errCode;

    //错误描述码
    private String errMsg;

    public CommonError(Integer errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    public CommonError(EmBusinessError emBusinessError){
        this.errCode = emBusinessError.getErrCode();
        this.errMsg = emBusinessError.getErrMsg();
    }
}
