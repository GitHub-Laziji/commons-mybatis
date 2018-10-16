package org.laziji.commons.mybatis.service;

import com.alibaba.fastjson.JSON;
import org.laziji.commons.mybatis.model.POJO;

import java.util.List;

public class Page<T extends POJO> {

    private List<T> list;
    private Integer total;

    public Page() {

    }

    public Page(List<T> list, Integer total) {
        this.list = list;
        this.total = total;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
