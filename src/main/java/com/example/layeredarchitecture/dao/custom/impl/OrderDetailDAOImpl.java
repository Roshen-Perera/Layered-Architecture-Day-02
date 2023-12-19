package com.example.layeredarchitecture.dao.custom.impl;

import com.example.layeredarchitecture.dao.SQLUtil;
import com.example.layeredarchitecture.dao.custom.OrderDetailDAO;
import com.example.layeredarchitecture.model.OrderDetailDTO;

import java.sql.SQLException;
import java.util.List;

public class OrderDetailDAOImpl implements OrderDetailDAO {

    public boolean saveDetails(String orderId, List<OrderDetailDTO> orderDetails) throws SQLException, ClassNotFoundException {
        for (OrderDetailDTO dto : orderDetails) {
            if (!saveOrderDetails(orderId,dto)) {
                return false;
            }
        }
        return true;
    }

    public boolean saveOrderDetails(String oid, OrderDetailDTO dto) throws SQLException, ClassNotFoundException {
        /*Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement stm = connection.prepareStatement("INSERT INTO OrderDetails (oid, itemCode, unitPrice, qty) VALUES (?,?,?,?)");
        stm.setString(1, oid);
        stm.setString(2, dto.getItemCode());
        stm.setBigDecimal(3, dto.getUnitPrice());
        stm.setInt(4, dto.getQty());
        return stm.executeUpdate()>0;*/
        return SQLUtil.execute("INSERT INTO OrderDetails (oid, itemCode, unitPrice, qty) VALUES (?,?,?,?)", oid, dto.getItemCode(), dto.getItemCode(), dto.getUnitPrice(), dto.getQty());
    }
}
