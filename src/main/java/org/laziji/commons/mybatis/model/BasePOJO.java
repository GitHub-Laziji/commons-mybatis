package org.laziji.commons.mybatis.model;

import com.alibaba.fastjson.JSON;

public abstract class BasePOJO implements POJO {
    private Long id;

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
