package com.cs.tmall.comparator;

import com.cs.tmall.pojo.Product;

import java.util.Comparator;

/**
 * @author cs
 * @version V1.0
 * @Title: tmall_ssm
 * @Package com.cs.tmall.comparator
 * @Description: 综合比较器，将销量*评价高的放前面
 * @date 2018/1/15 下午 04:03
 */
public class ProductAllComparator implements Comparator<Product>{

    @Override
    public int compare(Product o1, Product o2) {
        return o2.getReviewCount()*o2.getSaleCount()-o1.getReviewCount()*o1.getSaleCount();
    }
}
