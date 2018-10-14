package org.laziji.commons.mybatis.controller;


import org.laziji.commons.mybatis.query.Query;

public interface Controller<D, Q extends Query<D>> {

    ResponseData list(Q query);

    ResponseData save(D bean);

    ResponseData update(D bean);
}
