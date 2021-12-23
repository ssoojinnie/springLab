package kr.co.songjava.configuration.mybatis.typehandler;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StringValueBooleanTypeHandler implements TypeHandler<Boolean> {
    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, Boolean parameter, JdbcType jdbcType) throws SQLException {
        preparedStatement.setString(i, BooleanUtils.toString(parameter, "Y", "N"));
    }

    @Override
    public Boolean getResult(ResultSet resultSet, String columnName) throws SQLException {
        return BooleanUtils.toBoolean(resultSet.getString(columnName));
    }

    @Override
    public Boolean getResult(ResultSet resultSet, int columnIndex) throws SQLException {
        return BooleanUtils.toBoolean(resultSet.getString(columnIndex));
    }

    @Override
    public Boolean getResult(CallableStatement callableStatement, int columnIndex) throws SQLException {
        return BooleanUtils.toBoolean(callableStatement.getString(columnIndex));
    }
}
