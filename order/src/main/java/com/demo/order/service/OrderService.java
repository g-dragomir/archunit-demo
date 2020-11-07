package com.demo.order.service;

import com.demo.order.domain.OrderItem;

import java.util.List;

public interface OrderService {

    OrderItem getOrderItemById(int id);

    List<OrderItem> getAllOrdersForCustomer(int customerId);

    List<OrderItem> getAllOrdersForProduct(int productId);
}
