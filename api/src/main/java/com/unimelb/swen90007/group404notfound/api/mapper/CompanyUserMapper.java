package com.unimelb.swen90007.group404notfound.api.mapper;

import com.unimelb.swen90007.group404notfound.api.domain.CompanyUser;
import com.unimelb.swen90007.group404notfound.api.domain.Customer;
import com.unimelb.swen90007.group404notfound.api.mapper.dbconnection.PostgresConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CompanyUserMapper {
    public boolean addCompanyUser(CompanyUser companyUser) {
        boolean result;
        String sqlUser = "INSERT INTO public.companyuser (username, password, lastname, firstname, companyid)" +
                "VALUES (?, ?, ?, ?, ?)";
        Connection connection = null;
        PreparedStatement preparedUserStatement = null;
        try {
            connection = PostgresConnection.getConnection();
            preparedUserStatement = connection.prepareStatement(sqlUser, Statement.RETURN_GENERATED_KEYS);
            preparedUserStatement.setString(1, companyUser.getUsername());
            preparedUserStatement.setString(2, companyUser.getPassword());
            preparedUserStatement.setString(3, companyUser.getLastname());
            preparedUserStatement.setString(4, companyUser.getFirstname());
            preparedUserStatement.setLong(5, companyUser.getCompanyId());
            result = preparedUserStatement.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            PostgresConnection.closeConnection(connection, preparedUserStatement);
        }

        return result;
    }

    public List<CompanyUser> getAllCompanyUser() {
        String sql = "SELECT * FROM public.companyuser";
        List<CompanyUser> users = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = PostgresConnection.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            ResultSet results = preparedStatement.executeQuery();
            while(results.next()){
                CompanyUser companyUser = new CompanyUser(
                        results.getLong("id"),
                        results.getString("username"),
                        results.getString("password"),
                        results.getString("lastname"),
                        results.getString("firstname"),
                        results.getBigDecimal("balance"),
                        results.getLong("companyid")
                );
                if(companyUser!=null){
                    users.add(companyUser);
                }
            }
        }catch (SQLException e) {
           throw new RuntimeException(e);
        } finally {
           PostgresConnection.closeConnection(connection, preparedStatement);
        }
        return users;

    }

    public CompanyUser findCompanyUserById(Long id){
        String sql = "SELECT * FROM public.companyuser WHERE id = ?";
        CompanyUser companyUser = null;
        Connection connection = null;
        PreparedStatement preparedUserStatement = null;
        try {
            connection = PostgresConnection.getConnection();
            preparedUserStatement = connection.prepareStatement(sql);
            preparedUserStatement.setLong(1, id);
            ResultSet results = preparedUserStatement.executeQuery();
            companyUser = assignUserValue(results);
            results.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            PostgresConnection.closeConnection(connection, preparedUserStatement);
        }
        return companyUser;
    }

    public CompanyUser findCompanyUserByUsername(String username){
        String sql = "SELECT id, password,balance,companyid FROM public.companyuser WHERE username = ?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        CompanyUser companyUser = null;
        try {
            connection = PostgresConnection.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                companyUser = new CompanyUser(
                        resultSet.getLong("id"),
                        null,
                        resultSet.getString("password"),
                        null,
                        null,
                        resultSet.getBigDecimal("balance"),
                        resultSet.getLong("companyid"));
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            PostgresConnection.closeConnection(connection, preparedStatement);
        }
        return companyUser;
    }

    public boolean setCompanyUser(CompanyUser companyUser){
        String sql = "UPDATE public.companyuser SET username=?, password=?, lastname=?, firstname=?, balance=?, companyid=? WHERE id=?";
        Connection connection = null;
        connection = PostgresConnection.getConnection();
        PreparedStatement preparedStatement = null;
        boolean result = false;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, companyUser.getUsername());
            preparedStatement.setString(2, companyUser.getPassword());
            preparedStatement.setString(3, companyUser.getLastname());
            preparedStatement.setString(4, companyUser.getFirstname());
            preparedStatement.setBigDecimal(5, companyUser.getBalance());
            preparedStatement.setLong(6, companyUser.getCompanyId());
            preparedStatement.setLong(7, companyUser.getUserId());
            result = preparedStatement.executeUpdate()==1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            PostgresConnection.closeConnection(connection, preparedStatement);
        }
        return result;
    }

    private CompanyUser assignUserValue(ResultSet results){
        CompanyUser companyUser = null;
        try {
            if (results.next()) {
                companyUser = new CompanyUser(
                                results.getLong("id"),
                                results.getString("username"),
                                results.getString("password"),
                                results.getString("lastname"),
                                results.getString("firstname"),
                                results.getBigDecimal("balance"),
                                results.getLong("companyid")
                        );
                results.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return companyUser;
    }

}
