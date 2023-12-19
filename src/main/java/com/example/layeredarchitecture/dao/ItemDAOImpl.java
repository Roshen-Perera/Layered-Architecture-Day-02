package com.example.layeredarchitecture.dao;

import com.example.layeredarchitecture.db.DBConnection;
import com.example.layeredarchitecture.model.ItemDTO;
import com.example.layeredarchitecture.model.OrderDetailDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemDAOImpl implements ItemDAO{
    Connection connection;

    {
        try {
            connection = DBConnection.getDbConnection().getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ArrayList<ItemDTO> getAllItem() throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getDbConnection().getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM item");
        ArrayList<ItemDTO> itemList = new ArrayList<>();
        while (resultSet.next()) {
            ItemDTO itemDTO = new ItemDTO(
                    resultSet.getString("code"),
                    resultSet.getString("description"),
                    resultSet.getBigDecimal("unitPrice"),
                    resultSet.getInt("qtyOnHand"));
            itemList.add(itemDTO);
        }
        return itemList;
    }

    public ItemDTO findItem(String code) throws SQLException, ClassNotFoundException {
        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM Item WHERE code=?");
        pstm.setString(1, code);
        ResultSet rst = pstm.executeQuery();
        rst.next();
        return new ItemDTO(code, rst.getString("description"), rst.getBigDecimal("unitPrice"), rst.getInt("qtyOnHand"));
    }
    @Override
    public boolean btnItemSave(ItemDTO dto) throws SQLException, ClassNotFoundException {
        PreparedStatement pstm = connection.prepareStatement("INSERT INTO Item (code, description, unitPrice, qtyOnHand) VALUES (?,?,?,?)");

        pstm.setString(1, dto.getCode());
        pstm.setString(2, dto.getDescription());
        pstm.setBigDecimal(3, dto.getUnitPrice());
        pstm.setInt(4,dto.getQtyOnHand());

        return pstm.executeUpdate() > 0;
    }

    @Override
    public boolean btnItemUpdate(ItemDTO dto) throws SQLException, ClassNotFoundException {
        PreparedStatement pstm = connection.prepareStatement("UPDATE Item SET description=?, unitPrice=?, qtyOnHand=? WHERE code=?");

        pstm.setString(1, dto.getCode());
        pstm.setString(2, dto.getDescription());
        pstm.setBigDecimal(3, dto.getUnitPrice());
        pstm.setInt(4,dto.getQtyOnHand());

        return pstm.executeUpdate() > 0;
    }

    @Override
    public void btnItemDelete(String code) throws SQLException, ClassNotFoundException {
        PreparedStatement pstm = connection.prepareStatement("DELETE FROM Item WHERE code=?");
        pstm.setString(1, code);
        pstm.executeUpdate();
    }

    @Override
    public String generateId() throws SQLException, ClassNotFoundException {
        ResultSet rst = connection.createStatement().executeQuery("SELECT code FROM Item ORDER BY code DESC LIMIT 1;");
        if (rst.next()) {
            String id = rst.getString("code");
            int newItemId = Integer.parseInt(id.replace("I00-", "")) + 1;
            return String.format("I00-%03d", newItemId);
        } else {
            return "I00-001";
        }
    }
    public boolean updateItem(List<OrderDetailDTO> orderDetails) throws SQLException, ClassNotFoundException {
        for (OrderDetailDTO dto : orderDetails) {
            if (!updateItems(dto)) {
                return false;
            }
        }
        return true;
    }

    public boolean updateItems(OrderDetailDTO dto) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getDbConnection().getConnection();

        ItemDTO item = findItem(dto.getItemCode());
        item.setQtyOnHand(item.getQtyOnHand() - dto.getQty());

        PreparedStatement pstm = connection.prepareStatement("UPDATE Item SET description=?, unitPrice=?, qtyOnHand=? WHERE code=?");

        pstm.setString(1, item.getDescription());
        pstm.setBigDecimal(2, item.getUnitPrice());
        pstm.setInt(3, item.getQtyOnHand());
        pstm.setString(4, item.getCode());

        return pstm.executeUpdate() > 0;
    }


}
