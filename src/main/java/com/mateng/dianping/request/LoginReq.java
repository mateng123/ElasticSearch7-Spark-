package com.mateng.dianping.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author Teng.ma
 * @date 2021-02-10 14:25
 * @description
 */
@Data
public class LoginReq {

    @NotBlank(message = "手机号不能为空")
    private String telphone;

    @NotBlank(message = "密码不能为空")
    private String password;


}
