package com.example.layeredarchitecture.dao.custom;

import com.example.layeredarchitecture.dao.CrudDAO;
import com.example.layeredarchitecture.dao.SuperDAO;
import com.example.layeredarchitecture.model.OrderListDTO;

import java.sql.SQLException;

public interface SQLQueryDAO extends SuperDAO{
    OrderListDTO orderList() throws SQLException, ClassNotFoundException;
}
