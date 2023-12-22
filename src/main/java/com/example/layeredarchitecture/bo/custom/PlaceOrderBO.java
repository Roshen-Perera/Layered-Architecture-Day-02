package com.example.layeredarchitecture.bo.custom;

import com.example.layeredarchitecture.bo.SuperBO;
import com.example.layeredarchitecture.entity.Customer;
import com.example.layeredarchitecture.entity.Item;
import com.example.layeredarchitecture.model.CustomerDTO;
import com.example.layeredarchitecture.model.ItemDTO;
import com.example.layeredarchitecture.model.OrderDetailDTO;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public interface PlaceOrderBO extends SuperBO {
     boolean placeOrder(String orderId, LocalDate orderDate, String customerId, List<OrderDetailDTO> orderDetails) throws SQLException;
     Customer searchCustomer(String id) throws SQLException, ClassNotFoundException;
     Item searchItem(String id) throws SQLException, ClassNotFoundException;
     boolean existCustomer(String id) throws SQLException, ClassNotFoundException;

     boolean existItem(String id) throws SQLException, ClassNotFoundException;

     String generateOrderID() throws SQLException, ClassNotFoundException;

     ArrayList<CustomerDTO> getAllCustomer() throws SQLException, ClassNotFoundException;

     ArrayList<ItemDTO> getAllItem() throws SQLException, ClassNotFoundException;

}
