package com.demo.order.rest;

import com.demo.order.domain.OrderItem;
import com.demo.order.service.OrderService;
import io.swagger.annotations.Api;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(value = "Order API", tags = "Order APIs")
@RestController
@RequestMapping(path = "orders",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
public class OrderResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderResource.class);

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/live", method = RequestMethod.GET)
    public ResponseEntity live() {
        return ResponseEntity.ok(DateTime.now().toString());
    }

    @RequestMapping(value = "/{orderId}", method = RequestMethod.GET)
    public ResponseEntity getOrderById(@PathVariable("orderId") int orderId) {

        try {
            OrderItem orderItem = orderService.getOrderItemById(orderId);

            return ResponseEntity.ok().body(orderItem);

        } catch (Exception ex) {
            LOGGER.error("Error getting order with id: " + orderId, ex);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @RequestMapping(value = "/customer/{customerId}", method = RequestMethod.GET)
    public ResponseEntity getAllOrdersForCustomer(@PathVariable("customerId") int customerId) {

        try {
            List<OrderItem> orderItems = orderService.getAllOrdersForCustomer(customerId);

            return ResponseEntity.ok().body(orderItems);

        } catch (Exception ex) {
            LOGGER.error("Error getting all the orders for the customerId: " + customerId, ex);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @RequestMapping(value = "/product/{productId}", method = RequestMethod.GET)
    public ResponseEntity getAllOrdersForProduct(@PathVariable("productId") int productId) {

        try {
            List<OrderItem> orderItems = orderService.getAllOrdersForProduct(productId);

            return ResponseEntity.ok().body(orderItems);

        } catch (Exception ex) {
            LOGGER.error("Error getting all the orders for the productId: " + productId, ex);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // other APIs ...
}
