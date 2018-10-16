package org.laziji.commons.mybatis.controller;

import org.laziji.commons.mybatis.model.VO;
import org.laziji.commons.mybatis.query.Query;

public interface VOController<T extends VO, Q extends Query<T>> extends Controller<T, Q> {

}
