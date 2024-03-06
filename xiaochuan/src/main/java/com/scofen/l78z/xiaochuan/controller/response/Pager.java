package com.scofen.l78z.xiaochuan.controller.response;

import lombok.Data;

import java.io.Serializable;

/**
 * @author yunmu
 * 分页查询的数据
 */
@Data
public class Pager implements Serializable {
    private static final long serialVersionUID = -6919430804040925693L;

    private static final Integer DEFAULT_PAGE_SIZE = 20;
    private static final Integer DEFAULT_FIRST_PAGE = 1;
    private static final Long DEFAULT_TOTAL = 0L;

    private Integer currentPage;
    private Integer totalPage;
    private Integer pageSize;
    private Long total;

    private int startRow;
    private int endRow;


    public Pager() {
        this(DEFAULT_FIRST_PAGE, DEFAULT_PAGE_SIZE);
    }

    public Pager(Integer currentPage, Integer pageSize) {
        this.currentPage = processCurrentPage(currentPage);
        this.pageSize = processPageSize(pageSize);
        this.total = DEFAULT_TOTAL;
        this.totalPage = calculateTotalPage();

        setCurrentPage(currentPage);
        setPageSize(pageSize);
    }


    public Pager(Integer currentPage, Integer pageSize, Long total) {
        this.currentPage = processCurrentPage(currentPage);
        this.pageSize = processPageSize(pageSize);
        this.total = total;
        this.totalPage = calculateTotalPage();

        setCurrentPage(currentPage);
        setPageSize(pageSize);
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = processCurrentPage(currentPage);
        setStartEndRow();
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = processPageSize(pageSize);
        setStartEndRow();
        this.totalPage = calculateTotalPage();
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        if (total == null) {
            throw new IllegalArgumentException("total can't be null.");
        }
        this.total = total;
        int current = this.getCurrentPage().intValue();
        this.totalPage = calculateTotalPage();
        int lastPage = this.getTotalPage();
        if (current > lastPage) {
            this.setCurrentPage(new Integer(lastPage));
        }
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    protected Integer processCurrentPage(Integer currentPage) {
        int minCurrentPage = 1;
        if (currentPage == null || currentPage < minCurrentPage) {
            currentPage = 1;
        }
        return currentPage;
    }

    protected Integer processPageSize(Integer pageSize) {
        //查找表连接器最大返回10000行数据
        int maxPageSize = 10000;
        if (pageSize == null || pageSize < 1) {
            pageSize = 20;
        }else if (pageSize > maxPageSize){
            pageSize = maxPageSize;
        }
        return pageSize;
    }

    public Integer getOffset() {
        return startRow;
    }

    public Integer getLimit() {
        return pageSize;
    }

    private void setStartEndRow() {
        this.startRow = this.getPageSize().intValue() * (this.getCurrentPage().intValue() - 1);
        this.endRow = this.startRow + this.getPageSize().intValue() - 1;
    }

    public int calculateTotalPage() {
        int pgSize = this.getPageSize().intValue();
        int total = this.getTotal().intValue();
        int result = total / pgSize;
        if ((total == 0) || ((total % pgSize) != 0)) {
            result++;
        }
        return result;
    }
}
