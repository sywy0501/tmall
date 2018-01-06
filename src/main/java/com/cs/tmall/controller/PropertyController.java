package com.cs.tmall.controller;

import com.cs.tmall.pojo.Category;
import com.cs.tmall.pojo.Property;
import com.cs.tmall.service.CategoryService;
import com.cs.tmall.service.PropertyService;
import com.cs.tmall.util.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author cs
 * @version V1.0
 * @Title: tmall_ssm
 * @Package com.cs.tmall.controller
 * @Description: TODO
 * @date 2018/1/2 上午 09:24
 */
@Controller
@RequestMapping("")
public class PropertyController {
    @Autowired
    PropertyService propertyService;
    @Autowired
    CategoryService categoryService;

    @RequestMapping("admin_property_add")
    public String add(Model model, Property property){
        propertyService.add(property);
        return "redirect:admin_property_list?cid="+property.getCid();
    }

    @RequestMapping("admin_property_delete")
    public String delete(int id){
        Property property = propertyService.get(id);
        propertyService.delete(id);

        return "redirect:admin_property_list?cid="+property.getCid();
    }

    @RequestMapping("admin_property_edit")
    public String edit(Model model,int id){
        Property property = propertyService.get(id);
        Category category = categoryService.get(property.getCid());
        property.setCategory(category);
        model.addAttribute("p",property);
        return "admin/editProperty";
    }

    @RequestMapping("admin_property_update")
    public String update(Property property){
        propertyService.update(property);

        return "redirect:/admin_property_list?cid="+property.getCid();
    }

    @RequestMapping("admin_property_list")
    public String list(int cid, Model model, Page page){
        Category category = categoryService.get(cid);

        //通过PageHelper设置分页参数
        PageHelper.offsetPage(page.getStart(),page.getCount());
        //基于cid，获取当前分类下的属性集合
        List<Property> ps = propertyService.list(cid);
        //通过PageInfo获取属性总数
        int total = (int) new PageInfo<>(ps).getTotal();
        //把总数设置给分页page对象
        page.setTotal(total);
        //拼接字符串“&cid=”+c.getId(),设置给page对象的Param值。
        //因为属性分页都是基于当前分类下的分页，所以分页的时候需要传递这个cid
        page.setParam("&cid="+category.getId());
        //属性集合设置到request的“ps”属性上
        model.addAttribute("ps",ps);
        //把分类对象设置到request的“c”属性上
        model.addAttribute("c",category);
        //把分页对象设置到request的“page”对象上
        model.addAttribute("page",page);

        return "admin/listProperty";
    }
}
