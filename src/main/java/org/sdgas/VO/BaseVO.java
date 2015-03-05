package org.sdgas.VO;

import org.sdgas.base.PageView;

public class BaseVO {
    private PageView pageView;
    private int page = 1;
    private int pageSize = 10;
    private String resultMessage;
    private int flag;

    public PageView getPageView() {
        return pageView;
    }

    public void setPageView(PageView pageView) {
        this.pageView = pageView;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public int getPage() {
        return page < 1 ? 1 : page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

}
