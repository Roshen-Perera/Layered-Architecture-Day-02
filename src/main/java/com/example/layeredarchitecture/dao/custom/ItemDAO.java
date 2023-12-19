package com.example.layeredarchitecture.dao.custom;

import com.example.layeredarchitecture.dao.CrudDAO;
import com.example.layeredarchitecture.model.ItemDTO;

import java.sql.*;
import java.util.ArrayList;

public interface ItemDAO extends CrudDAO<ItemDTO> {
    ArrayList<ItemDTO> getAll()throws SQLException, ClassNotFoundException;
    boolean save(ItemDTO dto)throws SQLException, ClassNotFoundException;
    public boolean update(ItemDTO dto)throws SQLException, ClassNotFoundException;
    public boolean delete(String code)throws SQLException, ClassNotFoundException;
    public String generateId()throws SQLException, ClassNotFoundException;
}
