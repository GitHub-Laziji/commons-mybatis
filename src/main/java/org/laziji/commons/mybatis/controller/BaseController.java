package org.laziji.commons.mybatis.controller;

import org.laziji.commons.mybatis.model.DO;
import org.laziji.commons.mybatis.query.Query;
import org.laziji.commons.mybatis.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

public abstract class BaseController<D extends DO, Q extends Query<D>> implements Controller<D, Q> {

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
        if (service.insert(bean) != 1) {
            return ResponseData.INSERT_ERROR_RESPONSE;
        }
        return ResponseData.SUCCESS_RESPONSE;
    }

    @Override
    @RequestMapping("update")
    public ResponseData update(@RequestBody D bean) {
        bean.setGmtModified(new Date());
        if (service.update(bean) != 1) {
            return ResponseData.UPDATE_ERROR_RESPONSE;
        }
        return ResponseData.SUCCESS_RESPONSE;
    }

}
