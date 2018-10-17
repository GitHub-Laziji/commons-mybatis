package org.laziji.commons.mybatis.service;

import org.laziji.commons.mybatis.model.DO;

public interface DOService<T extends DO> extends Service<T> {

    T selectById(Long id);

    int insert(T bean);

    int update(T bean);

    int delete(Long id);
}
