package com.example.layeredarchitecture.dao;

import com.example.layeredarchitecture.model.CustomerDTO;

import java.sql.*;
import java.util.ArrayList;

public interface CustomerDAO {
    ArrayList<CustomerDTO> getAllCustomer() throws SQLException, ClassNotFoundException;

    boolean SaveOnAction(CustomerDTO dto) throws SQLException, ClassNotFoundException;

    boolean UpdateOnAction(CustomerDTO dto) throws SQLException, ClassNotFoundException;

    boolean DeleteOnAction(String id) throws SQLException, ClassNotFoundException;
    boolean existCustomer(String id) throws SQLException, ClassNotFoundException;
    String generateId() throws SQLException, ClassNotFoundException;
}
