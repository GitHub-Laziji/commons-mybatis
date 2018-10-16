package org.laziji.commons.mybatis.query;

import com.alibaba.fastjson.JSON;
import org.laziji.commons.mybatis.model.POJO;

public abstract class BaseQuery<T extends POJO> implements Query<T> {

    private Integer limit = 1;
    private Integer page = 0;
    private Integer offset = 0;
    private String sort;
    private String order;

    private Long id;

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit > 0 ? limit : 1;
        this.offset = this.page * this.limit;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page >= 0 ? page : 0;
        this.offset = this.page * this.limit;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

}
