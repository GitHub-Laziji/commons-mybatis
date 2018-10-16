package org.laziji.commons.mybatis.model;

import com.alibaba.fastjson.JSON;

import java.util.Date;

public class BaseDO implements DO {

    private Long id;
    private Date gmtCreate;
    private Date gmtModified;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Date getGmtCreate() {
        return gmtCreate;
    }

    @Override
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    @Override
    public Date getGmtModified() {
        return gmtModified;
    }

    @Override
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    @Override
    public String toString(){
        return JSON.toJSONString(this);
    }
}
