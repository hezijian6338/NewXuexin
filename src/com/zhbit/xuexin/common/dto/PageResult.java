package com.zhbit.xuexin.common.dto;

import java.util.List;

/**
 * 分页查询返回结果实体
 * @ClassName: PageResult
 * @Description: TODO
 * @author: zouxiang
 * @date: 2017年3月24日 下午3:04:01
 */
public class PageResult {
	/**
	 * 数据
	 */
    private List<Object> data;
    /**
     * 当前页
     */
    private int currentPage;
    /**
     * 最大页数
     */
    private int maxPage;
    /**
     * 查询总数
     */
    private int count;
    /**
     * 页面大小
     */
    private int pageSize;

    public PageResult(List<Object> data, int currentPage, int count, int pageSize) {
        this.data = data;
        this.currentPage = currentPage;
        this.count = count;
        this.pageSize = pageSize;
        this.maxPage = count % pageSize != 0 ? count / pageSize + 1 : count / pageSize;
    }

    public List<Object> getData() {
        return data;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public int getMaxPage() {
        return maxPage;
    }

    public int getCount() {
        return count;
    }

    public int getPageSize() {
        return pageSize;
    }
}
