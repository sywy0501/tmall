package com.cs.tmall.service.impl;

import com.cs.tmall.mapper.ProductMapper;
import com.cs.tmall.pojo.Category;
import com.cs.tmall.pojo.Product;
import com.cs.tmall.pojo.ProductExample;
import com.cs.tmall.pojo.ProductImage;
import com.cs.tmall.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    ReviewService reviewService;
    @Autowired
    OrderItemService orderItemService;
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
     * 为多个分类填充产品集合
     **/
    @Override
    public void fill(List<Category> categories) {
        for (Category c:categories){
            fill(c);
        }
    }

    /**
     * 为分类填充产品集合
     * */
    @Override
    public void fill(Category category) {
        List<Product> ps = list(category.getId());
        category.setProducts(ps);
    }

    /**
     * 为多个分类填充推荐产品集合
     * 把分类下的产品集合，按照8个为一行，拆成多行，以利于后续页面上进行显示
     * */
    @Override
    public void fillByRow(List<Category> categories) {
        int productNumberEachRow = 8;
        for (Category c:categories){
            List<Product> products = c.getProducts();
            List<List<Product>> productsByRow = new ArrayList<>();
            for (int i = 0;i<products.size();i+=productNumberEachRow){
                int size = i+productNumberEachRow;
                size = size>products.size()?products.size():size;
                List<Product> productsOfEachRow = products.subList(i,size);
                productsByRow.add(productsOfEachRow);
            }
            c.setProductsByRow(productsByRow);
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

    /**
     * 为产品设置销量和评价数量
     * */
    @Override
    public void setSaleAndReviewNumber(Product p) {
        int saleCount = orderItemService.getSaleCount(p.getId());
        p.setSaleCount(saleCount);

        int reviewCount = reviewService.getCount(p.getId());
        p.setReviewCount(reviewCount);
    }

    @Override
    public void setSaleAndReviewNumber(List<Product> products) {
        for (Product p:products){
            setSaleAndReviewNumber(p);
        }
    }

    @Override
    public List<Product> search(String key) {
        ProductExample example = new ProductExample();
        example.createCriteria().andNameLike("%"+key+"%");
        example.setOrderByClause("id desc");
        List result = productMapper.selectByExample(example);
        setFirstProductImage(result);
        setCategory(result);

        return result;
    }
}
