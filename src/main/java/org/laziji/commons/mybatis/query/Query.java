package org.laziji.commons.mybatis.query;

import org.laziji.commons.mybatis.model.POJO;

public interface Query<T extends POJO> {

    T toBean();
}
