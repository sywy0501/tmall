package com.cs.tmall.service;

import com.cs.tmall.pojo.Product;
import com.cs.tmall.pojo.Property;
import com.cs.tmall.pojo.PropertyValue;

import java.util.List;

/**
 * @author cs
 * @version V1.0
 * @Title: tmall_ssm
 * @Package com.cs.tmall.service
 * @Description: TODO
 * @date 2018/1/4 下午 02:39
 */
public interface PropertyValueService {
    void init(Product product);
    void update(PropertyValue propertyValue);

    PropertyValue get(int pid,int ptid);
    List<PropertyValue> list(int pid);
}
