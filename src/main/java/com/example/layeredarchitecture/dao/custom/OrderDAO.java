package com.example.layeredarchitecture.dao.custom;

import com.example.layeredarchitecture.model.CustomerDTO;
import com.example.layeredarchitecture.model.ItemDTO;
import com.example.layeredarchitecture.model.OrderDetailDTO;

import java.sql.*;
import java.time.LocalDate;
import java.util.List;

public interface OrderDAO {
    String generateID() throws SQLException, ClassNotFoundException;
    boolean existItem(String code) throws SQLException, ClassNotFoundException;
    boolean existCustomer(String id) throws SQLException, ClassNotFoundException;

    ItemDTO findItem(String code) throws SQLException, ClassNotFoundException;

    CustomerDTO getCustomer(String id) throws SQLException;


    boolean saveOrder(String orderId, LocalDate orderDate, String customerId, List<OrderDetailDTO> orderDetails) throws SQLException;

    boolean existOrder(String orderId) throws SQLException;
}
