package org.laziji.commons.mybatis.controller;

import org.laziji.commons.mybatis.model.DO;
import org.laziji.commons.mybatis.query.Query;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

public abstract class BaseReadOnlyDOController<T extends DO, Q extends Query<T>>
        extends BaseController<T, Q> implements DOController<T, Q> {

    @Override
    @RequestMapping(SAVE)
    public ResponseData save(@RequestBody T bean) {
        return ResponseData.ERROR_RESPONSE;
    }

    @Override
    @RequestMapping(UPDATE)
    public ResponseData update(@RequestBody T bean) {
        return ResponseData.ERROR_RESPONSE;
    }
}
