package com.hpwnote.blog.test;

import com.hpwnote.blog.back.bean.Article;
import com.hpwnote.blog.back.mapper.ArticleMapper;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author huangpw
 * @date 2024/8/6 17:50
 * 博客： https://blog.csdn.net/qq_18167779?type=blog
 * @description:
 */
public class TestBlog {

    private BeanFactory beanFactory =
            new ClassPathXmlApplicationContext("spring/applicationContext.xml");

//    private ArticleMapper articleMapper =
//            (ArticleMapper) beanFactory.getBean("articleMapper");

    @Test
    public void test01() {
        ArticleMapper articleMapper = (ArticleMapper)beanFactory.getBean("articleMapper");
        Article article = new Article();
        article.setDigest("文章摘要");
        article.setTitle("文章标题");
        article.setContent("文章内容");
        // 如果数据中数据是null的时候，就不参与插入操作
        int i = articleMapper.insertSelective(article);
        System.out.println("插入成功：" + i);
    }
}
