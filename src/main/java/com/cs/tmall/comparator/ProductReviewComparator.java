package com.cs.tmall.comparator;

import com.cs.tmall.pojo.Product;

import java.util.Comparator;

/**
 * @author cs
 * @version V1.0
 * @Title: tmall_ssm
 * @Package com.cs.tmall.comparator
 * @Description: 人气比较器，把评价数量多的放前面
 * @date 2018/1/15 下午 04:12
 */
public class ProductReviewComparator implements Comparator<Product>{
    @Override
    public int compare(Product o1, Product o2) {
        return o2.getReviewCount()-o1.getReviewCount();
    }
}
