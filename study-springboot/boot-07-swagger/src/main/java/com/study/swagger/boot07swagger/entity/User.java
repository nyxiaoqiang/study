package com.study.swagger.boot07swagger.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("用户实体")
public class User {
    @ApiModelProperty(value = "用户名", required = true, example = "李雪强")
    public String username;
    @ApiModelProperty(value = "密码", required = true, example = "123456")
    public String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
