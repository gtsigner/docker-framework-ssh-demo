package org.oeynet.godtoy.base;


import java.util.List;

/**
 * 定义一个分页对象
 *
 * @author 0
 */
public class Page {

    //当前页码
    private int currentPageNo = 1;
    //总分页数量
    private int totalPageCount;
    //记录总数
    private int totalCount;//总条数
    private int pageSize = 15;//每页显示条数
    //自动计算出页面偏移
    private int pageOffset;


    public int getCurrentPageNo() {
        return currentPageNo;
    }

    public void setCuttentPageNo(int pageNo) {
        //如果当前页码大于0，才设置当前页码值
        if (pageNo > 0) {
            this.currentPageNo = pageNo;
        } else {
            this.currentPageNo = 1;
        }
    }


    public int getPageOffset() {
        if (this.getTotalCount() % this.pageSize == 0) {
            this.totalPageCount = this.getTotalCount() / this.pageSize;
        } else if (this.getTotalCount() % this.pageSize > 0) {
            this.totalPageCount = this.getTotalCount() / this.pageSize + 1;
        } else {
            this.totalPageCount = 1;
        }
        return pageSize * (currentPageNo - 1);
    }

    public int getPageOffset(int currentPageNo) {
        this.currentPageNo = currentPageNo;
        return this.getCurrentPageNo();
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }


}