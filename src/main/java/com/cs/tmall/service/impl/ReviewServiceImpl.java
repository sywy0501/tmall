package com.cs.tmall.service.impl;

import com.cs.tmall.mapper.ReviewMapper;
import com.cs.tmall.pojo.Review;
import com.cs.tmall.pojo.ReviewExample;
import com.cs.tmall.pojo.User;
import com.cs.tmall.service.ReviewService;
import com.cs.tmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author cs
 * @version V1.0
 * @Title: tmall_ssm
 * @Package com.cs.tmall.service.impl
 * @Description: TODO
 * @date 2018/1/10 下午 04:47
 */
@Service
public class ReviewServiceImpl implements ReviewService{
    @Autowired
    ReviewMapper reviewMapper;
    @Autowired
    UserService userService;
    @Override
    public void add(Review review) {
        reviewMapper.insert(review);
    }

    @Override
    public void delete(int id) {
        reviewMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Review review) {
        reviewMapper.updateByPrimaryKeySelective(review);
    }

    @Override
    public Review get(int id) {
        return reviewMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Review> list(int pid) {
        ReviewExample example = new ReviewExample();
        example.createCriteria().andPidEqualTo(pid);
        example.setOrderByClause("id desc");

        List<Review> result = reviewMapper.selectByExample(example);
        setUser(result);
        return result;
    }

    public void setUser(List<Review> reviews){
        for (Review review:reviews){
            setUser(review);
        }
    }

    public void setUser(Review review){
        int uid = review.getUid();
        User user = userService.get(uid);
        review.setUser(user);
    }

    @Override
    public int getCount(int pid) {
        return list(pid).size();
    }
}
