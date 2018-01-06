package com.cs.tmall.service;

import com.cs.tmall.pojo.Product;
import com.cs.tmall.pojo.ProductImage;

import java.util.List;

/**
 * @author cs
 * @version V1.0
 * @Title: tmall_ssm
 * @Package com.cs.tmall.service
 * @Description: TODO
 * @date 2018/1/3 下午 03:08
 */
public interface ProductImageService {
    String TYPE_SINGLE = "type_single";
    String TYPE_DETAIL = "type_detail";

    void add(ProductImage productImage);
    void delete(int id);
    void update(ProductImage productImage);
    ProductImage get(int id);
    List list(int pid,String type);
}
