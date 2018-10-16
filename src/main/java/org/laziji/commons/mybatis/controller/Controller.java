package org.laziji.commons.mybatis.controller;


import org.laziji.commons.mybatis.model.DO;
import org.laziji.commons.mybatis.query.Query;

public interface Controller<D extends DO, Q extends Query<D>> {

    ResponseData list(Q query);

    ResponseData save(D bean);

    ResponseData update(D bean);
}
