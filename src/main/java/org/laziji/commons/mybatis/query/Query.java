package org.laziji.commons.mybatis.query;

import org.laziji.commons.mybatis.model.POJO;

public interface Query<T extends POJO> {

    Order ASC_ORDER = Order.ASC;
    Order DESC_ORDER = Order.DESC;

    Column RAND_COLUMN = Column.RAND;
    Column ID_COLUMN = Column.ID;
    Column GMT_CREATE_COLUMN = Column.GMT_CREATE;
    Column GMT_MODIFIED_COLUMN = Column.GMT_MODIFIED;

    T toBean();

    enum Order{
        ASC,DESC
    }

    enum Column{
        RAND("rand()"),ID,GMT_CREATE,GMT_MODIFIED;

        private String value;

        Column(){

        }

        Column(String value){
            this.value = value;
        }

        @Override
        public String toString() {
            return value==null?super.toString():value;
        }
    }
}
