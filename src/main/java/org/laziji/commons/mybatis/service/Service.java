package org.laziji.commons.mybatis.service;

import org.laziji.commons.mybatis.model.POJO;
import org.laziji.commons.mybatis.query.Query;

import java.util.List;

public interface Service<T extends POJO> {

    List<T> select(T bean);

    List<T> select(Query<T> query);

    List<T> selectAll();

    int selectCount(T bean);

    int selectCount(Query<T> query);

    int selectTotal();

    T selectOne(T bean);

    T selectOne(Query<T> query);

    T selectFirstOne(T bean);

    T selectFirstOne(Query<T> query);

    Page<T> selectPage(Query<T> query);
}
