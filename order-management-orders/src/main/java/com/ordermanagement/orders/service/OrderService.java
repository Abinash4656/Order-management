package com.ordermanagement.orders.service;


import java.util.List;

import com.ordermanagement.orders.DTOs.OrderResponseTO;
import com.ordermanagement.orders.exception.OrderNotFoundException;
import com.ordermanagement.orders.requestatributes.OrderRequest;

public interface OrderService {
public void saveOrder(OrderRequest orderRequest);
public List<OrderResponseTO> getOrders() throws OrderNotFoundException;
}
