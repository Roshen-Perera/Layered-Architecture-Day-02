package com.example.layeredarchitecture.dao.custom;

import com.example.layeredarchitecture.dao.SQLUtil;
import com.example.layeredarchitecture.db.DBConnection;
import com.example.layeredarchitecture.model.CustomerDTO;
import com.example.layeredarchitecture.model.ItemDTO;
import com.example.layeredarchitecture.model.OrderDetailDTO;

import java.sql.*;
import java.time.LocalDate;
import java.util.List;

public interface OrderDetailDAO{
    public boolean saveDetails(String orderId, List<OrderDetailDTO> orderDetails) throws SQLException, ClassNotFoundException;

    public boolean saveOrderDetails(String oid, OrderDetailDTO dto) throws SQLException, ClassNotFoundException;
}
