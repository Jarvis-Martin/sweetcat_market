package com.sweetcat.recommend.infrastructure.dao.typehandler;

import com.sweetcat.commons.util.JSONUtils;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/12-19:13
 * @version: 1.0
 */
@MappedJdbcTypes(JdbcType.VARCHAR)
@MappedTypes(Object.class)
public class ObjectStringTypeHandler extends BaseTypeHandler<Object> {
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, Object o, JdbcType jdbcType) throws SQLException {
        preparedStatement.setString(i, JSONUtils.toJson(o));
    }

    @Override
    public Object getNullableResult(ResultSet resultSet, String s) throws SQLException {
        return JSONUtils.fromJson(resultSet.getString(s), Object.class);
    }

    @Override
    public Object getNullableResult(ResultSet resultSet, int i) throws SQLException {
        return JSONUtils.fromJson(resultSet.getString(i), Object.class);
    }

    @Override
    public Object getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        return callableStatement.getObject(i, Object.class);
    }
}
