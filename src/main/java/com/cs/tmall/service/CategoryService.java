package com.cs.tmall.service;

import com.cs.tmall.pojo.Category;
import com.cs.tmall.util.Page;

import java.util.List;

/**
 * @author cs
 * @version V1.0
 * @Title: tmall_ssm
 * @Package com.cs.tmall.service
 * @Description: TODO
 * @date 2017/12/30 下午 04:25
 */
public interface CategoryService {

    List<Category> list();

    void add(Category category);

    void delete(Integer id);

    Category get(Integer id);

    void update(Category category);
}
