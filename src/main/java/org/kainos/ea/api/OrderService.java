package org.kainos.ea.api;

import org.kainos.ea.cli.Order;
import org.kainos.ea.cli.OrderRequest;
import org.kainos.ea.client.*;
import org.kainos.ea.core.OrderValidator;
import org.kainos.ea.db.OrderDao;

import java.sql.SQLException;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;


public class OrderService {
    private OrderDao orderDao = new OrderDao();
    private OrderValidator orderValidator = new OrderValidator();
    public List<Order> getAllOrders() throws FailedToGetOrdersException {
        try{
            List<Order> orderList = orderDao.getAllOrders();

            return orderList;
        } catch (SQLException e) {
            System.err.println(e.getMessage());

            throw  new FailedToGetOrdersException();
        }
    }

    public Order getOrderById(int id) throws FailedToGetOrderException, OrderDoesNotExistException {
        try {
            Order order = orderDao.getOrderById(id);

            if (order == null){
                throw new OrderDoesNotExistException();
            }

            return order;
        } catch (SQLException e){
            System.err.println(e.getMessage());

            throw new FailedToGetOrderException();
        }
    }

    public int createOrder(OrderRequest order) throws FailedToCreateOrderException, InvalidOrderException {
        try {
            String validation = orderValidator.isValidOrder(order);

            if (validation != null){
                throw new InvalidOrderException(validation);
            }

            int id = orderDao.createOrder(order);

            if (id == -1){
                throw new FailedToCreateOrderException();
            }

            return id;
        } catch (SQLException e){
            System.err.println(e.getMessage());

            throw new FailedToCreateOrderException();
        }

    }

    public void updateOrder(int id, OrderRequest order) throws InvalidOrderException, OrderDoesNotExistException, FailedToUpdateOrderException {
        try {
            String validation = orderValidator.isValidOrder(order);

            if (validation != null) {
                throw new InvalidOrderException(validation);
            }

            Order orderToUpdate = orderDao.getOrderById(id);

            if (orderToUpdate == null) {
                throw new OrderDoesNotExistException();
            }

            orderDao.updateOrder(id, order);
        } catch (SQLException e) {
            System.err.println(e.getMessage());

            throw new FailedToUpdateOrderException();
        }
    }

    public void deleteOrder(int id) throws OrderDoesNotExistException, FailedToDeleteOrderException {
        try{
            Order orderToDelete = orderDao.getOrderById(id);

            if (orderToDelete == null){
                throw new OrderDoesNotExistException();
            }

            orderDao.deleteOrder(id);
        } catch (SQLException e) {
            System.err.println(e.getMessage());

            throw new FailedToDeleteOrderException();
        }
    }
}
