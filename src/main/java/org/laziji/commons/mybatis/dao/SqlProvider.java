package org.laziji.commons.mybatis.dao;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.builder.annotation.ProviderContext;
import org.apache.ibatis.jdbc.SQL;
import org.laziji.commons.mybatis.dao.annotation.Ignore;
import org.laziji.commons.mybatis.dao.annotation.Table;
import org.laziji.commons.mybatis.model.DO;
import org.laziji.commons.mybatis.query.Query;
import org.springframework.core.ResolvableType;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.*;

public class SqlProvider {

    public String selectAll(ProviderContext context, Query query) {
        Class clazz = getEntityClass(context);
        assert clazz != null;
        StringBuilder sql = new StringBuilder(new SQL()
                .SELECT(getColumns(clazz))
                .FROM(getTableName(clazz))
                .WHERE(getWheres(query, clazz))
                .toString());
        JSONObject queryObj = parseObject(query);
        if (queryObj.get("sort") != null) {
            sql.append(" order by ").append(queryObj.getString("sort")).append(" ");
            if (queryObj.get("order") != null) {
                sql.append(queryObj.getString("order"));
            }
        }
        return sql.toString();
    }

    public String select(ProviderContext context, Query query) {
        StringBuilder sql = new StringBuilder(selectAll(context, query));
        JSONObject queryObj = parseObject(query);
        if (queryObj.get("offset") != null && queryObj.get("limit") != null) {
            sql.append(" limit #{offset}, #{limit}");
        }
        return sql.toString();
    }

    public String selectCount(ProviderContext context, Query query) {
        Class clazz = getEntityClass(context);
        assert clazz != null;
        return new SQL()
                .SELECT("count(*)")
                .FROM(getTableName(clazz))
                .WHERE(getWheres(query, clazz))
                .toString();
    }

    public String selectById(ProviderContext context) {
        Class clazz = getEntityClass(context);
        assert clazz != null;
        return new SQL()
                .SELECT(getColumns(clazz))
                .FROM(getTableName(clazz))
                .WHERE("`id`=#{id}")
                .toString();
    }

    public String insert(ProviderContext context, DO bean) {
        Class clazz = getEntityClass(context);
        assert clazz != null;
        SQL sql = new SQL();
        sql.INSERT_INTO(getTableName(clazz));
        Set<String> variables = new HashSet<>(Arrays.asList(getReadVariables(clazz)));
        JSONObject beanObj = JSON.parseObject(JSON.toJSONString(bean));
        for (String key : beanObj.keySet()) {
            if ("id".equals(key) || !variables.contains(key)) {
                continue;
            }
            sql.VALUES(String.format("`%s`", conversionName(key)), String.format("#{%s}", key));
        }
        return sql.toString();
    }

    public String update(ProviderContext context, DO bean) {
        Class clazz = getEntityClass(context);
        assert clazz != null;
        JSONObject beanObj = parseObject(bean);
        SQL sql = new SQL();
        sql.UPDATE(getTableName(clazz));
        Set<String> variables = new HashSet<>(Arrays.asList(getReadVariables(clazz)));
        for (String key : beanObj.keySet()) {
            if ("id".equals(key) || !variables.contains(key)) {
                continue;
            }
            sql.SET(String.format("`%s`=#{%s}", conversionName(key), key));
        }
        sql.WHERE("`id`=#{id}");
        return sql.toString();
    }

    public String delete(ProviderContext context) {
        Class clazz = getEntityClass(context);
        assert clazz != null;
        return new SQL()
                .DELETE_FROM(getTableName(clazz))
                .WHERE("`id`=#{id}")
                .toString();
    }


    private Class getEntityClass(ProviderContext context) {
        for (Type type : context.getMapperType().getGenericInterfaces()) {
            ResolvableType resolvableType = ResolvableType.forType(type);
            if (resolvableType.getRawClass() == Dao.class
                    || resolvableType.getRawClass() == DODao.class
                    || resolvableType.getRawClass() == VODao.class) {
                return resolvableType.getGeneric(0).getRawClass();
            }
        }
        return null;
    }

    private String getTableName(Class clazz) {
        Table annotation = (Table) clazz.getAnnotation(Table.class);
        if (annotation != null) {
            return String.format("`%s`", annotation.name());
        }
        return String.format("`%s`", conversionName(clazz.getSimpleName()));
    }

    private String[] getVariables(Class clazz, String[] prefixes) {
        List<String> variables = new ArrayList<>();
        for (Method method : clazz.getMethods()) {
            Ignore annotation = method.getAnnotation(Ignore.class);
            if (annotation != null) {
                continue;
            }
            String name = method.getName();
            for (String prefix : prefixes) {
                int length = prefix.length();
                if (name.length() > length && name.startsWith(prefix)
                        && name.charAt(length) >= 'A' && name.charAt(length) <= 'Z') {
                    String variableName = (char) (name.charAt(length) - 'A' + 'a') + name.substring(length + 1);
                    variables.add(variableName);
                    break;
                }
            }

        }
        return variables.toArray(new String[]{});
    }

    private String[] getReadVariables(Class clazz) {
        return getVariables(clazz, new String[]{"is", "get"});
    }

    private String[] getWriteVariables(Class clazz) {
        return getVariables(clazz, new String[]{"set"});
    }

    private String[] getColumns(Class clazz) {
        List<String> columns = new ArrayList<>();
        for (String variableName : getWriteVariables(clazz)) {
            columns.add(String.format("`%s` as `%s`", conversionName(variableName), variableName));
        }
        return columns.toArray(new String[]{});
    }

    private String[] getWheres(Query query, Class clazz) {
        List<String> wheres = new ArrayList<>();
        JSONObject queryObj = parseObject(query);
        for (Method method : clazz.getMethods()) {
            String name = method.getName();
            if (name.length() > 3 && name.startsWith("get")
                    && name.charAt(3) >= 'A' && name.charAt(3) <= 'Z') {
                String fieldName = (char) (name.charAt(3) - 'A' + 'a') + name.substring(4);
                if (queryObj.get(fieldName) != null) {
                    wheres.add(String.format("`%s`=#{%s}", conversionName(fieldName), fieldName));
                }
                if (method.getReturnType().equals(String.class)
                        && queryObj.get(fieldName + "Like") != null) {
                    wheres.add(String.format("`%s` like CONCAT('%%',#{%sLike}, '%%')", conversionName(fieldName), fieldName));
                }
            }
        }
        return wheres.toArray(new String[]{});
    }

    private JSONObject parseObject(Object object) {
        if (object == null) {
            return new JSONObject();
        }
        return JSON.parseObject(JSON.toJSONString(object));
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