package com.example.layeredarchitecture.dao;

import com.example.layeredarchitecture.db.DBConnection;
import com.example.layeredarchitecture.model.CustomerDTO;
import com.example.layeredarchitecture.model.ItemDTO;

import java.sql.*;

public class OrderDAOImpl implements OrderDAO {
    Connection connection;

    {
        try {
            connection = DBConnection.getDbConnection().getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public String generateID() throws SQLException, ClassNotFoundException {
        Statement pstm = connection.createStatement();
        ResultSet rst = pstm.executeQuery("SELECT oid FROM `Orders` ORDER BY oid DESC LIMIT 1;");

        return rst.next() ? String.format("OID-%03d", (Integer.parseInt(rst.getString("oid").replace("OID-", "")) + 1)) : "OID-001";
    }
    public boolean existItem(String code) throws SQLException, ClassNotFoundException {
        PreparedStatement pstm = connection.prepareStatement("SELECT code FROM Item WHERE code=?");
        pstm.setString(1, code);
        return pstm.executeQuery().next();
    }

    public boolean existCustomer(String id) throws SQLException, ClassNotFoundException {
        PreparedStatement pstm = connection.prepareStatement("SELECT id FROM Customer WHERE id=?");
        pstm.setString(1, id);
        return pstm.executeQuery().next();
    }

    public ItemDTO findItem(String code) throws SQLException, ClassNotFoundException {
        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM Item WHERE code=?");
        pstm.setString(1, code);
        ResultSet rst = pstm.executeQuery();
        rst.next();
        return new ItemDTO(code, rst.getString("description"), rst.getBigDecimal("unitPrice"), rst.getInt("qtyOnHand"));
    }

    public CustomerDTO getCustomer(String id) throws SQLException {
        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM Customer WHERE id=?");
        pstm.setString(1, id + "");
        ResultSet rst = pstm.executeQuery();
        rst.next();
        return new CustomerDTO(id + "id", rst.getString("name"), rst.getString("address"));
    }
/*
    public boolean saveOrder(OrderDTO dto) throws SQLException {
        PreparedStatement pstm = connection.prepareStatement("INSERT INTO `Orders` (oid, date, customerID) VALUES (?,?,?)");
        pstm.setString(1, dto.getOrderId());
        pstm.setString(2, String.valueOf(Date.valueOf(dto.getOrderDate())));
        pstm.setString(3, String.valueOf(dto.getOrderDate()));
        return pstm.executeUpdate()>0;
    }

    public boolean existOrder(String orderId) throws SQLException {
        PreparedStatement pstm = connection.prepareStatement("SELECT oid FROM `Orders` WHERE oid=?");
        pstm.setString(1, orderId);
        return pstm.executeQuery().next();
    }*/
}
