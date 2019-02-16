package org.laziji.commons.mybatis.dao;

import org.apache.ibatis.annotations.SelectProvider;
import org.laziji.commons.mybatis.model.POJO;
import org.laziji.commons.mybatis.query.Query;

import java.util.List;

public interface Dao<T extends POJO> {

    @SelectProvider(type = SqlProvider.class, method = "select")
    List<T> select(Query<T> query);

    @SelectProvider(type = SqlProvider.class, method = "selectCount")
    int selectCount(Query<T> query);
}

