package com.hpwnote.blog.back.bean;

import lombok.Data;
import tk.mybatis.mapper.annotation.NameStyle;
import tk.mybatis.mapper.code.Style;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Company :  北京动力节点
 * Author :   Andy
 * Date : 2021/7/19
 * Description :
 */
@Data
@Table(name = "t_user")
@NameStyle(Style.normal)
public class User {

    @Id
    private String uid;
    private String username;
    private String nickname;
    private String password;
    private String role;
    private String createTime;
    private String lastLoginTime;
    private String state;
    private String loginIp;
    private String phone;
    private String loginCount;
    private String img;

}
