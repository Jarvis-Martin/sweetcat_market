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
import java.util.List;

/**
 * @Author: Coder_Jarvis
 * @Description:
 * @Date: 2021-11-2021/11/9-12:30
 * @Version: 1.0
 */
@MappedJdbcTypes(JdbcType.VARCHAR)
@MappedTypes({List.class})
public class ListCharTypeHandler extends BaseTypeHandler<List<String>> {
    /**
     * 用于将java类型设置到预处理语句中
     */
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, List list, JdbcType jdbcType) throws SQLException {
        preparedStatement.setString(i, JSONUtils.toJson(list));
    }

    /**
     * 将数据库的返回结果转换为java类型
     */
    @Override
    public List getNullableResult(ResultSet resultSet, String s) throws SQLException {
        return JSONUtils.fromJson(resultSet.getString(s), List.class);

    }

    /**
     * 将数据库的返回结果转换为java类型
     */
    @Override
    public List getNullableResult(ResultSet resultSet, int i) throws SQLException {
        return JSONUtils.fromJson(resultSet.getString(i), List.class);
    }

    /**
     * 将数据库的返回结果转换为java类型，该方法在存储过程里面使用
     */
    @Override
    public List getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        return callableStatement.getObject(i, List.class);
    }
}
