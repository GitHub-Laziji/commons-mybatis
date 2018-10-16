package org.laziji.commons.mybatis.query;

import org.laziji.commons.mybatis.model.DO;

import java.util.Date;

public interface Query<D extends DO> {

    Long getId();

    void setId(Long id);

    Date getGmtCreate();

    void setGmtCreate(Date gmtCreate);

    Date getGmtModified();

    void setGmtModified(Date gmtModified);

    D toBean();
}
