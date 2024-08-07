package com.hpwnote.blog.back.bean;

import lombok.Data;
import tk.mybatis.mapper.annotation.NameStyle;
import tk.mybatis.mapper.code.Style;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author huangpw
 * @date 2024/8/6 17:46
 * 博客： https://blog.csdn.net/qq_18167779?type=blog
 * @description:
 */
@Data
@Table(name = "t_article")
@NameStyle(Style.normal)
public class Article {
    @Id
    private String aid;
    private String title;
    private String digest;
    private String content;
    private String cid;
    private String visit_count;
    private String create_time;
    private String update_time;
    private String is_hot;
    private String logo;
    private String uid;
    private String isOpen;
    private String thumbsUp;
    private String tagNames;
    private String isCommented;

}
