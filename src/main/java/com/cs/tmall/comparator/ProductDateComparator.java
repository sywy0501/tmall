package com.cs.tmall.comparator;

import com.cs.tmall.pojo.Product;

import java.util.Comparator;

/**
 * @author cs
 * @version V1.0
 * @Title: tmall_ssm
 * @Package com.cs.tmall.comparator
 * @Description: 新品比较器，把创建日期晚的放前面
 * @date 2018/1/15 下午 04:07
 */
public class ProductDateComparator implements Comparator<Product> {
    @Override
    public int compare(Product o1, Product o2) {
        return o1.getCreateDate().compareTo(o2.getCreateDate());
    }
}
