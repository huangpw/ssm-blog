package com.hpwnote.blog.back.bean;

import lombok.Data;
import lombok.ToString;
import tk.mybatis.mapper.annotation.NameStyle;
import tk.mybatis.mapper.code.Style;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Company :
 * Author :   Andy
 * Date : 2021/1/13
 * Description :
 */
@Data
@ToString
@Table(name = "t_tag")
@NameStyle(Style.normal)
public class Tag {

    @Id
    private String tid;
    private String tname;
    private String cid;
}
