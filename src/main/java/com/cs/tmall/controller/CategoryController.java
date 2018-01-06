package com.cs.tmall.controller;

import com.cs.tmall.pojo.Category;
import com.cs.tmall.service.CategoryService;
import com.cs.tmall.util.ImageUtil;
import com.cs.tmall.util.Page;
import com.cs.tmall.util.UploadImageFile;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author cs
 * @version V1.0
 * @Title: tmall_ssm
 * @Package com.cs.tmall.controller
 * @Description: TODO
 * @date 2017/12/30 下午 04:29
 */
@Controller
@RequestMapping("")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @RequestMapping("admin_category_list")
    public String list(Model model, Page page){
        PageHelper.offsetPage(page.getStart(),page.getCount());

        List<Category> cs = categoryService.list();
        int total = (int) new PageInfo<>(cs).getTotal();
        page.setTotal(total);

        model.addAttribute("cs",cs);
        model.addAttribute("page",page);

        return "admin/listCategory";
    }

    @RequestMapping("admin_category_add")
    public String add(UploadImageFile uploadImageFile, HttpSession httpSession, Category category) throws IOException {

        categoryService.add(category);

        File imageFolder = new File(httpSession.getServletContext().getRealPath("img/category"));
        File file = new File(imageFolder, category.getId()+".jpg");

        if (!file.getParentFile().exists()){
            file.getParentFile().mkdirs();
        }
        uploadImageFile.getImage().transferTo(file);
        BufferedImage image = ImageUtil.change2jpg(file);
        ImageIO.write(image,"jpg",file);

        return "redirect:/admin_category_list";

    }

    @RequestMapping("admin_category_delete")
    public String delete(int id,HttpSession httpSession){
        categoryService.delete(id);

        File imageFolder = new File(httpSession.getServletContext().getRealPath("img/category"));
        File file = new File(imageFolder,id+".jpg");
        file.delete();

        return "redirect:/admin_category_list";
    }

    @RequestMapping("admin_category_edit")
    public String edit(int id,Model model){
        Category category = categoryService.get(id);

        model.addAttribute("c",category);
        return "admin/editCategory";
    }

    @RequestMapping("admin_category_update")
    public String update(Category category,HttpSession httpSession,UploadImageFile uploadImageFile) throws IOException {
        categoryService.update(category);
        MultipartFile image = uploadImageFile.getImage();
        if(null!=image&&!image.isEmpty()){
            File imageFolder = new File(httpSession.getServletContext().getRealPath("img/category"));
            File file = new File(imageFolder, category.getId()+".jpg");
            image.transferTo(file);
            BufferedImage img = ImageUtil.change2jpg(file);
            ImageIO.write(img,"jpg",file);
        }
        return "redirect:/admin_category_list";
    }
}
