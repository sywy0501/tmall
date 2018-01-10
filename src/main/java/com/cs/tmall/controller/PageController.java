package com.cs.tmall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author cs
 * @version V1.0
 * @Title: tmall_ssm
 * @Package com.cs.tmall.controller
 * @Description: TODO
 * @date 2018/1/10 上午 10:38
 */
@Controller
@RequestMapping("")
public class PageController {
    @RequestMapping("registerPage")
    public String registerPage(){
        return "fore/register";
    }

    @RequestMapping("registerSuccessPage")
    public String registerSuccessPage(){
        return "fore/registerSuccess";
    }

    @RequestMapping("loginPage")
    public String loginPage(){
        return "fore/login";
    }

    @RequestMapping("forealipay")
    public String alipay(){
        return "fore/alipay";
    }
}
