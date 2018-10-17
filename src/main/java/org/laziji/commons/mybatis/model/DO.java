package org.laziji.commons.mybatis.model;

import java.util.Date;

public interface DO extends POJO {

    Long getId();

    void setId(Long id);

    Date getGmtCreate();

    void setGmtCreate(Date gmtCreate);

    Date getGmtModified();

    void setGmtModified(Date gmtModified);
}
