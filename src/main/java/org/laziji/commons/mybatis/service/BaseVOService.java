package org.laziji.commons.mybatis.service;

import org.laziji.commons.mybatis.model.VO;

public abstract class BaseVOService<T extends VO> extends BaseService<T> implements VOService<T> {

}
