package org.laziji.commons.mybatis.service;


import org.laziji.commons.mybatis.query.Query;

import java.util.List;

public interface Service<D> {

    D selectById(Long id);

    List<D> select(D bean);

    int selectCount(D bean);

    List<D> selectByQuery(Query<D> query);

    int selectCountByQuery(Query<D> query);

    List<D> selectAll();

    D selectOne(D bean);

    D selectOneByQuery(Query<D> query);

    Page<D> selectPageByQuery(Query<D> query);


    int insert(D bean);

    int update(D bean);

    int delete(Long id);


}
