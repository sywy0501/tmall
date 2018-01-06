package com.cs.tmall.service;

import com.cs.tmall.pojo.Property;

import java.util.List;

/**
 * @author cs
 * @version V1.0
 * @Title: tmall_ssm
 * @Package com.cs.tmall.service
 * @Description: TODO
 * @date 2018/1/2 上午 08:59
 */
public interface PropertyService {
    void add(Property property);
    void delete(int id);
    void update(Property property);
    Property get(int id);
    List list(int cid);
}
