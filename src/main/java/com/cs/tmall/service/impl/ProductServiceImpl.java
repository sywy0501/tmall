package com.cs.tmall.service.impl;

import com.cs.tmall.mapper.ProductMapper;
import com.cs.tmall.pojo.Category;
import com.cs.tmall.pojo.Product;
import com.cs.tmall.pojo.ProductExample;
import com.cs.tmall.pojo.ProductImage;
import com.cs.tmall.service.CategoryService;
import com.cs.tmall.service.ProductImageService;
import com.cs.tmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author cs
 * @version V1.0
 * @Title: tmall_ssm
 * @Package com.cs.tmall.service.impl
 * @Description: TODO
 * @date 2018/1/2 下午 03:03
 */
@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    ProductMapper productMapper;
    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductImageService productImageService;
    @Override
    public void add(Product product) {
        productMapper.insert(product);
    }

    @Override
    public void delete(int id) {
        productMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Product product) {
        productMapper.updateByPrimaryKeySelective(product);
    }

    @Override
    public List list(int cid) {
        ProductExample productExample = new ProductExample();
        productExample.createCriteria().andCidEqualTo(cid);
        productExample.setOrderByClause("id desc");

        List result = productMapper.selectByExample(productExample);
        setCategory(result);
        setFirstProductImage(result);
        return result;
    }

    @Override
    public Product get(int id) {
        Product p = productMapper.selectByPrimaryKey(id);
        setFirstProductImage(p);
        setCategory(p);
        return p;
    }
    /**
     * 根据pid和图片类型查询出所有的单个图片，然后将第一个取出来放在firstProductImage上
     * 在get方法中调用为单个产品设置图片
     * */
    @Override
    public void setFirstProductImage(Product product) {
        List<ProductImage> pis = productImageService.list(product.getId(),ProductImageService.TYPE_SINGLE);
        if (!pis.isEmpty()){
            ProductImage pi = pis.get(0);
            product.setFirstProductImage(pi);
        }
    }
    /**
     * 给多个产品设置图片
     * 在list方法中调用为多个产品设置图片
     * */
    public void setFirstProductImage(List<Product> ps){
        for (Product p:ps){
            setFirstProductImage(p);
        }
    }

    public void setCategory(Product product){
        int cid = product.getCid();
        Category c = categoryService.get(cid);
        product.setCategory(c);
    }

    public void setCategory(List<Product> ps){
        for (Product p : ps) {
            setCategory(p);
        }
    }
}
