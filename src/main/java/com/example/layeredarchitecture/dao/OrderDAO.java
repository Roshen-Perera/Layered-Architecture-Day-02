package com.example.layeredarchitecture.dao;

import com.example.layeredarchitecture.model.ItemDTO;

import java.sql.*;

public interface OrderDAO {
    String generateID() throws SQLException, ClassNotFoundException;

    boolean existItem(String code) throws SQLException, ClassNotFoundException;

    boolean existCustomer(String id) throws SQLException, ClassNotFoundException;

    ItemDTO findItem(String code) throws SQLException, ClassNotFoundException;
}
