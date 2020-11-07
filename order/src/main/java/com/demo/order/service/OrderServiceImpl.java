package com.demo.order.service;

import com.demo.order.dao.OrderDao;
import com.demo.order.domain.OrderItem;
import com.demo.order.domain.entity.Order;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private Mapper orderMapper;

    @Override
    public OrderItem getOrderItemById(int id) {

        Order order = orderDao.getOrderById(id);

        // business logic ...

        OrderItem orderItem = orderMapper.map(order, OrderItem.class);

        return orderItem;
    }

    @Override
    public List<OrderItem> getAllOrdersForCustomer(int customerId) {
        // business logic

        return new ArrayList<OrderItem>();
    }

    @Override
    public List<OrderItem> getAllOrdersForProduct(int productId) {
        // business logic

        return new ArrayList<OrderItem>();
    }
}
