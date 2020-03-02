package com.keep.keep_backfront.util;

import java.util.List;


public class PageBean<T> {
    // 当前页
    private Integer currentPage;
    // 每页显示的总条数
    private Integer pageSize;
    // 总条数
    private Long totalNum;
    // 是否有下一页
    private Integer isMore;
    // 总页数
    private Long totalPage;
    // 开始索引
    private Integer startIndex;
    // 分页结果
    private List<T> items;

    public PageBean(Integer currentPage, Integer pageSize, Long totalNum) {
        super();
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.totalNum = totalNum;
        this.totalPage = totalNum % pageSize == 0 ? totalNum/pageSize-1 : totalNum/pageSize;
        this.startIndex = currentPage == 0 ? 0 : currentPage * pageSize;
        this.isMore = this.currentPage >= this.totalPage? 0 : 1;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Long getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Long totalNum) {
        this.totalNum = totalNum;
    }

    public Integer getIsMore() {
        return isMore;
    }

    public void setIsMore(Integer isMore) {
        this.isMore = isMore;
    }

    public Long getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Long totalPage) {
        this.totalPage = totalPage;
    }

    public Integer getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(Integer startIndex) {
        this.startIndex = startIndex;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }
}
