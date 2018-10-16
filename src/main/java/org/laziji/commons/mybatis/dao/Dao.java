package org.laziji.commons.mybatis.dao;

import org.laziji.commons.mybatis.model.POJO;
import org.laziji.commons.mybatis.query.Query;

import java.util.List;

public interface Dao<T extends POJO> {

    T selectById(Long id);

    List<T> select(T bean);

    int selectCount(T bean);

    List<T> select(Query<T> query);

    int selectCount(Query<T> query);
}

