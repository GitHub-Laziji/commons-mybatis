package org.laziji.commons.mybatis.controller;

import org.laziji.commons.mybatis.model.DO;
import org.laziji.commons.mybatis.query.Query;

public interface ReadOnlyDOController<T extends DO, Q extends Query<T>> extends Controller<T, Q> {

}
