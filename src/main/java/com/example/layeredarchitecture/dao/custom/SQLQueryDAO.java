package com.example.layeredarchitecture.dao.custom;

import com.example.layeredarchitecture.model.OrderListDTO;

import java.sql.SQLException;

public interface SQLQueryDAO {
    OrderListDTO orderList() throws SQLException, ClassNotFoundException;
}
