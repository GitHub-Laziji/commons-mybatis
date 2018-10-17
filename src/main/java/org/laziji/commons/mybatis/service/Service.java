package org.laziji.commons.mybatis.service;

import org.laziji.commons.mybatis.model.POJO;
import org.laziji.commons.mybatis.query.Query;

import java.util.List;

public interface Service<T extends POJO> {

    List<T> select(T bean);

    int selectCount(T bean);

    List<T> selectAll();

    T selectOne(T bean);

    List<T> selectByQuery(Query<T> query);

    int selectCountByQuery(Query<T> query);

    T selectOneByQuery(Query<T> query);

    Page<T> selectPageByQuery(Query<T> query);
}
