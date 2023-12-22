package com.example.layeredarchitecture.bo;

import com.example.layeredarchitecture.dao.custom.CustomerDAO;
import com.example.layeredarchitecture.dao.custom.impl.CustomerDAOImpl;
import com.example.layeredarchitecture.dao.custom.impl.ItemDAOImpl;
import com.example.layeredarchitecture.dao.custom.impl.OrderDAOImpl;
import com.example.layeredarchitecture.dao.custom.impl.OrderDetailDAOImpl;
import com.example.layeredarchitecture.db.DBConnection;
import com.example.layeredarchitecture.model.CustomerDTO;
import com.example.layeredarchitecture.model.ItemDTO;
import com.example.layeredarchitecture.model.OrderDTO;
import com.example.layeredarchitecture.model.OrderDetailDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PlaceOrderBOImpl implements PlaceOrderBO{
    OrderDetailDAOImpl orderDetailDAO = new OrderDetailDAOImpl();
    CustomerDAO customerDAO = new CustomerDAOImpl();
    ItemDAOImpl itemDAO = new ItemDAOImpl();
    OrderDAOImpl orderDAO = new OrderDAOImpl();
    public boolean placeOrder(String orderId, LocalDate orderDate, String customerId, List<OrderDetailDTO> orderDetails) throws SQLException {
        /*Transaction*/
        Connection connection = null;
        try {
            connection = DBConnection.getDbConnection().getConnection();
            boolean isExist = orderDAO.exist(orderId);
            /*if order id already exist*/
            if (isExist) {
                return false;
            }
            connection.setAutoCommit(false);

            /*Refactored*/
            boolean isSaved = orderDAO.save(new OrderDTO(orderId, orderDate, customerId));
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
        return false;
    }

    public CustomerDTO searchCustomer(String id) throws SQLException, ClassNotFoundException {
        return customerDAO.getCustomer(id);
    }

    public ItemDTO searchItem(String id) throws SQLException, ClassNotFoundException {
        return itemDAO.getItem(id);
    }

    public boolean existCustomer(String id) throws SQLException, ClassNotFoundException {
        return customerDAO.exist(id);
    }

    public boolean existItem(String id) throws SQLException, ClassNotFoundException {
        return itemDAO.exist(id);
    }

    public String generateOrderID() throws SQLException, ClassNotFoundException {
        return orderDAO.generateID();
    }

    public ArrayList<CustomerDTO> getAllCustomer() throws SQLException, ClassNotFoundException {
        return customerDAO.getAll();
    }

    public ArrayList<ItemDTO> getAllItem() throws SQLException, ClassNotFoundException {
        return itemDAO.getAll();
    }
}
