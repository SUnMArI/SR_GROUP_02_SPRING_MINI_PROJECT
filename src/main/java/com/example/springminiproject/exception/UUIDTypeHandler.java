package com.example.springminiproject.exception;

import org.apache.ibatis.type.BaseTypeHandler;

import java.util.UUID;


import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UUIDTypeHandler extends BaseTypeHandler<UUID> {
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, UUID parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.toString());
    }

    @Override
    public UUID getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String uuidStr = rs.getString(columnName);
        return uuidStr != null ? UUID.fromString(uuidStr) : null;
    }

    @Override
    public UUID getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String uuidStr = rs.getString(columnIndex);
        return uuidStr != null ? UUID.fromString(uuidStr) : null;
    }

    @Override
    public UUID getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String uuidStr = cs.getString(columnIndex);
        return uuidStr != null ? UUID.fromString(uuidStr) : null;
    }
}
