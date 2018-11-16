package org.laziji.commons.mybatis.controller;

import org.laziji.commons.mybatis.model.POJO;
import org.laziji.commons.mybatis.query.Query;

public interface Controller<T extends POJO, Q extends Query<T>> {

    String LIST = "list";
    String SAVE = "save";
    String UPDATE = "update";
    String REMOVE = "remove";

    ResponseData list(Q query);

    void beforeList(Q query);

    void afterList(Q query);
}
