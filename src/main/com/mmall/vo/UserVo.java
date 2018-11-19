package com.mmall.vo;

import lombok.Data;

/**
 * the class is create by @Author:oweson
 *
 * @Date：2018/11/19 0019 19:52
 */
@Data
public class UserVo {
    private Integer id;
    private String uername;
    private String password;
    private String email;
    private String phone;
    private String question;
    private String answer;
    private Integer role;
    private String ceateTime;
    private String updateTime;


}
