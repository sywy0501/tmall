package com.cs.tmall.comparator;

import com.cs.tmall.pojo.Product;

import java.util.Comparator;

/**
 * @author cs
 * @version V1.0
 * @Title: tmall_ssm
 * @Package com.cs.tmall.comparator
 * @Description: 价格比较器，把价格低的的放前面
 * @date 2018/1/15 下午 04:10
 */
public class ProductPriceComparator implements Comparator<Product>{

    @Override
    public int compare(Product o1, Product o2) {
        return (int) (o1.getPromotePrice()-o2.getPromotePrice());
    }
}
