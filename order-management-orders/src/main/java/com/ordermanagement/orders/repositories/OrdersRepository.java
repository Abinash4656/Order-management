package com.ordermanagement.orders.repositories;

import org.springframework.data.repository.CrudRepository;

import com.ordermanagement.orders.entities.OrderData;

public interface OrdersRepository extends CrudRepository<OrderData, String>{

}
