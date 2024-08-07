package com.hpwnote.blog.base.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Enumeration;

/**
 * Company :
 * Author :   Andy
 * Description : 负责页面的统一跳转
 */
@Controller
public class ViewController {

    //localhost:8080/blog/add?name=..&title=...
    //localhost:8080/blog/add/zhangsan/旅游  workbench/article/index
    @RequestMapping("/toView/{firstView}/{secondView}/{thirdView}")
    public String toView(
            @PathVariable("firstView") String firstView,
            @PathVariable("secondView") String secondView ,
            @PathVariable("thirdView") String thirdView, HttpServletRequest request){

        //获取前台所有参数名字
        Enumeration<String> names = request.getParameterNames();
        while(names.hasMoreElements()){
            String name = names.nextElement();
            String value = request.getParameter(name);
            //设置到request域中
            request.setAttribute(name,value);
        }
        //File.separator:\
        return firstView + File.separator + secondView + File.separator + thirdView;
    }
}
