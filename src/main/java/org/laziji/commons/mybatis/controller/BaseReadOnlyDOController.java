package org.laziji.commons.mybatis.controller;

import org.laziji.commons.mybatis.model.DO;
import org.laziji.commons.mybatis.query.Query;

public abstract class BaseReadOnlyDOController<T extends DO, Q extends Query<T>>
        extends BaseController<T, Q> implements ReadOnlyDOController<T, Q> {

}
