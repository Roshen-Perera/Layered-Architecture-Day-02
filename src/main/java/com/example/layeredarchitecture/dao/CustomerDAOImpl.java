package com.example.layeredarchitecture.dao;

import com.example.layeredarchitecture.db.DBConnection;
import com.example.layeredarchitecture.model.CustomerDTO;

import java.sql.*;
import java.util.ArrayList;
public class CustomerDAOImpl {
    public ArrayList<CustomerDTO> getAllCustomer() throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getDbConnection().getConnection();
        Statement statement = connection.createStatement();
        ResultSet rst = statement.executeQuery("select * from customer");
        ArrayList<CustomerDTO> customerList = new ArrayList<>();
        while (rst.next()){
            CustomerDTO customerDTO = new CustomerDTO(rst.getString("id"), rst.getString("name"), rst.getString("address"));
            rst.getString(String.valueOf(customerDTO));
            getAllCustomer();
        }
        return getAllCustomer();
    }

    public void btnSaveOnAction(CustomerDTO dto) throws SQLException, ClassNotFoundException {
        CustomerDTO customerDTO = new CustomerDTO();

        Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement("INSERT INTO Customer (id,name, address) VALUES (?,?,?)");

        pstm.setString(1, dto.getId());
        pstm.setString(2, dto.getName());
        pstm.setString(3, dto.getAddress());

        pstm.executeUpdate();

    }

    public void btnUpdateOnAction(CustomerDTO dto) throws SQLException, ClassNotFoundException {

        Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement("UPDATE Customer SET name=?, address=? WHERE id=?");

        pstm.setString(1, dto.getId());
        pstm.setString(2, dto.getName());
        pstm.setString(3, dto.getAddress());

        pstm.executeUpdate();

    }
}
