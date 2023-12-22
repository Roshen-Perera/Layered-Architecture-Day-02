package com.example.layeredarchitecture.dao.custom;

import com.example.layeredarchitecture.dao.CrudDAO;
import com.example.layeredarchitecture.dao.SQLUtil;
import com.example.layeredarchitecture.model.CustomerDTO;
import com.example.layeredarchitecture.entity.Item;
import com.example.layeredarchitecture.model.OrderDetailDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public interface ItemDAO extends CrudDAO<Item> {
    ArrayList<Item> getAll()throws SQLException, ClassNotFoundException;
    boolean save(Item dto)throws SQLException, ClassNotFoundException;
    public boolean update(Item dto)throws SQLException, ClassNotFoundException;
    public boolean delete(String code)throws SQLException, ClassNotFoundException;
    public String generateId()throws SQLException, ClassNotFoundException;

    Item getItem(String id) throws SQLException, ClassNotFoundException;

    boolean updateItem(List<OrderDetailDTO> orderDetails) throws SQLException, ClassNotFoundException;

    boolean updateItems(OrderDetailDTO dto) throws SQLException, ClassNotFoundException;
}
