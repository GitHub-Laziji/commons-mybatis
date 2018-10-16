package org.laziji.commons.mybatis.controller;

import org.laziji.commons.mybatis.model.DO;
import org.laziji.commons.mybatis.query.Query;

public interface DOController<T extends DO, Q extends Query<T>> extends Controller<T, Q> {

    ResponseData save(T bean);

    ResponseData update(T bean);
}
