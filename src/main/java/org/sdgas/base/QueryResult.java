package org.sdgas.base;

import java.util.List;

public class QueryResult<T> {
    private List<T> resultList;
    private Long totalResult;

    public List<T> getResultList() {
        return resultList;
    }

    public void setResultList(List<T> resultList) {
        this.resultList = resultList;
    }

    public Long getTotalResult() {
        return totalResult;
    }

    public void setTotalResult(Long totalResult) {
        this.totalResult = totalResult;
    }
}

