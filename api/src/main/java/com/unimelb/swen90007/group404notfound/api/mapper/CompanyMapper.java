package com.unimelb.swen90007.group404notfound.api.mapper;

import com.unimelb.swen90007.group404notfound.api.domain.Company;
import com.unimelb.swen90007.group404notfound.api.mapper.dbconnection.PostgresConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class CompanyMapper {

    public boolean addCompany(Company company){
        boolean result;
        String sql = "INSERT INTO public.company(companyname, category)"+
                "VALUES (?, ?)";
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = PostgresConnection.getConnection();
            preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, company.getCompanyName());
            preparedStatement.setString(2, company.getCategory());
            result = preparedStatement.executeUpdate()==1;
            if(result) {
                ResultSet rs = preparedStatement.getGeneratedKeys();
                if (rs.next()) {
                    Long companyId = rs.getLong(1);
                    company.setCompanyId(companyId);
                }
                rs.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            PostgresConnection.closeConnection(connection, preparedStatement);
        }
        return result;
    }

    public Company findCompanyById(Long id){
        Company company = null;
        String sql = "SELECT * FROM public.company WHERE id = ?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = PostgresConnection.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                company = new Company(
                        resultSet.getLong("id"),
                        resultSet.getString("companyname"),
                        resultSet.getString("category"),
                        resultSet.getBigDecimal("balance")
                );
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            PostgresConnection.closeConnection(connection,preparedStatement);
        }
        return company;
    }

    public boolean setCompany(Company company){
        String sql = "UPDATE public.company SET companyname=?, category=?, balance=? WHERE id=?";
        Connection connection = null;
        connection = PostgresConnection.getConnection();
        PreparedStatement preparedStatement = null;
        boolean result = false;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, company.getCompanyName());
            preparedStatement.setString(2, company.getCategory());
            preparedStatement.setBigDecimal(3, company.getBalance());
            preparedStatement.setLong(4, company.getCompanyId());
            result = preparedStatement.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            PostgresConnection.closeConnection(connection, preparedStatement);
        }
        return result;
    }

    public Company findCompanyByName(String username){
        Company company = null;
        String sql = "SELECT id FROM public.company WHERE companyname = ?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = PostgresConnection.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                company = new Company(
                        resultSet.getLong("id"),
                        null,
                        null,
                        null
                        );

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            PostgresConnection.closeConnection(connection,preparedStatement);
        }
        return company;
    }

    public List<Company> findAllCompany(){
        List<Company> companies = new ArrayList<>();
        String sql = "SELECT id, companyname FROM public.company";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = PostgresConnection.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Company company = new Company(
                        resultSet.getLong("id"),
                        resultSet.getString("companyname"),
                        null,
                        null
                );
                companies.add(company);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            PostgresConnection.closeConnection(connection,preparedStatement);
        }
        return companies;
    }
}
