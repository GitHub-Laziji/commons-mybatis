package org.laziji.commons.mybatis.dao;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.builder.annotation.ProviderContext;
import org.apache.ibatis.jdbc.SQL;
import org.laziji.commons.mybatis.model.POJO;
import org.laziji.commons.mybatis.query.Query;
import org.springframework.core.ResolvableType;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Map;

public class SqlProvider {


    public <T extends POJO> String select(ProviderContext context, Query<T> query) {
        Class<?> entityClass = getEntityClass(context);
        assert entityClass != null;
        StringBuilder sql = new StringBuilder();
        sql.append("select ")
                .append(getColumns(entityClass))
                .append(" from")
                .append(getTableName(entityClass))
                .append(getQueryWhere(query, entityClass));

        JSONObject queryObj = JSON.parseObject(JSON.toJSONString(query));
        if (queryObj.get("sort") != null) {
            sql.append(" order by `").append(queryObj.getString("sort")).append("` ");
            if (queryObj.get("order") != null) {
                sql.append(queryObj.getString("order"));
            }
        }
        if (queryObj.get("offset") != null && queryObj.get("limit") != null) {
            sql.append(" limit #{offset}, #{limit}");
        }
        return sql.toString();
    }

    public <T extends POJO> String selectCount(ProviderContext context, Query<T> query) {
        Class<?> entityClass = getEntityClass(context);
        assert entityClass != null;
        return "select count(*) from "
                + getTableName(entityClass)
                + getQueryWhere(query, entityClass);
    }

    public String selectById(ProviderContext context) {
        Class<?> entityClass = getEntityClass(context);
        assert entityClass != null;
        return new SQL()
                .SELECT(getColumns(entityClass))
                .FROM(getTableName(entityClass))
                .WHERE("`id` = #{id}")
                .toString();
    }

    public <T extends POJO> String insert(ProviderContext context, T bean) {
        Class<?> entityClass = getEntityClass(context);
        assert entityClass != null;
        SQL sql = new SQL();
        sql.INSERT_INTO(getTableName(entityClass));
        JSONObject beanObj = JSON.parseObject(JSON.toJSONString(bean));
        for (Map.Entry<String, Object> entry : beanObj.entrySet()) {
            sql.VALUES("`" + conversionName(entry.getKey()) + "`", "#{" + entry.getKey() + "}");
        }
        return sql.toString();
    }

    public <T extends POJO> String update(ProviderContext context, T bean) {
        Class<?> entityClass = getEntityClass(context);
        assert entityClass != null;
        return new SQL()
                .UPDATE(getTableName(entityClass))
                .SET(getBeanSet(bean))
                .WHERE("`id` = #{id}")
                .toString();
    }

    public String delete(ProviderContext context) {
        Class<?> entityClass = getEntityClass(context);
        assert entityClass != null;
        return new SQL()
                .DELETE_FROM(getTableName(entityClass))
                .WHERE("`id` = #{value}")
                .toString();
    }


    private Class<?> getEntityClass(ProviderContext context) {
        Class<?> mapperType = context.getMapperType();

        for (Type parent : mapperType.getGenericInterfaces()) {
            ResolvableType parentType = ResolvableType.forType(parent);
            if (parentType.getRawClass() == Dao.class
                    || parentType.getRawClass() == DODao.class
                    || parentType.getRawClass() == VODao.class) {

                return parentType.getGeneric(0).getRawClass();
            }
        }
        return null;
    }

    private String getColumns(Class clazz) {
        StringBuilder columns = new StringBuilder();
        for (Method method : clazz.getMethods()) {
            String name = method.getName();
            if (name.length() > 3 && name.startsWith("set")
                    && name.charAt(3) >= 'A' && name.charAt(3) <= 'Z') {
                String fieldName = (char) (name.charAt(3) - 'A' + 'a') + name.substring(4);
                columns.append(",`").append(conversionName(fieldName)).append("` as `")
                        .append(fieldName).append("`");
            }
        }
        if (columns.length() == 0) {
            return " * ";
        }
        return " " + columns.substring(1) + " ";
    }

    private String getQueryWhere(Query query, Class clazz) {

        StringBuilder wheres = new StringBuilder();
        JSONObject queryObj = JSON.parseObject(JSON.toJSONString(query));
        for (Method method : clazz.getMethods()) {
            String name = method.getName();
            if (name.length() > 3 && name.startsWith("set")
                    && name.charAt(3) >= 'A' && name.charAt(3) <= 'Z') {
                String fieldName = (char) (name.charAt(3) - 'A' + 'a') + name.substring(4);
                if (queryObj.get(fieldName) != null) {
                    wheres.append("and `")
                            .append(conversionName(fieldName))
                            .append("` = #{").append(fieldName).append("}");
                }

                if (method.getReturnType().equals(String.class) && queryObj.get(fieldName + "Like") != null) {
                    wheres.append("and `")
                            .append(conversionName(fieldName))
                            .append("` like CONCAT('%',#{")
                            .append(fieldName).append("}, '%')");
                }
            }
        }

        if (wheres.length() == 0) {
            return " ";
        }
        return " where " + wheres.substring(4) + " ";
    }

    private <T extends POJO> String getBeanSet(T bean) {
        JSONObject beanObj = JSON.parseObject(JSON.toJSONString(bean));
        StringBuilder set = new StringBuilder();
        for (Map.Entry<String, Object> entry : beanObj.entrySet()) {
            set.append(",`").append(conversionName(entry.getKey()))
                    .append("`=#{").append(entry.getKey()).append("}");
        }
        if (set.length() == 0) {
            return "";
        }
        return set.substring(1);
    }


    private String getTableName(Class clazz) {
        return " `" + conversionName(clazz.getSimpleName()) + "` ";
    }


    private String conversionName(String name) {
        char[] chars = name.toCharArray();
        StringBuilder stringBuilder = new StringBuilder();
        for (char ch : chars) {
            if (ch >= 'A' && ch <= 'Z') {
                if (stringBuilder.length() > 0) {
                    stringBuilder.append('_');
                }
                stringBuilder.append((char) (ch - 'A' + 'a'));
            } else {
                stringBuilder.append(ch);
            }
        }
        return stringBuilder.toString();
    }
}
