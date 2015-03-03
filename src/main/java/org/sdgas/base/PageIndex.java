package org.sdgas.base;

/**
 * 分页功能的索引
 *
 * @author Nickle
 */
public class PageIndex {
    private long startIndex;  //开始索引标识
    private long endIndex;    //结束索引标识

    public PageIndex(long startIndex, long endIndex) {
        this.startIndex = startIndex;
        this.endIndex = endIndex;
    }

    public long getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(long startIndex) {
        this.startIndex = startIndex;
    }

    public long getEndIndex() {
        return endIndex;
    }

    public void setEndIndex(long endIndex) {
        this.endIndex = endIndex;
    }
}
