package com.cs.tmall.service;

import com.cs.tmall.pojo.User;

import java.util.List;

/**
 * @author cs
 * @version V1.0
 * @Title: tmall_ssm
 * @Package com.cs.tmall.service
 * @Description: TODO
 * @date 2018/1/4 下午 04:36
 */
public interface UserService {
    void add(User user);
    void delete(int id);
    void update(User user);
    boolean isExist(String str);

    User get(int id);
    List<User> list();
}
