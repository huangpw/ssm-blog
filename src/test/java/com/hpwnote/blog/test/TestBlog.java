package com.hpwnote.blog.test;

import com.hpwnote.blog.back.bean.Article;
import com.hpwnote.blog.back.mapper.ArticleMapper;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
//        int i = articleMapper.insert(article);
        System.out.println("插入成功：" + i);
    }

    @Test
    public void test02() {
        ArticleMapper articleMapper = (ArticleMapper)beanFactory.getBean("articleMapper");
        Article article = new Article();
        // 方式一
//        article.setAid("71");
//        article.setDigest("文章摘要71");
//        article.setTitle("文章标题71");
//        article.setContent("文章内容71");
//        int i = articleMapper.updateByPrimaryKeySelective(article);

        article.setDigest("文章摘要71-5");
        article.setTitle("文章标题71-5");
        article.setContent("文章内容71-5");
        //需求把cid=5的文章修改 参数:实体类的字节码
        Example example = new Example(Article.class);
        //参数1:实体类的属性名 参数2:实际的参数
        Example.Criteria criteria = example.createCriteria()
                                    .andEqualTo("cid", "5")
                                    .andEqualTo("aid", "71");
        int i = articleMapper.updateByExampleSelective(article, example);
        System.out.println("更新成功：" + i);
    }

    @Test
    public void test03() {
        ArticleMapper articleMapper = (ArticleMapper)beanFactory.getBean("articleMapper");
        // 删除方式一
        // int i = articleMapper.deleteByPrimaryKey("71");
        // 删除方式二
//        Article article = new Article();
//        article.setAid("70");
//        int i = articleMapper.delete(article);
        //删除方式三
        Example example = new Example(Article.class);
        example.createCriteria().andGreaterThanOrEqualTo("aid", "72");
        int i = articleMapper.deleteByExample(example);
        System.out.println("删除成功：" + i);
    }

    @Test
    public void test04() {
        ArticleMapper articleMapper = (ArticleMapper)beanFactory.getBean("articleMapper");
        //Article article = new Article();
        //article.setAid("59");
        //Article one = articleMapper.selectOne(article);
        //System.out.println(one);
        //
        //List<Article> articles = articleMapper.selectAll();
        //articles.forEach(System.out::println);

        //List<Integer> values = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 64);

        Example example = new Example(Article.class);
        //_:1个字符 %:0-n个字符
        //需求:查询title中包含学习并且创建时间>2021-01-13文章信息
        String title = "学习";
        String create_time = "2021-01-13";
        example.createCriteria()
                .andLike("title","%" + title + "%")
                .andGreaterThan("create_time",create_time);
                //.andIn("aid",values);

        List<Article> articles = articleMapper.selectByExample(example);

        System.out.println(articles);



    }

    @Test
    public void test05() {

    }
}
