package com.fuqiang.springbootmail.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>Title: TestController</p>
 * <p>Description: TestController</p>
 * <p>Copyright: Xi An BestTop Technologies, ltd. Copyright(c) 2018/p>
 *
 * @author Fuqiang
 * @version 0.0.0.1
 * <pre>Histroy:
 *       2020/1/3 0003 16:10 Create by Fuqiang
 * </pre>
 */
@Controller
public class PageController {


    /**
     * 默认login页面
     */
    @GetMapping("/login")
    public String toTest() {
        return "login";
    }

    /**
     * 页面间的跳转，经过controller转发
     */
    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public String toPage(HttpServletRequest request) {
        String url = request.getParameter("url");
        return url;
    }
}
