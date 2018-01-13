package com.cs.tmall.service;

import com.cs.tmall.pojo.Review;

import java.util.List;

/**
 * @author cs
 * @version V1.0
 * @Title: tmall_ssm
 * @Package com.cs.tmall.service
 * @Description: TODO
 * @date 2018/1/10 下午 04:25
 */
public interface ReviewService {
    void add(Review review);
    void delete(int id);
    void update(Review review);
    Review get(int id);
    List<Review> list(int pid);

    int getCount(int pid);
}
