package org.laziji.commons.mybatis.controller;

import org.laziji.commons.mybatis.model.VO;
import org.laziji.commons.mybatis.query.Query;

public abstract class BaseVOController<T extends VO, Q extends Query<T>> extends BaseController<T, Q> implements VOController<T, Q> {

}
