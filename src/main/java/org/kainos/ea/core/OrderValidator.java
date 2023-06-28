package org.kainos.ea.core;

import org.kainos.ea.cli.OrderRequest;
import org.kainos.ea.db.CustomerDao;

import java.sql.SQLException;
import java.util.Calendar;

public class OrderValidator {
    private CustomerDao customerDao = new CustomerDao();
    public String isValidOrder(OrderRequest order) throws SQLException {
        if (customerDao.getCustomerById(order.getCustomerID()) == null){
            return "Customer does not exist in database";
        }

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, -1);

        if (order.getOrderDate().before(calendar.getTime())){
            return "Order was placed more than 1 year ago";
        }

        return null;

    }
}
