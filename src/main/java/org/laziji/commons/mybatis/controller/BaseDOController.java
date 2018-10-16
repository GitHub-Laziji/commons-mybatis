package org.laziji.commons.mybatis.controller;

import org.laziji.commons.mybatis.model.DO;
import org.laziji.commons.mybatis.query.Query;
import org.laziji.commons.mybatis.service.DOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

public abstract class BaseDOController<T extends DO, Q extends Query<T>> extends BaseController<T, Q> implements DOController<T, Q> {

    @Autowired
    private DOService<T> service;

    @Override
    @RequestMapping(SAVE)
    public ResponseData save(@RequestBody T bean) {
        if (service.insert(bean) != 1) {
            return ResponseData.INSERT_ERROR_RESPONSE;
        }
        return ResponseData.SUCCESS_RESPONSE;
    }

    @Override
    @RequestMapping(UPDATE)
    public ResponseData update(@RequestBody T bean) {
        bean.setGmtModified(new Date());
        if (service.update(bean) != 1) {
            return ResponseData.UPDATE_ERROR_RESPONSE;
        }
        return ResponseData.SUCCESS_RESPONSE;
    }
}
