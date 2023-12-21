package com.example.layeredarchitecture.dao.custom.impl;

import com.example.layeredarchitecture.dao.SQLUtil;
import com.example.layeredarchitecture.dao.custom.SQLQueryDAO;
import com.example.layeredarchitecture.model.OrderListDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLQueryDAOImpl implements SQLQueryDAO {
    public OrderListDTO orderList() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT\n" +
                "    o.oid,\n" +
                "    o.date,\n" +
                "    c.id,\n" +
                "    c.name,\n" +
                "    i.code,\n" +
                "    i.description,\n" +
                "    od.qty,\n" +
                "    od.unitPrice  \n" +
                "FROM\n" +
                "    orders o\n" +
                "JOIN\n" +
                "    customer c ON o.customerID = c.id\n" +
                "JOIN\n" +
                "    orderdetails od ON o.oid = od.oid\n" +
                "JOIN\n" +
                "    item i ON od.itemCode = i.code\n" +
                "ORDER BY\n" +
                "    o.oid ASC;\n");
        rst.next();
        return new OrderListDTO(
                rst.getString(1),
                rst.getDate(2).toLocalDate(),
                rst.getString(3),
                rst.getString(4),
                rst.getString(5),
                rst.getString(6),
                rst.getInt(7),
                rst.getBigDecimal(8)
        );
    }
}
