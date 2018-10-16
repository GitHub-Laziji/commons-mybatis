package org.laziji.commons.mybatis.service;

import org.laziji.commons.mybatis.model.DO;

import java.util.List;

public class Page<D extends DO> {

    private List<D> list;
    private Integer total;

    public Page(){

    }

    public Page(List<D> list, Integer total) {
        this.list = list;
        this.total = total;
    }

    public List<D> getList() {
        return list;
    }

    public void setList(List<D> list) {
        this.list = list;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
