package com.unimelb.swen90007.group404notfound.api.mapper;


import com.unimelb.swen90007.group404notfound.api.domain.Customer;

import com.unimelb.swen90007.group404notfound.api.mapper.dbconnection.PostgresConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerMapper {
    public boolean addCustomer(Customer customer) {
        boolean result;
        String sqlUser = "INSERT INTO public.customer (username, password, lastname, firstname)" +
                "VALUES (?, ?, ?, ?)";
        Connection connection = null;
        PreparedStatement preparedUserStatement = null;
        try {
            connection = PostgresConnection.getConnection();
            preparedUserStatement = connection.prepareStatement(sqlUser, Statement.RETURN_GENERATED_KEYS);
            preparedUserStatement.setString(1, customer.getUsername());
            preparedUserStatement.setString(2, customer.getPassword());
            preparedUserStatement.setString(3, customer.getLastname());
            preparedUserStatement.setString(4, customer.getFirstname());

            result = preparedUserStatement.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            PostgresConnection.closeConnection(connection, preparedUserStatement);
        }

        return result;
    }

    public Customer findCustomerById(Long id){
        String sql = "SELECT * FROM public.customer WHERE id = ?";
        Customer customer = null;
        Connection connection = null;
        PreparedStatement preparedUserStatement = null;
        try {
            connection = PostgresConnection.getConnection();
            preparedUserStatement = connection.prepareStatement(sql);
            preparedUserStatement.setLong(1, id);
            ResultSet results = preparedUserStatement.executeQuery();
            customer = assignUserValue(results);
            results.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            PostgresConnection.closeConnection(connection, preparedUserStatement);
        }
        return customer;
    }

    public Customer findCustomerByUsername(String username){
        String sql = "SELECT id, password, balance FROM public.customer WHERE username = ?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Customer customer = null;
        try {
            connection = PostgresConnection.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                customer = new Customer(resultSet.getLong("id"),
                        null,
                        resultSet.getString("password"),
                        null,
                        null,
                        resultSet.getBigDecimal("balance"));
            }

            resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            PostgresConnection.closeConnection(connection, preparedStatement);
        }
        return customer;
    }

    public boolean setCustomer(Customer customer){
        String sql = "UPDATE public.customer SET username=?, password=?, lastname=?, firstname=?, balance=? WHERE id=?";
        Connection connection = null;
        connection = PostgresConnection.getConnection();
        PreparedStatement preparedStatement = null;
        boolean result = false;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, customer.getUsername());
            preparedStatement.setString(2, customer.getPassword());
            preparedStatement.setString(3, customer.getLastname());
            preparedStatement.setString(4, customer.getFirstname());
            preparedStatement.setBigDecimal(5, customer.getBalance());
            preparedStatement.setLong(6, customer.getUserId());
            result = preparedStatement.executeUpdate()==1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            PostgresConnection.closeConnection(connection, preparedStatement);
        }
        return result;
    }

    public List<Customer> getAllCustomer() {
        String sql = "SELECT * FROM public.customer";
        List<Customer> users = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = PostgresConnection.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            ResultSet results = preparedStatement.executeQuery();
            while(results.next()){
                Customer customer = new Customer(
                        results.getLong("id"),
                        results.getString("username"),
                        results.getString("password"),
                        results.getString("lastname"),
                        results.getString("firstname"),
                        results.getBigDecimal("balance")
                );
                if(customer!=null){
                    users.add(customer);
                }
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            PostgresConnection.closeConnection(connection, preparedStatement);
        }
        return users;

    }

    private Customer assignUserValue(ResultSet results){
        Customer customer = null;
        try {
            if (results.next()) {
                customer = new Customer(
                        results.getLong("id"),
                        results.getString("username"),
                        results.getString("password"),
                        results.getString("lastname"),
                        results.getString("firstname"),
                        results.getBigDecimal("balance")
                );
                results.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return customer;
    }

}
