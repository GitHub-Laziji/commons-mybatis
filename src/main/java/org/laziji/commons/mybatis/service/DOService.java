package org.laziji.commons.mybatis.service;

import org.laziji.commons.mybatis.model.DO;

public interface DOService<T extends DO> extends Service<T> {

    T selectById(Long id);

    boolean insert(T bean);

    boolean update(T bean);

    boolean delete(Long id);
}
