package org.laziji.commons.mybatis.service;

import org.laziji.commons.mybatis.model.POJO;
import org.laziji.commons.mybatis.query.Query;

import java.util.List;

public interface Service<T extends POJO> {

    List<T> select(Query<T> query);

    List<T> selectAll();

    List<T> selectAll(Query<T> query);

    int selectCount(Query<T> query);

    int selectTotal();

    T selectOne(Query<T> query);

    T selectFirstOne(Query<T> query);

    Page<T> selectPage(Query<T> query);
}
