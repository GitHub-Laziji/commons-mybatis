package org.laziji.commons.mybatis.dao;

import org.laziji.commons.mybatis.model.DO;

public interface DODao<T extends DO> extends Dao<T> {

    int insert(T bean);

    int update(T bean);

    int delete(Long id);
}
