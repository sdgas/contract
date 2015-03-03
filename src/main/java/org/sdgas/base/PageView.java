package org.sdgas.base;

import org.sdgas.util.WebTool;

import java.util.List;

public class PageView<T> {
    /**
     * 当前页 *
     */
    private int currentPage = 1;
    /**
     * 每页显示记录数量 *
     */
    private int maxResult = 12;
    /**
     * 页码开始和结束索引 *
     */
    private PageIndex pageIndex;
    /**
     * 分页数据 *
     */
    private List<T> records;
    /**
     * 总页数 *
     */
    private long totalPage = 1;
    /**
     * 总记录数 *
     */
    private long totalRecord;
    /**
     * 每页显示的页码 *
     */
    private long pageCode = 10;

    /**
     * 通过构造方法强制定义每页显示的数量和当前页 *
     */
    public PageView(int maxResult, int currentPage) {
        this.maxResult = maxResult;
        this.currentPage = currentPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public int getMaxResult() {
        return maxResult;
    }

    public PageIndex getPageIndex() {
        return pageIndex;
    }

    public List<T> getRecords() {
        return records;
    }

    public long getTotalPage() {
        return totalPage;
    }

    public long getTotalRecord() {
        return totalRecord;
    }

    /**
     * 设置查询结果 *
     *
     * @param qr
     */
    public void setQueryResult(QueryResult qr) {
        setTotalRecord(qr.getTotalResult());
        setRecords(qr.getResultList());
    }

    public void setRecords(List<T> records) {
        this.records = records;
    }

    public void setTotalPage(long totalPage) {
        this.totalPage = totalPage;
        this.pageIndex = WebTool.getPageIndex(maxResult, currentPage, totalPage);
    }

    public void setTotalRecord(long totalRecord) {
        this.totalRecord = totalRecord;
        setTotalPage(totalPage = this.totalRecord % this.maxResult == 0 ? this.totalRecord
                / this.maxResult
                : this.totalRecord / this.maxResult + 1);
    }

    public long getPageCode() {
        return pageCode;
    }

    public void setPageCode(long pageCode) {
        this.pageCode = pageCode;
    }

}
