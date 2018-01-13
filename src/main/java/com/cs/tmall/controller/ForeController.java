package com.cs.tmall.controller;

import com.cs.tmall.pojo.*;
import com.cs.tmall.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author cs
 * @version V1.0
 * @Title: tmall_ssm
 * @Package com.cs.tmall.controller
 * @Description: TODO
 * @date 2018/1/8 上午 09:44
 */
@Controller
@RequestMapping
public class ForeController {

    public static String USERNAME_EXIST="用户名已经被使用,不能使用";
    public static String WORNG_LOGIN="账号密码错误";

    @Autowired
    PropertyValueService propertyValueService;
    @Autowired
    OrderService orderService;
    @Autowired
    OrderItemService orderItemService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductService productService;
    @Autowired
    ProductImageService productImageService;
    @Autowired
    UserService userService;
    @Autowired
    ReviewService reviewService;

    @RequestMapping("forehome")
    public String home(Model model){
        List<Category> cs = categoryService.list();
        productService.fill(cs);
        productService.fillByRow(cs);
        model.addAttribute("cs",cs);
        return "fore/home";
    }

    @RequestMapping("foreregister")
    public String register(Model model, User user){
        String name = user.getName();
        name = HtmlUtils.htmlEscape(name);
        user.setName(name);
        boolean exist = userService.isExist(name);

        if (exist){
            model.addAttribute("msg",USERNAME_EXIST);
            model.addAttribute("user",null);
            return "fore/register";
        }
        userService.add(user);

        return "redirect:registerSuccessPage";
    }

    @RequestMapping("forelogin")
    public String login(@RequestParam("name")String name, @RequestParam("password")String password, Model model, HttpSession httpSession){
        name = HtmlUtils.htmlEscape(name);
        User user = userService.get(name,password);

        if (null==user){
            model.addAttribute("msg",WORNG_LOGIN);
            return "fore/login";
        }
        httpSession.setAttribute("user",user);
        return "redirect:forehome";
    }

    @RequestMapping("forelogout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "redirect:forehome";
    }

    @RequestMapping("foreproduct")
    public String product(int pid,Model model){
        Product product = productService.get(pid);

        List<ProductImage> productSingleImages = productImageService.list(pid,ProductImageService.TYPE_SINGLE);
        List<ProductImage> productDetailImages = productImageService.list(pid,ProductImageService.TYPE_DETAIL);
        product.setProductDetailImages(productDetailImages);
        product.setProductSingleImages(productSingleImages);

        List<PropertyValue> pvs = propertyValueService.list(product.getId());
        List<Review> reviews = reviewService.list(product.getId());
        productService.setSaleAndReviewNumber(product);

        model.addAttribute("reviews",reviews);
        model.addAttribute("p",product);
        model.addAttribute("pvs",pvs);
        return "fore/product";
    }
}
