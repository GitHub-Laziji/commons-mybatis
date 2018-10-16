package org.laziji.commons.mybatis.service;

import org.laziji.commons.mybatis.model.DO;

public interface DOService<T extends DO> extends Service<T> {

    int insert(T bean);

    int update(T bean);

    int delete(Long id);
}
