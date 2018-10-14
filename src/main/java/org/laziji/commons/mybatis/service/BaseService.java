package org.laziji.commons.mybatis.service;


import org.laziji.commons.mybatis.dao.Dao;
import org.laziji.commons.mybatis.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public abstract class BaseService<D> implements Service<D> {

    @Autowired
    private Dao<D> mapper;

    @Override
    public D selectById(Long id){
        return mapper.selectById(id);
    }

    @Override
    public List<D> select(D bean){
        return mapper.select(bean);
    }

    @Override
    public int selectCount(D bean){
        return mapper.selectCount(bean);
    }

    @Override
    public List<D> selectByQuery(Query<D> query){
        return mapper.select(query);
    }

    @Override
    public int selectCountByQuery(Query<D> query){
        return mapper.selectCount(query);
    }

    @Override
    public List<D> selectAll(){
        return select(null);
    }

    @Override
    public D selectOne(D bean){
        if(selectCount(bean)!=1){
            return null;
        }
        List<D> list = select(bean);
        return list.get(0);
    }

    @Override
    public D selectOneByQuery(Query<D> query){
        if(selectCountByQuery(query)!=1){
            return null;
        }
        List<D> list = selectByQuery(query);
        return list.get(0);
    }

    @Override
    public Page<D> selectPageByQuery(Query<D> query){
        Page<D> page = new Page<>();
        page.setList(selectByQuery(query));
        page.setTotal(selectCountByQuery(query));
        return page;
    }


    @Override
    public int insert(D bean){
        return mapper.insert(bean);
    }

    @Override
    public int update(D bean){
        return mapper.update(bean);
    }

    @Override
    public int delete(Long id){
        return mapper.delete(id);
    }

}
