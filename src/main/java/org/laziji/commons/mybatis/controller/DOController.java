package org.laziji.commons.mybatis.controller;

import org.laziji.commons.mybatis.model.DO;
import org.laziji.commons.mybatis.query.Query;

public interface DOController<T extends DO, Q extends Query<T>> extends Controller<T, Q> {

    ResponseData save(T bean);

    ResponseData update(T bean);

    ResponseData remove(T bean);

    void beforeSave(T bean);
    void afterSave(T bean);
    void beforeUpdate(T bean);
    void afterUpdate(T bean);
    void beforeRemove(T bean);
    void afterRemove(T bean);
}
