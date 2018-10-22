package org.laziji.commons.mybatis.service;

import org.laziji.commons.mybatis.dao.Dao;
import org.laziji.commons.mybatis.model.POJO;
import org.laziji.commons.mybatis.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseService<T extends POJO> implements Service<T> {

    @Autowired
    private Dao<T> mapper;

    @Override
    public List<T> select(T bean) {
        return mapper.select(bean);
    }

    @Override
    public List<T> select(Query<T> query) {
        return mapper.select(query);
    }

    @Override
    public List<T> selectAll() {
        return select((T)null);
    }

    @Override
    public int selectCount(T bean) {
        return mapper.selectCount(bean);
    }

    @Override
    public int selectCount(Query<T> query) {
        return mapper.selectCount(query);
    }

    @Override
    public int selectTotal() {
        return mapper.selectCount((T)null);
    }

    @Override
    public T selectOne(T bean) {
        if (selectCount(bean) != 1) {
            return null;
        }
        List<T> list = select(bean);
        return list.get(0);
    }

    @Override
    public T selectOne(Query<T> query) {
        if (selectCount(query) != 1) {
            return null;
        }
        List<T> list = select(query);
        return list.get(0);
    }

    @Override
    public T selectFirstOne(Query<T> query) {
        List<T> list = select(query);
        if(list==null||list.size()==0){
            return null;
        }
        return list.get(0);
    }

    @Override
    public T selectFirstOne(T bean) {
        List<T> list = select(bean);
        if(list==null||list.size()==0){
            return null;
        }
        return list.get(0);
    }

    @Override
    public Page<T> selectPage(Query<T> query) {
        Page<T> page = new Page<>();
        int count = mapper.selectCount(query);
        page.setTotal(count);
        if (count == 0) {
            page.setList(new ArrayList<>());
            return page;
        }
        page.setList(mapper.select(query));
        return page;
    }
}
