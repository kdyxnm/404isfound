package com.unimelb.swen90007.group404notfound.api.mapper;

import com.unimelb.swen90007.group404notfound.api.domain.CompanyListing;
import com.unimelb.swen90007.group404notfound.api.domain.InvestListing;
import com.unimelb.swen90007.group404notfound.api.domain.Listing;
import com.unimelb.swen90007.group404notfound.api.dto.ListingDetailDTO;
import com.unimelb.swen90007.group404notfound.api.mapper.dbconnection.PostgresConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InvestListingMapper {

    public Listing findListingById(Long id) {
        String sql = "SELECT * FROM public.investlisting WHERE id = ?";
        InvestListing listing = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        connection = PostgresConnection.getConnection();
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            listing = assignListingValue(resultSet);
            resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            PostgresConnection.closeConnection(connection, preparedStatement);
        }
        return listing;

    }

    public boolean addInvestListing(InvestListing listing){
        String sql = "INSERT INTO investlisting (shareid, numshare) VALUES (?,?)";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        connection = PostgresConnection.getConnection();
        boolean back;
        try {
            preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, listing.getShareId());
            preparedStatement.setInt(2, listing.getNumShare());
            back = preparedStatement.executeUpdate()==1;
            if(back ){
                ResultSet rs = preparedStatement.getGeneratedKeys();
                if(rs.next()){
                    Long listingid = rs.getLong(1);
                    listing.setListingId(listingid);
                }
                rs.close();

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            PostgresConnection.closeConnection(connection,preparedStatement);
        }
        return back;
    }

    public boolean setInvestListing(InvestListing listing){
        String sql = "UPDATE public.investlisting SET shareid=?, numshare=? WHERE id=?";
        Connection connection = null;
        connection = PostgresConnection.getConnection();
        PreparedStatement preparedStatement = null;
        Boolean result = false;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, listing.getShareId());
            preparedStatement.setInt(2, listing.getNumShare());
            preparedStatement.setLong(3, listing.getListingId());
            result = preparedStatement.executeUpdate()==1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            PostgresConnection.closeConnection(connection, preparedStatement);
        }
        return result;
    }
    private InvestListing assignListingValue(ResultSet resultSet) {
        InvestListing listing = null;
        try {
            if (resultSet.next()) {
                listing = new InvestListing(resultSet.getLong("id"),
                                resultSet.getLong("shareid"),
                                resultSet.getInt("numshare"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listing;
    }

    public ListingDetailDTO getListingByCompanyNameAndType(String companyName, Character shareType) {
        System.out.println(companyName);
        System.out.println(shareType);
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ListingDetailDTO listingDetail = null;
        String sql =
                "SELECT l.*, s.price, c.companyname FROM public.investlisting l" +
                        " JOIN public.share s ON l.shareid = s.id " +
                        "JOIN public.company c ON s.companyid = c.id WHERE c.companyname = ? " +
                        "AND s.sharetype = ?";
        try {
            connection = PostgresConnection.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, companyName);
            preparedStatement.setString(2, shareType.toString());
            ResultSet rs = preparedStatement.executeQuery();

            if(rs.next()) {
                listingDetail = new ListingDetailDTO();
                listingDetail.setId(rs.getLong("id"));
                listingDetail.setShareId(rs.getLong("shareid"));
                listingDetail.setNumShare(rs.getInt("numshare"));
                listingDetail.setPrice(rs.getBigDecimal("price"));
                listingDetail.setShareType(shareType);
                listingDetail.setCompanyName(companyName);
            }
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {

            PostgresConnection.closeConnection(connection, preparedStatement);
        }
        return listingDetail;
    }


    public List<InvestListing> findAllInvestListingByUserId(Long userId){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        List<InvestListing> listingDetails = new ArrayList<>();
        String sql =
                "SELECT l.* ,p.userid FROM public.investlisting l" +
                        " JOIN public.portfolio p ON l.id = p.listingid " +
                        "WHERE p.userid = ?";
        try {
            connection = PostgresConnection.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, userId);
            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next()) {
                InvestListing listingDetail = assignListingValue(rs);

                listingDetails.add(listingDetail);
            }
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {

            PostgresConnection.closeConnection(connection, preparedStatement);
        }
        return listingDetails;
    }

    public boolean deleteInvestListing(InvestListing listing){
        String sql = "DELETE FROM public.investlisting WHERE id = ?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Boolean result = false;
        Long listingId = listing.getListingId();
        try {
            connection = PostgresConnection.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, listingId);
            result = preparedStatement.executeUpdate()==1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            PostgresConnection.closeConnection(connection, preparedStatement);
        }
        return result;
    }
}
