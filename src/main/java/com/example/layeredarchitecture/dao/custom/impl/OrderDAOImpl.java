package com.example.layeredarchitecture.dao.custom.impl;

import com.example.layeredarchitecture.bo.custom.impl.PlaceOrderBOImpl;
import com.example.layeredarchitecture.dao.SQLUtil;
import com.example.layeredarchitecture.dao.custom.OrderDAO;
import com.example.layeredarchitecture.model.OrderDTO;
import com.example.layeredarchitecture.model.OrderDetailDTO;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {

    OrderDetailDAOImpl orderDetailDAO = new OrderDetailDAOImpl();

    ItemDAOImpl itemDAO = new ItemDAOImpl();

    public String generateID() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT oid FROM `Orders` ORDER BY oid DESC LIMIT 1;");
        return rst.next() ? String.format("OID-%03d", (Integer.parseInt(rst.getString("oid").replace("OID-", "")) + 1)) : "OID-001";
    }




    public boolean saveOrder(String orderId, LocalDate orderDate, String customerId, List<OrderDetailDTO> orderDetails) throws SQLException {
        /*Transaction*/
        PlaceOrderBOImpl placeOrderBO = new PlaceOrderBOImpl();
        return placeOrderBO.placeOrder(orderId, orderDate, customerId, orderDetails);



        /*Connection connection = null;
        try {
            connection = DBConnection.getDbConnection().getConnection();
            boolean isExist = exist(orderId);
            *//*if order id already exist*//*
            if (isExist) {
                return false;
            }
            connection.setAutoCommit(false);

            *//*Refactored*//*
            boolean isSaved = save(new OrderDTO(orderId, orderDate, customerId));
            if(isSaved) {
                boolean isOrderDetailSaved = orderDetailDAO.saveDetails(orderDetails);
                if (isOrderDetailSaved) {
                    boolean isUpdated = itemDAO.updateItem(orderDetails);
                    if (isUpdated) {
                        connection.commit();
                    }
                }
            }
            connection.rollback();
            connection.setAutoCommit(true);
            return true;

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;*/
    }

    @Override
    public ArrayList<OrderDTO> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(OrderDTO dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO `Orders` (oid, date, customerID) VALUES (?,?,?)",dto.getOrderId(),
                dto.getOrderDate(),dto.getCustomerId());
    }

    @Override
    public boolean update(OrderDTO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT oid FROM `Orders` WHERE oid=?", id);
        return resultSet.next();
    }

    @Override
    public String generateId() throws SQLException, ClassNotFoundException {
        return null;
    }
}
