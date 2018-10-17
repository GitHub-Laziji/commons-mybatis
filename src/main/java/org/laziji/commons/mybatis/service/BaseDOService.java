package org.laziji.commons.mybatis.service;

import org.laziji.commons.mybatis.dao.DODao;
import org.laziji.commons.mybatis.model.DO;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseDOService<T extends DO> extends BaseService<T> implements DOService<T> {

    @Autowired
    private DODao<T> mapper;

    @Override
    public T selectById(Long id) {
        return mapper.selectById(id);
    }

    @Override
    public int insert(T bean) {
        return mapper.insert(bean);
    }

    @Override
    public int update(T bean) {
        return mapper.update(bean);
    }

    @Override
    public int delete(Long id) {
        return mapper.delete(id);
    }
}
