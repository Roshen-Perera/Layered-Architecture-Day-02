package com.example.layeredarchitecture.dao;

import com.example.layeredarchitecture.model.ItemDTO;

import java.sql.*;
import java.util.ArrayList;

public interface ItemDAO {
    ArrayList<ItemDTO> getAllItem()throws SQLException, ClassNotFoundException;
    boolean btnItemSave(ItemDTO dto)throws SQLException, ClassNotFoundException;
    public boolean btnItemUpdate(ItemDTO dto)throws SQLException, ClassNotFoundException;
    public void btnItemDelete(String code)throws SQLException, ClassNotFoundException;
    public String generateId()throws SQLException, ClassNotFoundException;
}
