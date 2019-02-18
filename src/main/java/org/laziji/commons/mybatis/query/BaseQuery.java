package org.laziji.commons.mybatis.query;

import com.alibaba.fastjson.JSON;
import org.laziji.commons.mybatis.model.POJO;

import java.lang.reflect.ParameterizedType;

public abstract class BaseQuery<T extends POJO> implements Query<T> {

    private Integer limit = 1;
    private Integer page = 0;
    private Integer offset = 0;
    private String sort;
    private String order;

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

    public void setPage(Integer page) {
        if (page == null) {
            page = 0;
        }
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

    public void setSort(Column sort) {
        this.sort = sort == null ? null : sort.toString();
    }

    public void setSortValue(String sort) {
        this.sort = sort;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order == null ? null : order.toString();
    }

    public void setOrderValue(String order) {
        if (order == null) {
            this.order = null;
            return;
        }
        String t = order.toLowerCase();
        if (!"asc".equals(t) && !"desc".equals(t)) {
            return;
        }
        this.order = order;
    }

    @Override
    public T toBean() {
        return JSON.parseObject(JSON.toJSONString(this),
                ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

}
