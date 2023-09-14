package com.unimelb.swen90007.group404notfound.api.mapper;

import com.unimelb.swen90007.group404notfound.api.domain.Company;
import com.unimelb.swen90007.group404notfound.api.domain.Share;
import com.unimelb.swen90007.group404notfound.api.mapper.dbconnection.PostgresConnection;

import java.sql.*;

public class ShareMapper {
    public Share findShareById(Long id){
        String sql = "SELECT * FROM public.share WHERE id = ?";
        Share share = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try{
            connection = PostgresConnection.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            share = assignShareValue(rs);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            PostgresConnection.closeConnection(connection,preparedStatement);
        }
        return share;
    }

    private Share assignShareValue(ResultSet rs){
        Share share = null;
        try {
            if(rs.next()){
                share = new Share(rs.getLong("id"),
                                    rs.getLong("companyid"),
                                    rs.getString("sharetype").charAt(0),
                                    rs.getBigDecimal("price"));
            }
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return share;
    }


    public boolean addShare(Share share){
        boolean result = false;
        String sql = "INSERT INTO public.share (companyid, sharetype, price) VALUES (?,?,?)";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        connection = PostgresConnection.getConnection();
        try {
            preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, share.getCompanyId());
            preparedStatement.setString(2, share.getShareType().toString());
            preparedStatement.setBigDecimal(3, share.getPrice());

            result = preparedStatement.executeUpdate()==1;
            if(result) {
                ResultSet rs = preparedStatement.getGeneratedKeys();
                if (rs.next()) {
                    Long shareId = rs.getLong(1);
                    share.setId(shareId);
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

    public boolean setShare(Share share){
        String sql = "UPDATE public.share SET companyid=?, sharetype=?, price=? WHERE id=?";
        Connection connection = null;
        connection = PostgresConnection.getConnection();
        PreparedStatement preparedStatement = null;
        boolean result = false;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, share.getCompanyId());
            preparedStatement.setString(2, String.valueOf(share.getShareType()));
            preparedStatement.setBigDecimal(3, share.getPrice());
            preparedStatement.setLong(4, share.getId());
            result = preparedStatement.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            PostgresConnection.closeConnection(connection, preparedStatement);
        }
        return result;
    }
}
