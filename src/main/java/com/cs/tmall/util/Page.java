package com.cs.tmall.util;

/**
 * @author cs
 * @version V1.0
 * @Title: tmall_ssm
 * @Package com.cs.tmall.util
 * @Description: TODO
 * @date 2017/12/27 上午 08:20
 */
public class Page {
    /**
     * start开始页数
     */
    private int start;
    /**
     * count每页显示个数
     */
    private int count;
    /**
     * total总个数
     */
    private int total;
    /**
     * param参数
     */
    private String param;
    /**
     * DEFAULT_COUNT默认每页显示5条数据
     */
    private static final int DEFAULT_COUNT = 5;

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public Page() {
        count = DEFAULT_COUNT;
    }

    public Page(int start, int count) {
        this();
        this.start = start;
        this.count = count;
    }
    /**
     * 判断是否有前一页
     * */
    public boolean isHasPrevious() {
        if (start == 0) {
            return false;
        }
        return true;
    }
    /**
     * 判断是否有后一页
     * */
    public boolean isHasNext() {
        if (start == getLast()) {
            return false;
        }
        return true;
    }

    /**
     * 根据每页显示的数量count以及总共有多少数据total，计算出总共有多少页
     */
    public int getTotalPage() {
        int totalPage;
        //假设总数是50，能够被5整除，那么就有10页
        if (0 == total % count) {
            totalPage = total / count;
        } else {
            //假设总数是51，不能被5整除，那么就有11页
            totalPage = total / count + 1;
        }
        if (0 == totalPage) {
            totalPage = 1;
        }
        return totalPage;
    }

    /**
     * 计算最后一页开始数
     */
    public int getLast() {
        int last;
        //假设总数是50，是能够被5整除的，那么最后一页的开始就是45
        if (0 == total % count) {
            last = total - count;
        } else {
            //假设总数是51，不能被5整除的，那么最后一页的开始就是50
            last = total - total % count;
        }
        last = last < 0 ? 0 : last;
        return last;
    }

    @Override
    public String toString() {
        return "Page{" +
                "start=" + start +
                ", count=" + count +
                ", total=" + total +
                ", param='" + param + '\'' +
                '}';
    }
}
