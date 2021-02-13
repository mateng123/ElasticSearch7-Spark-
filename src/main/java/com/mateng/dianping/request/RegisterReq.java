package com.mateng.dianping.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author Teng.ma
 * @date 2021-02-10 13:22
 * @description
 */
@Data
public class RegisterReq {

    @NotBlank(message = "手机号不能为空")
    private String telphone;

    @NotBlank(message = "密码不能为空")
    private String password;

    @NotBlank(message = "昵称不能为空")
    private String nickName;

    @NotNull(message = "性别不能为空")
    private Integer gender;
}
