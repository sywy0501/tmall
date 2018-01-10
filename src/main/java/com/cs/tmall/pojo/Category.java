package com.cs.tmall.pojo;

import java.util.List;

public class Category {
    private Integer id;

    private String name;

    /**
     * 每个分类下的产品
     **/
    private List<Product> products;

    /**
     *  每个分类对应多个List<Product>，这个属性是为了在每个分类右边显示推荐产品列表
     **/
    private List<List<Product>> productsByRow;

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<List<Product>> getProductsByRow() {
        return productsByRow;
    }

    public void setProductsByRow(List<List<Product>> productsByRow) {
        this.productsByRow = productsByRow;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
}