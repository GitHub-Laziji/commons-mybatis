package org.laziji.commons.mybatis.dao;


import org.laziji.commons.mybatis.query.Query;

import java.util.List;

public interface Dao<D> {

    D selectById(Long id);

    List<D> select(D bean);

    int selectCount(D bean);

    List<D> select(Query<D> query);

    int selectCount(Query<D> query);

    int insert(D bean);

    int update(D bean);

    int delete(Long id);

}

