package com.unimelb.swen90007.group404notfound.api.mapper;

import com.unimelb.swen90007.group404notfound.api.domain.Admin;
import com.unimelb.swen90007.group404notfound.api.domain.CompanyUser;
import com.unimelb.swen90007.group404notfound.api.mapper.dbconnection.PostgresConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminMapper {
    public Admin findAdminByUsername(String username){
        String sql = "SELECT id, password FROM public.admin WHERE username = ?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Admin admin = null;
        try {
            connection = PostgresConnection.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                admin =  new Admin(resultSet.getLong("id"),
                        username,
                        resultSet.getString("password"),
                        null,
                        null
                        );
            }

            resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            PostgresConnection.closeConnection(connection, preparedStatement);
        }
        return admin;
    }
    public Admin findAdminById(Long userId){
        String sql = "SELECT * FROM public.admin WHERE id = ?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Admin admin = null;
        try {
            connection = PostgresConnection.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                admin =  new Admin(userId,
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getString("lastname"),
                        resultSet.getString("firstname")
                );
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            PostgresConnection.closeConnection(connection, preparedStatement);
        }
        return admin;
    }
}
