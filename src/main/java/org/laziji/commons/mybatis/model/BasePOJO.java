package org.laziji.commons.mybatis.model;

import com.alibaba.fastjson.JSON;

public abstract class BasePOJO implements POJO {

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
