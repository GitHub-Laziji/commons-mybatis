package org.laziji.commons.mybatis.model;

import java.util.Date;

public abstract class BaseDO extends BasePOJO implements DO {

    private Date gmtCreate;
    private Date gmtModified;

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
}
