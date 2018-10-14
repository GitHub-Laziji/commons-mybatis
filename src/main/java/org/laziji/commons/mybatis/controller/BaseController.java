package org.laziji.commons.mybatis.controller;

import org.laziji.commons.mybatis.query.Query;
import org.laziji.commons.mybatis.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

public abstract class BaseController<D, Q extends Query<D>> implements Controller<D,Q> {

    @Autowired
    private Service<D> service;

    @Override
    @RequestMapping("list")
    public ResponseData list(@RequestBody Q query) {
        return ResponseData.successResponse(service.selectPageByQuery(query));
    }

    @Override
    @RequestMapping("save")
    public ResponseData save(@RequestBody D bean) {
        service.insert(bean);
        return ResponseData.successResponse();
    }

    @Override
    @RequestMapping("update")
    public ResponseData update(@RequestBody D bean) {
        service.update(bean);
        return ResponseData.successResponse();
    }

}
