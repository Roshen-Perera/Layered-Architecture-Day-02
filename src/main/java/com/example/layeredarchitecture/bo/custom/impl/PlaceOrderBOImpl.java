package com.example.layeredarchitecture.bo.custom.impl;

import com.example.layeredarchitecture.bo.custom.PlaceOrderBO;
import com.example.layeredarchitecture.dao.DAOFactory;
import com.example.layeredarchitecture.dao.custom.CustomerDAO;
import com.example.layeredarchitecture.dao.custom.ItemDAO;
import com.example.layeredarchitecture.dao.custom.OrderDAO;
import com.example.layeredarchitecture.dao.custom.impl.OrderDetailDAOImpl;
import com.example.layeredarchitecture.db.DBConnection;
import com.example.layeredarchitecture.entity.Customer;
import com.example.layeredarchitecture.entity.Item;
import com.example.layeredarchitecture.model.CustomerDTO;
import com.example.layeredarchitecture.model.ItemDTO;
import com.example.layeredarchitecture.model.OrderDTO;
import com.example.layeredarchitecture.model.OrderDetailDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PlaceOrderBOImpl implements PlaceOrderBO {
    OrderDetailDAOImpl orderDetailDAO = (OrderDetailDAOImpl) DAOFactory.getdaoFactory().getDAO(DAOFactory.DAOTypes.ORDER_DETAILS);
    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getdaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);
    ItemDAO itemDAO = (ItemDAO) DAOFactory.getdaoFactory().getDAO(DAOFactory.DAOTypes.ITEM);
    OrderDAO orderDAO = (OrderDAO) DAOFactory.getdaoFactory().getDAO(DAOFactory.DAOTypes.ORDER);


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

    public Customer searchCustomer(String id) throws SQLException, ClassNotFoundException {
        return customerDAO.getCustomer(id);
    }

    public Item searchItem(String id) throws SQLException, ClassNotFoundException {
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
        ArrayList<CustomerDTO> customerDTOS = new ArrayList<>();
        ArrayList<Customer> customers = customerDAO.getAll();
        for (Customer customer : customers){
            customerDTOS.add(new CustomerDTO(customer.getId(), customer.getName(), customer.getAddress()));
        }
        return customerDTOS;






















    }

    public ArrayList<ItemDTO> getAllItem() throws SQLException, ClassNotFoundException {
        ArrayList<ItemDTO> itemDTOS = new ArrayList<>();
        ArrayList<Item> items = itemDAO.getAll();
        for (Item item : items){
            itemDTOS.add(new ItemDTO(item.getCode(), item.getDescription(), item.getUnitPrice(), item.getQtyOnHand()));
        }
        return itemDTOS;
    }
}
