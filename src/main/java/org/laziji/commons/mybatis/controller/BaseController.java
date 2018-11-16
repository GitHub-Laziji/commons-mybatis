package org.laziji.commons.mybatis.controller;

import org.laziji.commons.mybatis.model.POJO;
import org.laziji.commons.mybatis.query.Query;
import org.laziji.commons.mybatis.service.Page;
import org.laziji.commons.mybatis.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

public abstract class BaseController<D extends POJO, Q extends Query<D>> implements Controller<D, Q> {

    @Autowired
    private Service<D> service;

    @Override
    @RequestMapping(LIST)
    public ResponseData list(@RequestBody Q query) {
        beforeList(query);
        Page<D> page = service.selectPage(query);
        afterList(query);
        return ResponseData.successResponse(page);
    }

    @Override
    public void beforeList(Q query){

    }

    @Override
    public void afterList(Q query){

    }
}
