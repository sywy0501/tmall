package com.cs.tmall.service;

import com.cs.tmall.pojo.Category;
import com.cs.tmall.pojo.Product;

import java.util.List;

/**
 * @author cs
 * @version V1.0
 * @Title: tmall_ssm
 * @Package com.cs.tmall.service
 * @Description: TODO
 * @date 2018/1/2 下午 02:59
 */
public interface ProductService {
    void add(Product p);
    void delete(int id);
    void update(Product p);
    List list(int cid);
    Product get(int id);
    void setFirstProductImage(Product p);

    void fill(List<Category> categories);
    void fill(Category category);
    void fillByRow(List<Category> categories);

    void setSaleAndReviewNumber(Product p);
    void setSaleAndReviewNumber(List<Product> products);
}
