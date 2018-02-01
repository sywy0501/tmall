package com.cs.tmall.interceptor;

import com.cs.tmall.pojo.Category;
import com.cs.tmall.pojo.OrderItem;
import com.cs.tmall.pojo.User;
import com.cs.tmall.service.CategoryService;
import com.cs.tmall.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author cs
 * @version V1.0
 * @Title: tmall_ssm
 * @Package com.cs.tmall.interceptor
 * @Description: TODO
 * @date 2018/2/1 上午 09:23
 */
public class OtherInterceptor extends HandlerInterceptorAdapter{
    @Autowired
    CategoryService categoryService;
    @Autowired
    OrderItemService orderItemService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object handler)throws Exception{
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)throws Exception{
        List<Category> cs = categoryService.list();
        request.getSession().setAttribute("cs",cs);

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        int cartTotalItemNumber = 0;
        if (null!=user){
            List<OrderItem> ois = orderItemService.listByUser(user.getId());
            for (OrderItem oi:ois){
                cartTotalItemNumber+=oi.getNumber();
            }
        }
        request.getSession().setAttribute("cartTotalItemNumber",cartTotalItemNumber);
    }

    @Override
    public void afterCompletion(HttpServletRequest request,HttpServletResponse response,Object handler,Exception ex)throws Exception{

    }
}
