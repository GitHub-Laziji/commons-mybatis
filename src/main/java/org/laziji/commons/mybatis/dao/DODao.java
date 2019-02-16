package org.laziji.commons.mybatis.dao;

import org.apache.ibatis.annotations.*;
import org.laziji.commons.mybatis.model.DO;

public interface DODao<T extends DO> extends Dao<T> {

    @SelectProvider(type = SqlProvider.class, method = "selectById")
    T selectById(Long id);

    @InsertProvider(type = SqlProvider.class, method = "insert")
    @Options(useGeneratedKeys = true, keyColumn = "id")
    int insert(T bean);

    @UpdateProvider(type = SqlProvider.class, method = "update")
    int update(T bean);

    @DeleteProvider(type = SqlProvider.class, method = "delete")
    int delete(Long id);
}
