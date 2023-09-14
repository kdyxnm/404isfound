
package com.unimelb.swen90007.group404notfound.api.mapper;

import com.unimelb.swen90007.group404notfound.api.domain.Portfolio;
import com.unimelb.swen90007.group404notfound.api.mapper.dbconnection.PostgresConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PortfolioMapper {
    public Portfolio findPortById(Long id){
        String sql = "SELECT * FROM public.portfolio WHERE id = ?";
        Portfolio portfolio = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        connection = PostgresConnection.getConnection();
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                portfolio = new Portfolio(
                        resultSet.getLong("id"),
                        resultSet.getLong("userid"),
                        resultSet.getLong("listingid"),
                        resultSet.getLong("shareid"),
                        resultSet.getLong("companyid")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            PostgresConnection.closeConnection(connection, preparedStatement);
        }
    return portfolio;
    }

    public boolean addPortfolio(Portfolio portfolio){
        String sql = "INSERT INTO portfolio (userid, listingid, shareid, companyid) VALUES (?,?,?,?)";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        connection = PostgresConnection.getConnection();
        Boolean res;
        try {
            preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, portfolio.getUserId());
            preparedStatement.setLong(2, portfolio.getListingId());
            preparedStatement.setLong(3, portfolio.getShareId());
            preparedStatement.setLong(4, portfolio.getCompanyId());
            res = preparedStatement.executeUpdate()==1;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            PostgresConnection.closeConnection(connection,preparedStatement);
        }
        return res;
    }

    public List<Portfolio> findAllPortfolioIdByUserId(Long userId){
        String sql = "SELECT * FROM public.portfolio " +
                "WHERE userid = ?";
        List<Portfolio> portfolios = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = PostgresConnection.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Portfolio portfolio = new Portfolio(
                        resultSet.getLong("id"),
                        resultSet.getLong("userid"),
                        resultSet.getLong("listingid"),
                        resultSet.getLong("shareid"),
                        resultSet.getLong("companyid")
                );
                portfolios.add(portfolio);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            PostgresConnection.closeConnection(connection, preparedStatement);
        }
        return portfolios;
    }


    public Long findExistByuidAndsid(Long uid, Long sid){
        String sql = "SELECT * FROM portfolio WHERE shareid = ? AND userid = ?";
        Portfolio portfolio = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        connection = PostgresConnection.getConnection();
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, sid);
            preparedStatement.setLong(2, uid);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                portfolio = new Portfolio(
                        resultSet.getLong("id"),
                        resultSet.getLong("userid"),
                        resultSet.getLong("listingid"),
                        resultSet.getLong("shareid"),
                        resultSet.getLong("companyid")
                );
            }else{
                return -1L;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            PostgresConnection.closeConnection(connection, preparedStatement);
        }
        return portfolio.getListingId();
    }

    public boolean deletePortfolio(Portfolio portfolio){
        String sql = "DELETE FROM public.portfolio WHERE id = ?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        connection = PostgresConnection.getConnection();
        Boolean result = false;
        Long pid = portfolio.getPortfolioId();
        try {
            connection = PostgresConnection.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, pid);
            result = preparedStatement.executeUpdate()==1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            PostgresConnection.closeConnection(connection, preparedStatement);
        }
        return result;
    }
}

